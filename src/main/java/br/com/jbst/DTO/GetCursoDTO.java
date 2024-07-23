package br.com.jbst.DTO;

import java.time.Instant;

import java.util.UUID;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class GetCursoDTO {
    private UUID idcurso;
	private Instant dataHoraCriacao;
	private Integer codigo;
	private String curso;
	private String modelo_certificado;

	
}
