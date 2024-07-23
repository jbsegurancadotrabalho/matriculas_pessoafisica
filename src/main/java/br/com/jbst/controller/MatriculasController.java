package br.com.jbst.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbst.DTO.CriarContaResponseDto;
import br.com.jbst.DTO.GetMatriculasDTO;
import br.com.jbst.services.MatriculasServices;

@RestController
@RequestMapping("/api/matriculas-pessoafisica")
public class MatriculasController {

	@Autowired
	MatriculasServices matriculasService;

	@GetMapping("/consultar-matriculas")
	public ResponseEntity<List<GetMatriculasDTO>> consultarMatriculas() throws Exception {
		List<GetMatriculasDTO> matriculas = matriculasService.consultarMatriculas();
		return ResponseEntity.status(HttpStatus.OK).body(matriculas);
	}

	@GetMapping("/consultar-matriculas-pessoafisica")
	public ResponseEntity<List<GetMatriculasDTO>> consultarMatriculasPessoaFisicaPorMes(
			@RequestParam(name = "mes", required = false, defaultValue = "1") int mes) {
		List<GetMatriculasDTO> matriculasPessoaFisica = matriculasService.consultarMatriculasPessoaFisicaPorMes(mes);
		return ResponseEntity.status(HttpStatus.OK).body(matriculasPessoaFisica);
	}

	@GetMapping("/download-material/{material}")
	public ResponseEntity<byte[]> downloadMaterial(@PathVariable UUID material) {
		try {
			byte[] CursoMaterial = matriculasService.getCursoMaterial(material);

			// Configurar os cabeçalhos da resposta, por exemplo, o tipo de conteúdo
			// e o nome do arquivo se necessário
			// HttpHeaders headers = new HttpHeaders();
			// headers.setContentType(MediaType.APPLICATION_PDF);
			// headers.setContentDispositionFormData("attachment", "proficiencia.pdf");

			return ResponseEntity.status(HttpStatus.OK)
					// .headers(headers)
					.body(CursoMaterial);
		} catch (Exception e) {
			// Lidar com exceções, por exemplo, proficiência não encontrada ou dados vazios
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/download-avaliacao/{avaliacao}")
	public ResponseEntity<byte[]> downloadAvaliacao(@PathVariable UUID avaliacao) {
		try {
			byte[] CursoAvaliacao = matriculasService.getCursoAvaliacao(avaliacao);

			// Configurar os cabeçalhos da resposta, por exemplo, o tipo de conteúdo
			// e o nome do arquivo se necessário
			// HttpHeaders headers = new HttpHeaders();
			// headers.setContentType(MediaType.APPLICATION_PDF);
			// headers.setContentDispositionFormData("attachment", "proficiencia.pdf");

			return ResponseEntity.status(HttpStatus.OK)
					// .headers(headers)
					.body(CursoAvaliacao);
		} catch (Exception e) {
			// Lidar com exceções, por exemplo, proficiência não encontrada ou dados vazios
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/download-gabarito/{gabarito}")
	public ResponseEntity<byte[]> downloadGabarito(@PathVariable UUID gabarito) {
		try {
			byte[] CursoGabarito = matriculasService.getCursoGabarito(gabarito);

			// Configurar os cabeçalhos da resposta, por exemplo, o tipo de conteúdo
			// e o nome do arquivo se necessário
			// HttpHeaders headers = new HttpHeaders();
			// headers.setContentType(MediaType.APPLICATION_PDF);
			// headers.setContentDispositionFormData("attachment", "proficiencia.pdf");

			return ResponseEntity.status(HttpStatus.OK)
					// .headers(headers)
					.body(CursoGabarito);
		} catch (Exception e) {
			// Lidar com exceções, por exemplo, proficiência não encontrada ou dados vazios
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("consultar-usuarios")
	public ResponseEntity<List<CriarContaResponseDto>> consultarTodosUsuarios() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(matriculasService.ConsultarTodosOsUsuarios());

	}
	
	 @GetMapping("/usuario/{usuarioId}")
	    public ResponseEntity<List<GetMatriculasDTO>> getMatriculasByUsuarioId(@PathVariable UUID usuarioId) {
	        List<GetMatriculasDTO> matriculas = matriculasService.findMatriculasByUsuarioId(usuarioId);
	        return ResponseEntity.ok(matriculas);
	    }
}
