package br.com.jbst.DTO;


import java.util.UUID;
import lombok.Data;


@Data
public class GetPessoaFisicaDTO {		
    private UUID idpessoafisica;
	private String pessoafisica;
	private String rg;
	private String cpf;


}
