package com.pdi.spring.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Calendar;

@Entity
public record Cadastro(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id,
                       @Column(nullable = false) @NotBlank Pessoa pessoa,
                       @Column(nullable = false) @NotBlank Endereco endereco){

    private static long nextId = 1;

    public static Cadastro criarNovoCadastro(@NotEmpty Pessoa pessoa, @NotEmpty Endereco endereco) {
        return new Cadastro(nextId++, pessoa, endereco);
    }

    public static Cadastro atualizarCadastro(Cadastro cadastroExistente, Pessoa novaPessoa, Endereco novoEndereco) {
        return new Cadastro(cadastroExistente.id(), novaPessoa, novoEndereco);
    }
}
