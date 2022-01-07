package br.edu.ifrn.tcc.dominio;

/**
*
* OBJETIVO: essa classe tem o objetivo de guardar a foto do usuario no banco de dados
*
* @author Isadora Kaline Penha da Silva (isadorakalinesilva@gmail.com)
* @author Igor Bruno das Chagas da Fonseca (brunno.chagas.1@gmail.com)
*
* DATA DA CRIACAO: 09/03/2021
################################
* ULTIMA ALTERACAO: 30/12/2021
*
*###############################
*
*/

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Foto {

	/** id para identificar usuario
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** relacionamento de muitos para um
	*/
	@ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Arquivo arquivo;
	
	/** variavel para guardar a legenda da foto do usuario
	*/
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
