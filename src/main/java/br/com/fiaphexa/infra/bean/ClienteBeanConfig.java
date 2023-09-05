package br.com.fiaphexa.infra.bean;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.AtualizaClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.CadastraClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.ProcuraClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.RemoveClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;
import br.com.fiaphexa.aplicacao.casosdeuso.cliente.AtualizaClienteCasoDeUsoImpl;
import br.com.fiaphexa.aplicacao.casosdeuso.cliente.CadastraClienteCasoDeUsoImpl;
import br.com.fiaphexa.aplicacao.casosdeuso.cliente.ProcuraClienteCasoDeUsoImpl;
import br.com.fiaphexa.aplicacao.casosdeuso.cliente.RemoveClienteCasoDeUsoImpl;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteBeanConfig {

    @Bean
    CadastraClienteCasoDeUso cadastraClientePortaEntrada(ClienteRepositoryService clienteRepositoryService){
        return new CadastraClienteCasoDeUsoImpl(clienteRepositoryService);
    }

    @Bean
    AtualizaClienteCasoDeUso atualizaClientePortaEntrada(ClienteRepositoryService clienteRepositoryService){
        return new AtualizaClienteCasoDeUsoImpl(clienteRepositoryService);
    }

    @Bean
    RemoveClienteCasoDeUso removeClientePortaEntrada(ClienteRepositoryService clienteRepositoryService,
                                                     PedidoRepositoryService pedidoRepositoryService){
        return new RemoveClienteCasoDeUsoImpl(clienteRepositoryService, pedidoRepositoryService);
    }

    @Bean
    ProcuraClienteCasoDeUso procuraClientePortaEntrada(ClienteRepositoryService clienteRepositoryService){
        return new ProcuraClienteCasoDeUsoImpl(clienteRepositoryService);
    }
}
