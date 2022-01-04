package br.edu.ifrn.tcc.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Foto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Arquivo arquivo;
	
	private String legenda;
	
	public Foto(Long id, Arquivo arquivo, String legenda) {
		super();
		this.id = id;
		this.arquivo = arquivo;
		this.legenda = legenda;
	}

	public Foto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}
	
}
