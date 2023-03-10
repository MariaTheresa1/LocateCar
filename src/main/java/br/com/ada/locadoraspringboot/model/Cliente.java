package br.com.ada.locadoraspringboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cpf;
    private String nome;
    private String dataDeNascimento;
    private String endereco;
    private String telefone;
    private String email;
    private Float saldoDevedor;

}
