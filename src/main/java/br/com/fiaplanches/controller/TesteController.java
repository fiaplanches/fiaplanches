package br.com.fiaplanches.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @GetMapping("/exemploTeste")
    public String getExemplo(){
        return "Teste realizado com sucesso";
    }
}
