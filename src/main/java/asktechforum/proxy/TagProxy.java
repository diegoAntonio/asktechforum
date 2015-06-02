package asktechforum.proxy;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.repositorio.CadastroPerguntasDAO;

public class TagProxy {

	private static TagProxy instance;
	private ArrayList<String> lista;
	private CadastroPerguntasDAO cPDAO;

	private TagProxy() {
		super();
	}

	public static TagProxy getTagProxy() {
		if (instance == null) {
			instance = new TagProxy();
		}
		return instance;
	}

	public ArrayList<String> consultarTag() throws SQLException{
		if(this.lista != null){
			this.lista = cPDAO.consultaTodasAsTags();
		}
		return this.lista;
	}
}
