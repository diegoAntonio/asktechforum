package asktechforum.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Tag;
import asktechforum.interfaces.CadastroTag;
import asktechforum.repositorio.CadastroTagDAO;

public class CadastroTagBC implements CadastroTag {

	private CadastroTagDAO cadastro;

	public CadastroTagBC() {
		cadastro = new CadastroTagDAO();
	}

	public void adcionarTag(Tag tag) {
		try {
			if (tag.getNome().isEmpty()) {

			} else {

				cadastro.adcionarTag(tag);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletarTag(int id) {
		try {
			if (id == 0) {

			} else {
				cadastro.deletarTag(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Tag consultarTagId(int id) {
		Tag tag = new Tag();
		try {
			if (id == 0) {

			} else {
				tag = cadastro.consultarTagId(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tag;
	}

	public ArrayList<Tag> consultarTagPorNome(String nome) {

		ArrayList<Tag> tag = new ArrayList<Tag>();
		try {
			if (nome.isEmpty()) {

			} else {
				CadastroTagDAO cadastro = new CadastroTagDAO();
				tag = cadastro.consultarTagPorNome(nome);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tag;

	}

	public ArrayList<Tag> consultarTodasTag() {
		ArrayList<Tag> tag = new ArrayList<Tag>();
		try {
			tag = cadastro.consultarTodasTag();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tag;
	}

}
