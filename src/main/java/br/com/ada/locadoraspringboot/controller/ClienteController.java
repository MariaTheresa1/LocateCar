package br.com.ada.locadoraspringboot.controller;

import br.com.ada.locadoraspringboot.model.Cliente;
import br.com.ada.locadoraspringboot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public void createCliente(@RequestBody Cliente cliente){
        this.clienteService.createCliente(cliente);
    }

}
