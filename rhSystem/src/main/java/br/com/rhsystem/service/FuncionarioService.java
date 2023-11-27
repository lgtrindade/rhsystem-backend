package br.com.rhsystem.service;

import br.com.rhsystem.dto.FuncionarioDTO;
import br.com.rhsystem.entity.FuncionarioEntity;
import br.com.rhsystem.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Lazy
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public void incluirFuncionario(FuncionarioDTO funcionarioDTO) {
        FuncionarioEntity funcionarioEntity = FuncionarioEntity.toEntity(funcionarioDTO);
        funcionarioRepository.save(funcionarioEntity);
    }

    @Transactional
    public void alterarFuncionario(FuncionarioDTO funcionarioAlterado) {
        salvarAlteracoesFuncionario(funcionarioAlterado);
    }

    private void salvarAlteracoesFuncionario(FuncionarioDTO funcionarioAlterado) {
        Optional<FuncionarioEntity> funcionarioNaBase = funcionarioRepository.findById(funcionarioAlterado.getId());
        if (funcionarioNaBase.isPresent()) {
            FuncionarioEntity funcionario = funcionarioNaBase.get();
            funcionario.setEmail(funcionarioAlterado.getEmail());
            funcionario.setTelefone(funcionarioAlterado.getTelefone());
            funcionario.setCargo(funcionarioAlterado.getCargo());
            funcionario.setDepartamento(funcionarioAlterado.getDepartamento());
            funcionario.setEndereco(funcionarioAlterado.getEndereco());
            funcionario.setBairro(funcionarioAlterado.getBairro());
            funcionario.setCep(funcionarioAlterado.getCep());
            funcionario.setCidade(funcionarioAlterado.getCidade());
            funcionario.setEstado(funcionario.getEstado());
            funcionarioRepository.save(funcionario);
        }
    }

    @Transactional
    public void excluirFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public Optional<FuncionarioDTO> buscarFuncionario(Long id) {
        Optional<FuncionarioEntity> funcionario = funcionarioRepository.findById(id);
        return retornaFuncionario(funcionario);
    }

    private static Optional<FuncionarioDTO> retornaFuncionario(Optional<FuncionarioEntity> funcionario) {
        if (funcionario.isPresent()) {
            FuncionarioEntity funcionarioEntity = funcionario.get();
            FuncionarioDTO funcionarioDTO = FuncionarioDTO.toDTO(funcionarioEntity);
            return Optional.of(funcionarioDTO);
        } else {
            return Optional.empty();
        }
    }

    public List<FuncionarioDTO> buscarTodosFuncionarios() {
        List<FuncionarioEntity> funcionarios = funcionarioRepository.findAll();
        List<FuncionarioDTO> funcionariosDTO = new ArrayList<>();
        converterListaFuncionarios(funcionarios, funcionariosDTO);
        return funcionariosDTO;
    }

    private static void converterListaFuncionarios(List<FuncionarioEntity> funcionarios, List<FuncionarioDTO> funcionariosDTO) {
        for (FuncionarioEntity funcionarioEntity : funcionarios) {
            FuncionarioDTO funcionarioDTO = FuncionarioDTO.toDTO(funcionarioEntity);
            funcionariosDTO.add(funcionarioDTO);
        }
    }


}