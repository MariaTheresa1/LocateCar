package br.com.ada.locadoraspringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "tb_cliente")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "O preenchimento do CPF é obrigatório.")
    @NotBlank(message = "O campo CPF não pode ser vazio.")
    @Size(min = 11, message = "O CPF deve ter no minimo 11 caracteres.")
    @Column(unique = true)
    private String cpf;
    private String nome;
    private String dataDeNascimento;
    private String endereco;
    private String telefone;
    private String email;
    private Float saldoDevedor;

}
