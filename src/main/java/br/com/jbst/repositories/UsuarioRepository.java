package br.com.jbst.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbst.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

}
