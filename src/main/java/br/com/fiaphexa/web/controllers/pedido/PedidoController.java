package br.com.fiaphexa.web.controllers.pedido;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.*;
import br.com.fiaphexa.dominio.enuns.StatusPedido;
import br.com.fiaphexa.web.controllers.pedido.request.AdicionaCarrinhoRequestDto;
import br.com.fiaphexa.aplicacao.dtos.PageInfoDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;
import br.com.fiaphexa.web.controllers.pedido.request.AtualizaStatusPagamentoRequestDto;
import br.com.fiaphexa.web.controllers.pedido.request.PagamentoRequestDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final BuscaPedidosClienteCasoDeUso buscaPedidosClienteCasoDeUso;
    private final BuscaPedidosCasoDeUso buscaPedidosCasoDeUso;
    private final PagaPedidoCasoDeUso pagaPedidoCasoDeUso;
    private final AdicionaNoCarrinhoCasoDeUso adicionaNoCarrinhoCasoDeUso;
    private final ConsultaStatusPagamentoCasoDeUso consultaStatusPagamentoCasoDeUso;
    private final AtualizaStatusPagamentoCasoDeUso atualizaStatusPagamentoCasoDeUso;
    private final AtualizaPedidoCasoDeUso atualizaPedidoCasoDeUso;
    private final BuscaPedidosOrdenadosCasoDeUso buscaPedidosOrdenadosCasoDeUso;

    public PedidoController(BuscaPedidosClienteCasoDeUso buscaPedidosClienteCasoDeUso,
                            BuscaPedidosCasoDeUso buscaPedidosCasoDeUso, PagaPedidoCasoDeUso pagaPedidoCasoDeUso,
                            AdicionaNoCarrinhoCasoDeUso adicionaNoCarrinhoCasoDeUso,
                            ConsultaStatusPagamentoCasoDeUso consultaStatusPagamentoCasoDeUso,
                            AtualizaStatusPagamentoCasoDeUso atualizaStatusPagamentoCasoDeUso,
                            AtualizaPedidoCasoDeUso atualizaPedidoCasoDeUso,
                            BuscaPedidosOrdenadosCasoDeUso buscaPedidosOrdenadosCasoDeUso){
        this.buscaPedidosClienteCasoDeUso = buscaPedidosClienteCasoDeUso;
        this.buscaPedidosCasoDeUso = buscaPedidosCasoDeUso;
        this.pagaPedidoCasoDeUso = pagaPedidoCasoDeUso;
        this.adicionaNoCarrinhoCasoDeUso = adicionaNoCarrinhoCasoDeUso;
        this.consultaStatusPagamentoCasoDeUso = consultaStatusPagamentoCasoDeUso;
        this.atualizaStatusPagamentoCasoDeUso = atualizaStatusPagamentoCasoDeUso;
        this.atualizaPedidoCasoDeUso = atualizaPedidoCasoDeUso;
        this.buscaPedidosOrdenadosCasoDeUso = buscaPedidosOrdenadosCasoDeUso;
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

    @PostMapping("/paga-pedido")
    public ResponseEntity<HttpStatus> pagaPedido(@RequestBody @Valid PagamentoRequestDto pagamentoRequestDto) {
        pagaPedidoCasoDeUso.pagaPedido(pagamentoRequestDto);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/adiciona-carrinho")
    public ResponseEntity<Long> adicionaNoCarrinho(@RequestBody @Valid AdicionaCarrinhoRequestDto adicionaCarrinhoRequestDto) {
        PedidoDto pedido = adicionaNoCarrinhoCasoDeUso.adicionaNoCarrinho(AdicionaCarrinhoRequestDto.toPedidoComIdProdutosDto(adicionaCarrinhoRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido.id());
    }

    @GetMapping("/consulta-status-pagamento")
    public ResponseEntity<String> consultaStatusPagamento(@RequestBody @Valid PagamentoRequestDto pagamentoRequestDto) {
        var statusPagamento = consultaStatusPagamentoCasoDeUso.consultaStatusPagamento(pagamentoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(statusPagamento);
    }

    @PostMapping("/atualiza-status-pagamento")
    public ResponseEntity<Boolean> atualizaPagamento(@RequestBody @Valid AtualizaStatusPagamentoRequestDto requestDto) {
        var statusPagamento = atualizaStatusPagamentoCasoDeUso.atualizaStatusPagamento(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(statusPagamento);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PedidoDto> atualizaPedido(@PathVariable @Valid Long id) {
        PedidoDto pedidoDto = atualizaPedidoCasoDeUso.atualizaPedido(id);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoDto);
    }

    @GetMapping("/ordenados")
    public ResponseEntity<Page<PedidoDto>> buscaPedidosOrdenados(@PageableDefault Pageable pageable) {
        List<PedidoDto> pedidos = buscaPedidosOrdenadosCasoDeUso.buscaPedidosOrdenados();
        List<PedidoDto> listaOrdenada = new ArrayList<>();

        pedidos.stream().filter(pedidosOrdenados -> pedidosOrdenados.statusPedido() == StatusPedido.PRONTO).forEach(listaOrdenada::add);
        pedidos.stream().filter(pedidosOrdenados -> pedidosOrdenados.statusPedido() == StatusPedido.EM_PREPARO).forEach(listaOrdenada::add);
        pedidos.stream().filter(pedidosOrdenados -> pedidosOrdenados.statusPedido() == StatusPedido.RECEBIDO).forEach(listaOrdenada::add);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PageImpl<PedidoDto>(listaOrdenada, pageable, pedidos.size())
        );

    }
}
