package br.com.rhsystem.dto;

import br.com.rhsystem.entity.FuncionarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private EnderecoDTO endereco;

    public static FuncionarioDTO toDTO(FuncionarioEntity funcionarioEntity) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(funcionarioEntity.getId());
        funcionarioDTO.setNome(funcionarioEntity.getNome());
        funcionarioDTO.setCpf(funcionarioEntity.getCpf());
        funcionarioDTO.setEmail(funcionarioEntity.getEmail());
        funcionarioDTO.setTelefone(funcionarioEntity.getTelefone());
        funcionarioDTO.setCargo(funcionarioEntity.getCargo());
        funcionarioDTO.setDepartamento(funcionarioEntity.getDepartamento());
        funcionarioDTO.setDataContratacao(funcionarioEntity.getDataContratacao());
        EstadoDTO estado = EstadoDTO.builder()
                .id(funcionarioEntity.getEstadoId())
                .nome(funcionarioEntity.getEstado())
                .build();
        CidadeDTO cidade = CidadeDTO.builder()
                .id(funcionarioEntity.getCidadeId())
                .nome(funcionarioEntity.getCidade())
                .estado(estado)
                .build();
        EnderecoDTO endereco = EnderecoDTO.builder()
                .logradouro(funcionarioEntity.getEndereco())
                .complemento(funcionarioEntity.getComplemento())
                .bairro(funcionarioEntity.getBairro())
                .numero(funcionarioEntity.getNumero())
                .cep(funcionarioEntity.getCep())
                .cidade(cidade)
                .build();
        funcionarioDTO.setEndereco(endereco);
        return funcionarioDTO;
    }


}
