package br.com.jbst.DTO;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;


@Data
public class GetEvidenciaDTO {
	private UUID idEvidencias;
	private Instant dataHoraCriacao;
	private String nome;
	private String descricao;	
}
