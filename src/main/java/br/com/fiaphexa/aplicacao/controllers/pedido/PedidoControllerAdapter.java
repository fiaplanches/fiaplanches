package br.com.fiaphexa.aplicacao.controllers.pedido;

import br.com.fiaphexa.aplicacao.controllers.pedido.response.BuscaPedidoResponse;
import br.com.fiaphexa.aplicacao.controllers.produto.response.RetornaProdutoDto;
import br.com.fiaphexa.dominio.dtos.PageInfoDto;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.BuscaPedidosClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.CriaPedidoPortaEntrada;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("pedidos")
public class PedidoControllerAdapter {

    private final BuscaPedidosClientePortaEntrada buscaPedidosClientePortaEntrada;
    private final BuscaPedidosPortaEntrada buscaPedidosPortaEntrada;
    private final CriaPedidoPortaEntrada criaPedidoPortaEntrada;

    public PedidoControllerAdapter(BuscaPedidosClientePortaEntrada buscaPedidosClientePortaEntrada,
                                   BuscaPedidosPortaEntrada buscaPedidosPortaEntrada, CriaPedidoPortaEntrada criaPedidoPortaEntrada){
        this.buscaPedidosClientePortaEntrada = buscaPedidosClientePortaEntrada;
        this.buscaPedidosPortaEntrada = buscaPedidosPortaEntrada;
        this.criaPedidoPortaEntrada = bus

}

    @GetMapping("/todos")
    public List<Page<BuscaPedidoResponse>> buscaPedidos(@PageableDefault Pageable pageable) {
        PageInfoDto pageInfo = new PageInfoDto();
        BeanUtils.copyProperties(pageable, pageInfo);
    }
//
//    @PostMapping
//    public RetornaProdutoDto cadastrarProduto(@RequestBody ProdutoRequestDto cadastraProdutoRequest, UriComponentsBuilder uriBuilder) {
//        var produto = cadastraProdutoPortaEntrada.cadastrarProduto(cadastraProdutoRequest.toProduto());
//        var retornoProduto = new RetornaProdutoDto(produto);
//        var uri = uriBuilder.path("/produtos/{nomeProduto}").buildAndExpand((retornoProduto)).toUri();
//        return retornoProduto;
//    }
//
//    @PutMapping
//    public RetornaProdutoDto atualizarProduto(@RequestBody AtualizaProdutoDto atualizaProdutoDto) {
//        return new RetornaProdutoDto(atualizaProdutoPortaEntrada.atualizaProduto(atualizaProdutoDto.toProdutoDto()));
//    }
//
//    @DeleteMapping("/{id}")
//    public String removeProduto(@PathVariable @Valid Long id) {
//        removeProdutoPortaEntrada.remove(id);
//        return "Produto excluido com sucesso";
//    }
}
