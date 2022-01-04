package br.edu.ifrn.tcc.dominio;

//OBJETIVO: essa classe tem o objetivo de guardar o arquivo da foto do usuario no banco de dados

//AUTORES: Isadora Kaline Penha da Silva (isadorakalinesilva@gmail.com)
//		   Igor Bruno das Chagas da Fonseca (brunno.chagas.1@gmail.com)

//DATA DA CRIACAO: 09/03/2021
//ULTIMA ALTERACAO: 30/12/2021

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Arquivo {
	
	// id para identificar usuario
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//variavel que guarda o nome do arquivo
	@Column(nullable = false)
	private String nomeArquivo;
	
	//variavel que guarda o tipo do arquivo
	private String tipoArquivo;
	
	//variavel que guarda os dados do arquivo
	@Lob
	@Basic(fetch =FetchType.LAZY)
	private byte[] dados;

	public Arquivo(Long id, String nomeArquivo, String tipoArquivo, byte[] dados) {
		super();
		this.id = id;
		this.nomeArquivo = nomeArquivo;
		this.tipoArquivo = tipoArquivo;
		this.dados = dados;
	}

	public Arquivo() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}
}
