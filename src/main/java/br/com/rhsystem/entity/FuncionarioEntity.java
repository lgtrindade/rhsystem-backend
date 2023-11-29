package br.com.rhsystem.entity;

import br.com.rhsystem.dto.FuncionarioDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
@Table(name = "funcionario")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "data_contratacao")
    private LocalDate dataContratacao;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "cep")
    private String cep;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cidade")
    private String cidade;


    public static FuncionarioEntity toEntity(FuncionarioDTO funcionarioDTO) {
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setNome(funcionarioDTO.getNome());
        funcionarioEntity.setCpf(funcionarioDTO.getCpf());
        funcionarioEntity.setEmail(funcionarioDTO.getEmail());
        funcionarioEntity.setTelefone(funcionarioDTO.getTelefone());
        funcionarioEntity.setCargo(funcionarioDTO.getCargo());
        funcionarioEntity.setDepartamento(funcionarioDTO.getDepartamento());
        funcionarioEntity.setDataContratacao(funcionarioDTO.getDataContratacao());
        funcionarioEntity.setEndereco(funcionarioDTO.getEndereco().getLogradouro());
        funcionarioEntity.setNumero(funcionarioDTO.getEndereco().getNumero());
        funcionarioEntity.setComplemento(funcionarioDTO.getEndereco().getComplemento());
        funcionarioEntity.setBairro(funcionarioDTO.getEndereco().getBairro());
        funcionarioEntity.setCep(funcionarioDTO.getEndereco().getCep());
        funcionarioEntity.setEstado(funcionarioDTO.getEndereco().getCidade().getEstado().getNome());
        funcionarioEntity.setCidade(funcionarioDTO.getEndereco().getCidade().getNome());
        return funcionarioEntity;
    }

}

