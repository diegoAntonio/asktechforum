package asktechforum.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Tag;

public interface CadastroTag {
	public void adcionarTag(Tag tag) throws SQLException;
	public void deletarTag(int id) throws SQLException;
	public Tag consultarTagId(int id) throws SQLException;
	public ArrayList<Tag> consultarTodasTag() throws SQLException;
	public ArrayList<Tag> consultarTagPorNome(String nome) throws SQLException;
}
