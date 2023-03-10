package br.com.ada.locadoraspringboot.controller;

import br.com.ada.locadoraspringboot.model.Cliente;
import br.com.ada.locadoraspringboot.service.ClienteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public void createCliente(@RequestBody Cliente cliente){
        this.clienteService.createCliente(cliente);
    }

    @GetMapping("/todos")
    public List<Cliente> listarTodos(){
        return this.clienteService.listarTodos();
    }

    @GetMapping("/by/{id}")
    public ResponseEntity<Cliente> buscarClientePor(@PathVariable("id") Long id) {
        Optional<Cliente> optionalCliente = this.clienteService.buscarClientePorId(id);

        if(optionalCliente.isPresent()){
            return ResponseEntity.ok(optionalCliente.get());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
