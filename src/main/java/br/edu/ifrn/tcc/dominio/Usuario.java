package br.edu.ifrn.tcc.dominio;

/**
*
* OBJETIVO: essa classe tem o objetivo de guardar o usuario no banco de dados
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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**diferencia usuario administrador de usuario comum para acessa a pagina
	*/
	public static final String ADMIN = "ADMIN";
	public static final String USUARIO_COMUM = "COMUM";
	
	// id para identificar usuario
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** no projeto, essa variavel é a afinidade do usuario. Nao fica salvo no banco de dados.
	*/
	@Transient
	private int afinidade;
	
	/** nome do usuario
	*/
	@Column(nullable = false)
	@NotBlank(message = "Preencha campo nome!")
	@Size (min = 4, message = "Nome deve ter no min. 4 caracteres!")
	private String nome;
	
	/**email do usuario
	*/
	@Column(nullable = false)
	@NotBlank(message = "Preencha o campo email!")
	private String email;
	
	/** senha do usuario
	*/
	@Column(nullable = false)
	@NotBlank(message = "Preencha o campo senha!")
	@Size (min = 6, message = "Senha deve ter no min. 6 caracteres!")
	private String senha;
	
	/** confirmacao de senha do usuario
	*/
	@Transient
	@Column(nullable = false)
	private String confirm;
	
	/** dia da data de nascimento do usuario
	*/
	@Column(nullable = false)
	@NotBlank(message = "Preencha o campo dia!")
	private String dia;
	
	/** mes de nascimento do usuario
	*/
	@Column(nullable = false)
	@NotBlank(message = "Preencha o campo mes!")
	private String mes;
	
	/** ano de nascimento do usuario
	*/
	@Column(nullable = false)
	@NotBlank(message = "Preencha o campo ano!")
	private String ano;
	
	/** genero do usuario
	*/
	@Column(nullable = false)
	@NotBlank(message = "Preencha o campo sexo!")
	private String sexo;
	
	/** telefone para contato do usuario
	*/
	@Column(nullable = false)
	@Size (min = 11, message = "Nome deve ter no min. 11 caracteres!")
	@NotBlank(message = "Preencha campo telefone!")
	private String telefone;
	
	/** campus em que o usuario estuda
	*/
	@Column(nullable = false)
	@NotBlank(message = "Preencha campo campus!")
	private String campus;
	
	/** biografia do usuario
	*/
	private String bio;
	
	/** interesses/gostos do usuario
	*/
	private String interesseDanca;
	
	private String interesseArte;
	
	private String interessePolitica;
	
	private String interesseFutebol;
	
	private String interesseFestas;
	
	private String interesseMusica;
	
	private String interesseEsportes;
	
	private String interesseComedia;
	
	private String interesseFilmes;
	
	private String interesseSeries;
	
	private String interesseAnimes;
	
	private String interesseCozinhar;
	
	private String interesseViagens;
	
	private String interesseEstudar;
	
	private String m1;
	
	@Column(nullable = false)
	private String perfil = USUARIO_COMUM;
	
	/** relacionamento um para um
	*/
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Arquivo foto;
	
	/** relacionamento um para muitos
	*/
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Foto> publiFoto;
	
	/** relacionamento um para muitos
	*/
	@OneToMany(mappedBy = "darMatch")
	private List<MatchClass> darmatch ;
	
	/** relacionamento um para muitos
	*/
	@OneToMany(mappedBy = "recebeMatch")
	private List<MatchClass> recebematch;
	
	/** atributo do tipo matchClass
	*/
	@Transient
	private List<MatchClass> matchesMutuos;
	
	public int getId() {
		return id;
	}
	
	public Arquivo getFoto() {
		return foto;
	}
	public void setFoto(Arquivo foto) {
		this.foto = foto;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getInteresseDanca() {
		return interesseDanca;
	}

	public void setInteresseDanca(String interesseDanca) {
		this.interesseDanca = interesseDanca;
	}

	public String getInteresseArte() {
		return interesseArte;
	}

	public void setInteresseArte(String interesseArte) {
		this.interesseArte = interesseArte;
	}

	public String getInteressePolitica() {
		return interessePolitica;
	}

	public void setInteressePolitica(String interessePolitica) {
		this.interessePolitica = interessePolitica;
	}

	public String getInteresseFutebol() {
		return interesseFutebol;
	}

	public void setInteresseFutebol(String interesseFutebol) {
		this.interesseFutebol = interesseFutebol;
	}

	public String getInteresseFestas() {
		return interesseFestas;
	}

	public void setInteresseFestas(String interesseFestas) {
		this.interesseFestas = interesseFestas;
	}

	public String getInteresseMusica() {
		return interesseMusica;
	}

	public void setInteresseMusica(String interesseMusica) {
		this.interesseMusica = interesseMusica;
	}

	public String getInteresseEsportes() {
		return interesseEsportes;
	}

	public void setInteresseEsportes(String interesseEsportes) {
		this.interesseEsportes = interesseEsportes;
	}

	public String getInteresseComedia() {
		return interesseComedia;
	}

	public void setInteresseComedia(String interesseComedia) {
		this.interesseComedia = interesseComedia;
	}

	public String getInteresseFilmes() {
		return interesseFilmes;
	}

	public void setInteresseFilmes(String interesseFilmes) {
		this.interesseFilmes = interesseFilmes;
	}

	public String getInteresseSeries() {
		return interesseSeries;
	}

	public void setInteresseSeries(String interesseSeries) {
		this.interesseSeries = interesseSeries;
	}

	public String getInteresseAnimes() {
		return interesseAnimes;
	}

	public void setInteresseAnimes(String interesseAnimes) {
		this.interesseAnimes = interesseAnimes;
	}

	public String getInteresseCozinhar() {
		return interesseCozinhar;
	}

	public void setInteresseCozinhar(String interesseCozinhar) {
		this.interesseCozinhar = interesseCozinhar;
	}

	public String getInteresseViagens() {
		return interesseViagens;
	}

	public void setInteresseViagens(String interesseViagens) {
		this.interesseViagens = interesseViagens;
	}

	public String getInteresseEstudar() {
		return interesseEstudar;
	}

	public void setInteresseEstudar(String interesseEstudar) {
		this.interesseEstudar = interesseEstudar;
	}

	public List<Foto> getPubliFoto() {
		return publiFoto;
	}

	public void setPubliFoto(List<Foto> publiFoto) {
		this.publiFoto = publiFoto;
	}

	public int getAfinidade() {
		return afinidade;
	}

	public void setAfinidade(int afinidade) {
		this.afinidade = afinidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}
	
}
