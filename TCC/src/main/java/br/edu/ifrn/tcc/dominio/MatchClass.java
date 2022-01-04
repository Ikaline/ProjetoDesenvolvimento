package br.edu.ifrn.tcc.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MatchClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne (optional = false)
    private Usuario darMatch;

    public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	@ManyToOne (optional = false)
    private Usuario recebeMatch;
    
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

}