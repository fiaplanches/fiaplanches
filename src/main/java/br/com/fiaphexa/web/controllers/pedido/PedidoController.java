package br.com.fiaphexa.web.controllers.pedido;

import br.com.fiaphexa.web.controllers.pedido.request.CriaPedidoRequestDto;
import br.com.fiaphexa.aplicacao.dtos.PageInfoDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.BuscaPedidosClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.BuscaPedidosCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.CriaPedidoCasoDeUso;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final BuscaPedidosClienteCasoDeUso buscaPedidosClienteCasoDeUso;
    private final BuscaPedidosCasoDeUso buscaPedidosCasoDeUso;
    private final CriaPedidoCasoDeUso criaPedidoCasoDeUso;

    public PedidoController(BuscaPedidosClienteCasoDeUso buscaPedidosClienteCasoDeUso,
                            BuscaPedidosCasoDeUso buscaPedidosCasoDeUso, CriaPedidoCasoDeUso criaPedidoCasoDeUso){
        this.buscaPedidosClienteCasoDeUso = buscaPedidosClienteCasoDeUso;
        this.buscaPedidosCasoDeUso = buscaPedidosCasoDeUso;
        this.criaPedidoCasoDeUso = criaPedidoCasoDeUso;

}

    @GetMapping()
    public ResponseEntity<Page<PedidoDto>> buscaPedidos(@PageableDefault Pageable pageable) {
        PageInfoDto pageInfo = new PageInfoDto();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<PedidoDto> pedidos = buscaPedidosCasoDeUso.buscaPedidos(pageInfo);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PageImpl<PedidoDto>(pedidos, pageable, pedidos.size())
        );
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Page<PedidoDto>> buscaPedidosCliente(@PageableDefault Pageable pageable, @PathVariable String cpf) {
        PageInfoDto pageInfo = new PageInfoDto();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<PedidoDto> pedidos = buscaPedidosClienteCasoDeUso.buscaPedidosCliente(cpf, pageInfo);
        return ResponseEntity.status(HttpStatus.OK).body(
                new PageImpl<PedidoDto>(pedidos, pageable, pedidos.size())
        );
    }

    @PostMapping()
    public ResponseEntity<PedidoDto> criaPedido(@RequestBody @Valid CriaPedidoRequestDto criaPedidoRequestDto) {
        PedidoDto pedido = criaPedidoCasoDeUso.criaPedido(CriaPedidoRequestDto.toPedidoComIdProdutosDto(criaPedidoRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
