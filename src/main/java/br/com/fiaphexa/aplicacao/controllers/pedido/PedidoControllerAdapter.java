package br.com.fiaphexa.aplicacao.controllers.pedido;

import br.com.fiaphexa.dominio.dtos.PageInfoDto;
import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.BuscaPedidosClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.BuscaPedidosPortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.CriaPedidoPortaEntrada;
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
public class PedidoControllerAdapter {

    private final BuscaPedidosClientePortaEntrada buscaPedidosClientePortaEntrada;
    private final BuscaPedidosPortaEntrada buscaPedidosPortaEntrada;
    private final CriaPedidoPortaEntrada criaPedidoPortaEntrada;

    public PedidoControllerAdapter(BuscaPedidosClientePortaEntrada buscaPedidosClientePortaEntrada,
                                   BuscaPedidosPortaEntrada buscaPedidosPortaEntrada, CriaPedidoPortaEntrada criaPedidoPortaEntrada){
        this.buscaPedidosClientePortaEntrada = buscaPedidosClientePortaEntrada;
        this.buscaPedidosPortaEntrada = buscaPedidosPortaEntrada;
        this.criaPedidoPortaEntrada = criaPedidoPortaEntrada;

}

    @GetMapping()
    public ResponseEntity<Page<PedidoDto>> buscaPedidos(@PageableDefault Pageable pageable) {
        PageInfoDto pageInfo = new PageInfoDto();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<PedidoDto> pedidos = buscaPedidosPortaEntrada.buscaPedidos(pageInfo);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PageImpl<PedidoDto>(pedidos, pageable, pedidos.size())
        );
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Page<PedidoDto>> buscaPedidosCliente(@PageableDefault Pageable pageable, @PathVariable String cpf) {
        PageInfoDto pageInfo = new PageInfoDto();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<PedidoDto> pedidos = buscaPedidosClientePortaEntrada.buscaPedidosCliente(cpf, pageInfo);
        return ResponseEntity.status(HttpStatus.OK).body(
                new PageImpl<PedidoDto>(pedidos, pageable, pedidos.size())
        );
    }

    @PostMapping()
    public ResponseEntity<PedidoDto> criaPedido(PedidoDto pedidoDto) {
        PedidoDto pedido = criaPedidoPortaEntrada.criaPedido(pedidoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
