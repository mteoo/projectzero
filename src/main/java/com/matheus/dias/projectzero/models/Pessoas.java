package com.matheus.dias.projectzero.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheus.dias.projectzero.core.jpavalidator.cpf.Cpf;
import com.matheus.dias.projectzero.core.jpavalidator.email.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PESSOAS")
@NoArgsConstructor
public class Pessoas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 1, max = 250, message = "O campo nome deve possuir entre {min} e {max} caracteres")
    @NotBlank(message = "O campo 'Nome' é obrigatório")
    private String nome;

    @Size(min = 0, max = 250, message = "O campo e-mail deve possuir no máximo {max} caracteres")
    @Email(message = "O formato do e-mail informado é inválido")
    private String email;

    @NotNull(message = "O campo 'Data de nascimento' é obrigatório")
    private LocalDate dtNascimento;

    @Size(min = 0, max = 40, message = "O campo naturalidade deve possuir no máximo {max} caracteres")
    private String naturalidade;

    @Size(min = 0, max = 40, message = "O campo nacionalidade deve possuir no máximo {max} caracteres")
    private String nacionalidade;

    @NotNull(message = "O campo 'CPF' é obrigatório")
    @Cpf
    @Column(unique = true, name = "cpf")
    @Size(min = 11, max = 11, message = "O campo CPF deve possuir {max} caracteres")
    private String cpf;

    @JsonIgnore
    private LocalDateTime audDhCriacao;

    @JsonIgnore
    private LocalDateTime audDhAlteracao;

    @JsonIgnore
    private Integer audVersao = 0;

    public void updateVersion() {
        this.audVersao = this.audVersao + 1;
    }
}
