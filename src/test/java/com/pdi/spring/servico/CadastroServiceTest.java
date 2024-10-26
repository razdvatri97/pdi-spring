package com.pdi.spring.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.pdi.spring.entidade.Cadastro;
import com.pdi.spring.entidade.Endereco;
import com.pdi.spring.entidade.Pessoa;
import com.pdi.spring.repositorio.CadastroRepositorio;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CadastroServiceTest {

    @Mock
    CadastroRepositorio repositorio;

    @InjectMocks
    CadastroService service;

    private Cadastro cadastro;

    @BeforeEach
    public void setup(){
        Pessoa pessoa = new Pessoa(1L, "Joao", "12345678900", LocalDate.of(1997, 11, 15));
        Endereco endereco = new Endereco(1L,"Rua 1", 450, "Cidade 1", "Bairro 1", "RS", 91370230);
        cadastro = new Cadastro(1L, pessoa, endereco);
    }

    @Test
    void deveEncontrarCadastroCPF(){
        String cpf = "12345678900";

        when(repositorio.findCadastroByPessoa_Cpf(cpf)).thenReturn(Optional.of(cadastro));

        Optional<Cadastro> resultado = service.consultar(cpf);

        assertTrue(resultado.isPresent());
        assertEquals(cadastro, resultado.get());
        verify(repositorio, times(1)).findCadastroByPessoa_Cpf(cpf);
    }

    @Test
    void naoDeveEncontrarCadastroCPF(){
        String cpf = "12345678900";
        when(repositorio.findCadastroByPessoa_Cpf(cpf)).thenReturn(Optional.empty());

        Optional<Cadastro> resultado = service.consultar(cpf);

        assertFalse(resultado.isPresent());
        verify(repositorio, times(1)).findCadastroByPessoa_Cpf(cpf);
    }

    @Test
    void deveAtualizarCadastro(){
        Pessoa pessoa = new Pessoa(1L,"Joao", "12345678900", LocalDate.of(1997, 11, 15));
        Endereco endereco = new Endereco(1L,"Rua 2", 450, "Cidade 1", "Bairro 1", "RS", 91370230);

        when(repositorio.findCadastroById(anyLong())).thenReturn(Optional.of(cadastro));

        ResponseEntity<String> resultado = service.atualizar(1L, pessoa, endereco);
        assertEquals(200, resultado.getStatusCode()
            .value());
    }

    @Test
    void naoDeveAtualizarCadastro(){
        Pessoa pessoa = new Pessoa(1L,"Joao", "12345678900", LocalDate.of(1997, 11, 15));
        Endereco endereco = new Endereco(1L,"Rua 2", 450, "Cidade 1", "Bairro 1", "RS", 91370230);

        when(repositorio.findCadastroById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<String> resultado = service.atualizar(1L, pessoa, endereco);
        assertEquals(404, resultado.getStatusCode()
            .value());
    }
}
