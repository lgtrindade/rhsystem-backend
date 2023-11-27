package br.com.rhsystem.dto;

import br.com.rhsystem.entity.FuncionarioEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FuncionarioDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String cargo;
    private String departamento;
    private LocalDate dataContratacao;
    private String endereco;
    private String complemento;
    private String bairro;
    private String cep;
    private String estado;
    private String cidade;

    public static FuncionarioDTO toDTO(FuncionarioEntity funcionarioEntity) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setNome(funcionarioEntity.getNome());
        funcionarioDTO.setCpf(funcionarioEntity.getCpf());
        funcionarioDTO.setEmail(funcionarioEntity.getEmail());
        funcionarioDTO.setTelefone(funcionarioEntity.getTelefone());
        funcionarioDTO.setCargo(funcionarioEntity.getCargo());
        funcionarioDTO.setDepartamento(funcionarioEntity.getDepartamento());
        funcionarioDTO.setDataContratacao(funcionarioEntity.getDataContratacao());
        funcionarioDTO.setEndereco(funcionarioEntity.getEndereco());
        funcionarioDTO.setComplemento(funcionarioEntity.getComplemento());
        funcionarioDTO.setBairro(funcionarioEntity.getBairro());
        funcionarioDTO.setCep(funcionarioEntity.getCep());
        funcionarioDTO.setEstado(funcionarioEntity.getEstado());
        funcionarioDTO.setCidade(funcionarioEntity.getCidade());

        return funcionarioDTO;
    }


}
