package com.pdi.spring.repositorio;

import com.pdi.spring.entidade.Cadastro;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepositorio extends JpaRepository<Cadastro, Long> {

    Optional<Cadastro> findCadastroById(long id);

    Optional<Cadastro> findCadastroByPessoa_Cpf(String cpf);

}
