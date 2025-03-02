package br.com.jbst.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;



@Data
@Entity
@Table(name = "matriculas")
public class Matriculas {

	// Campo 1
	@Id
	@Column(name = "idmatricula")
	private UUID idMatricula;
	
	// Campo 2
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datahoracriacao", nullable = false)
	private Instant dataHoraCriacao;
	
	
	// Campo 3
	@Column(name = "venda", length = 100, nullable = true)
	private String venda;
	
	// Campo 4
	@Column(name = "valor", length = 100, nullable = true)
	private BigDecimal valor;
	
	// Campo 5
    @Column(name = "numeromatricula", nullable = false)
    private Integer numeroMatricula;
	
	// Campo 6
	@Column(name = "status", length = 100, nullable = false)
	private String status;
	
	// Campo 7
	@Column(name = "tipo_de_pagamento", length = 100, nullable = false)
	private String tipo_de_pagamento;
	
	// Campo 8
    @Column(name = "observacoes", length = 1000, nullable = true)
    private String observacoes;
    
	// Campo 9
	@ManyToOne 
	@JoinColumn(name = "id_turmas", nullable = true) 
	private Turmas turmas;
	
	// Campo 10
	@ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "idFuncionario", nullable = true) 
	private Funcionario funcionario;
	
	// Campo 11
	@ManyToOne 
	@JoinColumn(name = "idPessoaFisica", nullable = true) 
	private PessoaFisica pessoafisica;
	
	// Campo 12
	@ManyToOne
	@JoinColumn(name = "idfaturamento", nullable = true)
	private Faturamento faturamento;
	
	// Campo 13
	@ManyToOne 
	@JoinColumn(name = "idfaturamentopf", nullable = true)
	private FaturamentoPf faturamentopf;
	
	// Campo 14
	@ManyToOne 
	@JoinColumn(name = "idpedidos", nullable = true) 
	private Pedidos pedidos;

	 @OneToMany(mappedBy = "matriculas") 
	private List<Evidencias> evidencias;
	 
	 @ManyToMany
	 @JoinTable(
	     name = "usuarios_matricula",
	     joinColumns = @JoinColumn(name = "id_matricula"),
	     inverseJoinColumns = @JoinColumn(name = "id_usuario")
	 )
	 private List<Usuario> usuarios;
		    
		}


