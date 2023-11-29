package br.com.rhsystem.repository;

import br.com.rhsystem.entity.FuncionarioEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

    List<FuncionarioEntity> findByNome(String nome, Pageable pageable);

}
