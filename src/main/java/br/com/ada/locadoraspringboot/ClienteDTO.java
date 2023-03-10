package br.com.ada.locadoraspringboot;

import lombok.*;
@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String cpf;
    private String nome;
    private String dataDeNascimento;
    private String endereco;
    private String telefone;
    private String email;
    private Float saldoDevedor;

}
