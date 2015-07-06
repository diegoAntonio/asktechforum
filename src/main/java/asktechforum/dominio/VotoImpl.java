package asktechforum.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Diz pro jpa que voto e uma entidade
@Entity
@Table(name="voto") //diz o nome da tabela em que voto sera mapeado
public class VotoImpl implements Voto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idVoto;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="idResposta")
	private RespostaImpl resposta;
	
	public VotoImpl() {
	}

	@Override
	public int getIdVoto() {
		return idVoto;
	}

	@Override
	public void setIdVoto(int idVoto) {
		this.idVoto = idVoto;
	}


	@Override
	public int getIdUsuario() {
		return this.usuario.getIdUsuario();
	}

	
	@Override
	public void setIdUsuario(int idUsuario) {
		this.usuario.setIdUsuario(idUsuario);
	}

	@Override
	public int getIdResposta() {
		return this.resposta.getIdResposta();
	}

	@Override
	public void setIdResposta(int idResposta) {
		this.resposta.setIdResposta(idResposta);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public RespostaImpl getResposta() {
		return resposta;
	}

	public void setResposta(RespostaImpl resposta) {
		this.resposta = resposta;
	}
}
