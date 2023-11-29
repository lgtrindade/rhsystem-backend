package br.com.rhsystem.service;

import br.com.rhsystem.dto.FuncionarioDTO;
import br.com.rhsystem.entity.FuncionarioEntity;
import br.com.rhsystem.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public FuncionarioDTO incluirFuncionario(FuncionarioDTO funcionarioDTO) {
        FuncionarioEntity funcionarioEntity = FuncionarioEntity.toEntity(funcionarioDTO);
        FuncionarioEntity funcionarioSalvo = funcionarioRepository.save(funcionarioEntity);
        return FuncionarioDTO.toDTO(funcionarioSalvo);
    }

    @Transactional
    public void alterarFuncionario(Long id, FuncionarioDTO funcionarioAlterado) {
        salvarAlteracoesFuncionario(id, funcionarioAlterado);
    }

    private void salvarAlteracoesFuncionario(Long id, FuncionarioDTO funcionarioAlterado) {
        Optional<FuncionarioEntity> funcionarioNaBase = funcionarioRepository.findById(id);
        if (funcionarioNaBase.isPresent()) {
            FuncionarioEntity funcionario = funcionarioNaBase.get();
            funcionario.setEmail(funcionarioAlterado.getEmail());
            funcionario.setTelefone(funcionarioAlterado.getTelefone());
            funcionario.setCargo(funcionarioAlterado.getCargo());
            funcionario.setDepartamento(funcionarioAlterado.getDepartamento());
            funcionario.setEndereco(funcionarioAlterado.getEndereco().getLogradouro());
            funcionario.setBairro(funcionarioAlterado.getEndereco().getBairro());
            funcionario.setCep(funcionarioAlterado.getEndereco().getCep());
            funcionario.setCidade(funcionarioAlterado.getEndereco().getCidade().getNome());
            funcionario.setEstado(funcionarioAlterado.getEndereco().getCidade().getEstado().getNome());
            funcionarioRepository.save(funcionario);
        }
    }

    @Transactional
    public void excluirFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public FuncionarioDTO buscarFuncionario(Long id) {
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));

        return FuncionarioDTO.toDTO(funcionarioEntity);
    }

    public List<FuncionarioDTO> buscarTodosFuncionarios(Pageable pageable) {
        Page<FuncionarioEntity> funcionarios = funcionarioRepository.findAll(pageable);
        List<FuncionarioDTO> funcionariosDTO = new ArrayList<>();
        converterListaFuncionarios(funcionarios.getContent(), funcionariosDTO);
        return funcionariosDTO;
    }

    private void converterListaFuncionarios(List<FuncionarioEntity> funcionarios, List<FuncionarioDTO> funcionariosDTO) {
        for (FuncionarioEntity funcionarioEntity : funcionarios) {
            FuncionarioDTO funcionarioDTO = FuncionarioDTO.toDTO(funcionarioEntity);
            funcionariosDTO.add(funcionarioDTO);
        }
    }

}