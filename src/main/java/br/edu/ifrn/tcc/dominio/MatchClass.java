package br.edu.ifrn.tcc.dominio;

//OBJETIVO: essa classe tem o objetivo de guardar o match do usuario no banco de dados

//AUTORES: Isadora Kaline Penha da Silva (isadorakalinesilva@gmail.com)
//		   Igor Bruno das Chagas da Fonseca (brunno.chagas.1@gmail.com)

//DATA DA CRIACAO: 09/03/2021
//ULTIMA ALTERACAO: 30/12/2021

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class MatchClass {

	// id para identificar usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   
    //variavel que guarda os matches mutuos (matche dado e recebido a mesma pessoa)
    @Transient
    private Usuario matchesMutuos;

    // relacionamento de muitos para um
    @ManyToOne (optional = false)
    private Usuario darMatch;

    public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	// relacionamento de muitos para um
	@ManyToOne (optional = false)
    private Usuario recebeMatch;
    
	// variavel que guarda os matches aprovados
    private boolean aprovado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getDarMatch() {
        return darMatch;
    }

    public void setDarMatch(Usuario darMatch) {
        this.darMatch = darMatch;
    }

    public Usuario getRecebeMatch() {
        return recebeMatch;
    }

    public void setRecebeMatch(Usuario recebeMatch) {
        this.recebeMatch = recebeMatch;
    }

	public Usuario getMatchesMutuos() {
		return matchesMutuos;
	}

	public void setMatchesMutuos(Usuario matchesMutuos) {
		this.matchesMutuos = matchesMutuos;
	}

}