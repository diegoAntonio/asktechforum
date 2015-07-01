package asktechforum.dominio;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="usuario")
@NamedQueries({@NamedQuery(name="Usuario.email",query="SELECT u FROM Usuario u WHERE u.email like :email"),
			   @NamedQuery(name="Usuario.email_senha",query="SELECT u FROM Usuario u WHERE u.email = :email and u.senha = :senha"),
			   @NamedQuery(name="Usuario.nome",query="SELECT u FROM Usuario u WHERE u.nome like ':nome'")})
public class Usuario {
	public static String JPQL_email = "Usuario.email";
	public static String JPQL_email_senha = "Usuario.email_senha";
	public static String JPQL_nome = "Usuario.nome";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUsuario;
	
	private String nome;
	
	@Column(name="dt_nasc")
	private Date dataNascimento;
	
	private boolean admin;
	
	private String email;
	
	private String localizacao;
	
	private String senha;
	
	@Transient
	private String confSenha;
	
	@Transient
	private String dataString;
	
	@Transient
	private Pergunta pergunta;
	
	public Usuario() {
	}
	
	//GETTERS AND SETTERS
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfSenha() {
		return confSenha;
	}
	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}

	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	
}