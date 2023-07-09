package br.com.fiaphexa.infra.bean;

import br.com.fiaphexa.dominio.portas.entrada.clientes.AtualizaClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.clientes.CadastraClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.clientes.ProcuraClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.clientes.RemoveClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.cliente.ClienteRepositoryPortaSaida;
import br.com.fiaphexa.dominio.services.cliente.AtualizaClienteService;
import br.com.fiaphexa.dominio.services.cliente.CadastraClienteService;
import br.com.fiaphexa.dominio.services.cliente.ProcuraClienteService;
import br.com.fiaphexa.dominio.services.cliente.RemoveClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteBeanConfig {

    @Bean
    CadastraClientePortaEntrada cadastraClientePortaEntrada(ClienteRepositoryPortaSaida clienteRepositoryPortaSaida){
        return new CadastraClienteService(clienteRepositoryPortaSaida);
    }

    @Bean
    AtualizaClientePortaEntrada atualizaClientePortaEntrada(ClienteRepositoryPortaSaida clienteRepositoryPortaSaida){
        return new AtualizaClienteService(clienteRepositoryPortaSaida);
    }

    @Bean
    RemoveClientePortaEntrada removeClientePortaEntrada(ClienteRepositoryPortaSaida clienteRepositoryPortaSaida){
        return new RemoveClienteService(clienteRepositoryPortaSaida);
    }

    @Bean
    ProcuraClientePortaEntrada procuraClientePortaEntrada(ClienteRepositoryPortaSaida clienteRepositoryPortaSaida){
        return new ProcuraClienteService(clienteRepositoryPortaSaida);
    }
}
