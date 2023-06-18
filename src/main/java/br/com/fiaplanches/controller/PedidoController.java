package br.com.fiaplanches.controller;

import br.com.fiaplanches.records.PedidoRecord;
import br.com.fiaplanches.service.ClienteService;
import br.com.fiaplanches.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pedido")
@RestController
public class PedidoController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{idCliente}")
    public ResponseEntity<List<PedidoRecord>> buscaPedidosCliente(@PathVariable Long idCliente) {
        List<PedidoRecord> retornaPedidos = clienteService.buscaPedidosCliente(idCliente);
        return ResponseEntity.ok(retornaPedidos);
    }

    @PostMapping
    public ResponseEntity<PedidoRecord> criarPedido(@RequestBody PedidoRecord pedidoRecord) {
        PedidoRecord retornoPedido = pedidoService.criarPedido(pedidoRecord);
        return ResponseEntity.ok(retornoPedido);
    }
}
