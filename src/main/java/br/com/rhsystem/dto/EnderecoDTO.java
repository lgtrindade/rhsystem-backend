package br.com.rhsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoDTO {
    private String logradouro;
    private Long numero;
    private String complemento;
    private String bairro;
    private String cep;
    private CidadeDTO cidade;
}
