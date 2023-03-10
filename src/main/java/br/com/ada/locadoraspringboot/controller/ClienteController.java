package br.com.ada.locadoraspringboot.controller;

import br.com.ada.locadoraspringboot.ClienteDTO;
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
    public ResponseEntity<String> createCliente(@RequestBody Cliente cliente){
        try {

            Cliente clienteDB = Cliente.builder()
                    .cpf(cliente.getCpf())
                    .nome(cliente.getNome())
                    .dataDeNascimento(cliente.getDataDeNascimento())
                    .endereco(cliente.getEndereco())
                    .telefone(cliente.getTelefone())
                    .email(cliente.getEmail())
                    .saldoDevedor(cliente.getSaldoDevedor())
                    .build();

            this.clienteService.createCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente criado.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
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

    @PutMapping("/")
    public ResponseEntity<String> atualizarCliente(@RequestBody ClienteDTO cliente) {

        try {
            Optional<Cliente> optionalCliente = this.clienteService.buscarClientePeloCpf(cliente.getCpf());

            if (optionalCliente.isPresent()) {
                Cliente clientePorCpfDB = optionalCliente.get();
                Cliente clienteAtualizar = Cliente.builder().id(clientePorCpfDB.getId())
                        .cpf(cliente.getCpf())
                        .nome(cliente.getNome())
                        .dataDeNascimento(cliente.getDataDeNascimento())
                        .endereco(cliente.getEndereco())
                        .telefone(cliente.getTelefone())
                        .email(cliente.getEmail())
                        .saldoDevedor(cliente.getSaldoDevedor())
                        .build();

                this.clienteService.createCliente(clienteAtualizar);

                return ResponseEntity
                        .ok("Cliente atualizado.");
            }
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public void removerCliente(@PathVariable Long id){
        this.clienteService.removerClientePorId(id);
    }

}
