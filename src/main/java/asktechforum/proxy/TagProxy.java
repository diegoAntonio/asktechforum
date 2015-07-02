package asktechforum.proxy;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.factory.FabricaDAO;
import asktechforum.factory.FactoryDataBase;
import asktechforum.interfaces.RepositorioPergunta;

/**
 * Classe que implementa o Proxy virtual para o acesso as tags do Forum
 * 
 * @author Barbara
 *
 */
public class TagProxy {

	private static TagProxy instance;
	private ArrayList<String> lista;
	private FabricaDAO fabrica;
	private RepositorioPergunta perguntaDAO;

	private TagProxy() {
		this.fabrica = FactoryDataBase.getInstancia().criarFabrica("JDBC");
		this.perguntaDAO = fabrica.criarDaoPergunta();
		this.lista = new ArrayList<String>();
	}

	public static TagProxy getTagProxy() {
		if (instance == null) {
			instance = new TagProxy();
		}
		return instance;
	}

	public ArrayList<String> consultarTag() throws SQLException{
		if(this.lista == null || this.lista.isEmpty()){
			this.lista = this.perguntaDAO.consultaTodasAsTags();
		}
		return this.lista;
	}
}
