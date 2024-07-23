package br.com.jbst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jbst.DTO.CriarContaResponseDto;
import br.com.jbst.DTO.GetMatriculasDTO;
import br.com.jbst.entities.Curso;
import br.com.jbst.entities.Matriculas;
import br.com.jbst.entities.Usuario;
import br.com.jbst.repositories.CursoRepository;
import br.com.jbst.repositories.EnderecoRepository;
import br.com.jbst.repositories.InstrutorRepository;
import br.com.jbst.repositories.MatriculasRepository;
import br.com.jbst.repositories.TurmasRepository;
import br.com.jbst.repositories.UnidadeDeTreinamentoRepository;
import br.com.jbst.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;


@Service
public class MatriculasServices {


	@Autowired
	MatriculasRepository matriculasRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	InstrutorRepository instrutorRepository;

	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	CursoRepository cursoRepository;

	@Autowired
	TurmasRepository turmasRepository;


	@Autowired
	UnidadeDeTreinamentoRepository unidadeRepository;

	@Autowired
	ModelMapper modelMapper;


	 public List<GetMatriculasDTO> consultarMatriculas() throws Exception {
	        List<Matriculas> matriculas = matriculasRepository.findAllMatriculas();
	        return mapToDTOList(matriculas);
	    }

	    public List<GetMatriculasDTO> consultarMatriculasPessoaFisicaPorMes(int mes) {
	        List<Matriculas> matriculasPessoaFisica = matriculasRepository.findMatriculasPessoaFisicaByMes(mes);
	        return mapToDTOList(matriculasPessoaFisica);
	    }

	    private List<GetMatriculasDTO> mapToDTOList(List<Matriculas> matriculas) {
	        return modelMapper.map(matriculas, new TypeToken<List<GetMatriculasDTO>>() {}.getType());
	    }

	    public byte[] getCursoMaterial(UUID material) {
	        Optional<Curso> cursoOptional = cursoRepository.findById(material);

	        if (cursoOptional.isPresent()) {
	            Curso curso = cursoOptional.get();

	            if (curso.getMaterial() != null) {
	                return curso.getMaterial();
	            } else {
	                throw new RuntimeException("Os dados binários do Curso estão vazios.");
	            }
	        } else {
	            throw new RuntimeException("Material não encontrado para o ID: " + material);
	        }
	    }
	    
	    public byte[] getCursoAvaliacao(UUID avaliacao) {
	        Optional<Curso> cursoOptional = cursoRepository.findById(avaliacao);

	        if (cursoOptional.isPresent()) {
	            Curso curso = cursoOptional.get();

	            if (curso.getAvaliacao() != null) {
	                return curso.getAvaliacao();
	            } else {
	                throw new RuntimeException("Os dados binários do Curso estão vazios.");
	            }
	        } else {
	            throw new RuntimeException("Avaliação não encontrada para o ID: " + avaliacao);
	        }
	    }
	    
	    public byte[] getCursoGabarito(UUID gabarito) {
	        Optional<Curso> cursoOptional = cursoRepository.findById(gabarito);

	        if (cursoOptional.isPresent()) {
	            Curso curso = cursoOptional.get();

	            if (curso.getGabarito() != null) {
	                return curso.getGabarito();
	            } else {
	                throw new RuntimeException("Os dados binários do Curso estão vazios.");
	            }
	        } else {
	            throw new RuntimeException("Gabarito não encontrado para o ID: " + gabarito);
	        }
	    }
	    public List<CriarContaResponseDto> ConsultarTodosOsUsuarios() throws Exception {

			List<Usuario> usuario =usuarioRepository.findAll();
			List<CriarContaResponseDto> lista = modelMapper.map(usuario, new TypeToken<List<CriarContaResponseDto>>() {
			}.getType());
			return lista;
		}
	    @Transactional
	    public List<GetMatriculasDTO> findMatriculasByUsuarioId(UUID usuarioId) {
	        List<Matriculas> matriculas = matriculasRepository.findMatriculasByUsuarioId(usuarioId);
	        return matriculas.stream()
	                .map(matricula -> modelMapper.map(matricula, GetMatriculasDTO.class))
	                .collect(Collectors.toList());
	    }


}
