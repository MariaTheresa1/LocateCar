package br.com.ada.locadoraspringboot.controller;

import br.com.ada.locadoraspringboot.model.Cliente;
import br.com.ada.locadoraspringboot.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public String clientes(Model model) {
        List<Cliente> clientes = this.clienteService.listarTodos();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("/cliente/add")
    public String addCliente(Model model, Cliente cliente) {
        model.addAttribute("add", Boolean.TRUE);
        model.addAttribute("cliente", Objects.nonNull(cliente) ? cliente : new Cliente());
        return "cliente-add";
    }

    @PostMapping("/cliente/add")
    public String criarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
                               BindingResult result,
                               Model model) {

        if(result.hasErrors()) {
            return addCliente(model, cliente);
        }

        this.clienteService.createCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/cliente/{clienteId}/delete")
    public String deletarCliente(@PathVariable("clienteId") Long clienteId) {
        this.clienteService.removerClientePorId(clienteId);
        return "redirect:/clientes";
    }

    @GetMapping("/cliente/{clienteId}/edit")
    public String mostrarEdicaoCliente(Model model, @PathVariable("clienteId") Long clienteId) {
        Optional<Cliente> optionalCliente = this.clienteService.buscarClientePorId(clienteId);
        optionalCliente.ifPresent(cliente -> model.addAttribute("cliente", cliente));
        model.addAttribute("add", Boolean.FALSE);
        return "cliente-add";
    }

    @PutMapping("/cliente/{clienteId}/edit")
    public String editarCliente(@ModelAttribute("cliente") Cliente cliente,
                                @PathVariable("clienteId") Long clienteId) {
        cliente.setId(clienteId);
        this.clienteService.createCliente(cliente);
        return "redirect:/clientes";
    }

}
