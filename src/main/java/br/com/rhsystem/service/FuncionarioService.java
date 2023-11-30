package br.com.rhsystem.service;

import br.com.rhsystem.dto.FuncionarioDTO;
import br.com.rhsystem.entity.FuncionarioEntity;
import br.com.rhsystem.exception.FuncionarioNaoEncotradoException;
import br.com.rhsystem.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public FuncionarioDTO alterarFuncionario(Long id, FuncionarioDTO funcionarioAlterado) {
        FuncionarioEntity funcionarioEntity = salvarAlteracoesFuncionario(id, funcionarioAlterado);
        return FuncionarioDTO.toDTO(funcionarioEntity);
    }

    private FuncionarioEntity salvarAlteracoesFuncionario(Long id, FuncionarioDTO funcionarioAlterado) {
        FuncionarioEntity funcionario = buscar(id);

        funcionario.setEmail(funcionarioAlterado.getEmail());
        funcionario.setTelefone(funcionarioAlterado.getTelefone());
        funcionario.setCargo(funcionarioAlterado.getCargo());
        funcionario.setDepartamento(funcionarioAlterado.getDepartamento());
        funcionario.setEndereco(funcionarioAlterado.getEndereco().getLogradouro());
        funcionario.setComplemento(funcionarioAlterado.getEndereco().getComplemento());
        funcionario.setBairro(funcionarioAlterado.getEndereco().getBairro());
        funcionario.setCep(funcionarioAlterado.getEndereco().getCep());
        funcionario.setCidade(funcionarioAlterado.getEndereco().getCidade().getNome());
        funcionario.setCidadeId(funcionarioAlterado.getEndereco().getCidade().getId());
        funcionario.setEstado(funcionarioAlterado.getEndereco().getCidade().getEstado().getNome());
        funcionario.setEstadoId(funcionarioAlterado.getEndereco().getCidade().getEstado().getId());
        return funcionarioRepository.save(funcionario);

    }

    @Transactional
    public void excluirFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public FuncionarioDTO buscarFuncionario(Long id) {
        FuncionarioEntity funcionarioEntity = buscar(id);

        return FuncionarioDTO.toDTO(funcionarioEntity);
    }

    private FuncionarioEntity buscar(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioNaoEncotradoException(String.format("Funcionario de id: %s, não encotrado", id)));
    }

    public Page<FuncionarioDTO> buscarTodosFuncionarios(Pageable pageable) {
        Page<FuncionarioEntity> funcionarios = funcionarioRepository.findAll(pageable);
        List<FuncionarioDTO> funcionariosDTO = new ArrayList<>();
        converterListaFuncionarios(funcionarios.getContent(), funcionariosDTO);
       return new PageImpl<>(funcionariosDTO, pageable, funcionarios.getTotalElements());
    }

    private void converterListaFuncionarios(List<FuncionarioEntity> funcionarios, List<FuncionarioDTO> funcionariosDTO) {
        for (FuncionarioEntity funcionarioEntity : funcionarios) {
            FuncionarioDTO funcionarioDTO = FuncionarioDTO.toDTO(funcionarioEntity);
            funcionariosDTO.add(funcionarioDTO);
        }
    }

}