package br.com.ada.locadoraspringboot.service;

import br.com.ada.locadoraspringboot.model.Cliente;
import br.com.ada.locadoraspringboot.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void createCliente(Cliente cliente){
        this.clienteRepository.save(cliente);
    }

}
