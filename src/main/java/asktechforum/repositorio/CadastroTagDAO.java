package asktechforum.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Tag;
import asktechforum.interfaces.CadastroTag;
import asktechforum.util.ConnectionUtil;

public class CadastroTagDAO implements CadastroTag{

	private Connection con = null;

	public CadastroTagDAO() {
		con = ConnectionUtil.getConnection();
	}

	public void adcionarTag(Tag tag) throws SQLException {

		String sql = "insert into TAG(idTag, nome) values (?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			int index = 0;
			stmt.setInt(++index, tag.getIdTag());
			stmt.setString(++index, tag.getNome());
			stmt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}

	}

	public void deletarTag(int id) throws SQLException {

		String sql = "delete from TAG where idTag = " + id;
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}

	}

	public Tag consultarTagId(int id) throws SQLException {
		Tag tag = new Tag();

		String sql = "select * from TAG where idTag = " + id;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				tag.setIdTag(rs.getInt("idTag"));
				tag.setNome(rs.getString("nome"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			rs.close();
			con.close();
		}

		return tag;

	}

	public ArrayList<Tag> consultarTodasTag() throws SQLException {
		ArrayList<Tag> tag = new ArrayList<Tag>();

		String sql = "select * from TAG order by nome";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			tag = montarLista(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			rs.close();
			con.close();
		}

		return tag;

	}

	public ArrayList<Tag> consultarTagPorNome(String nome) throws SQLException {
		ArrayList<Tag> tag = new ArrayList<Tag>();

		String sql = "select * from TAG where nome like %" + nome + "%";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			tag = montarLista(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			rs.close();
			con.close();
		}

		return tag;

	}

	private ArrayList<Tag> montarLista(ResultSet rs) throws SQLException {
		ArrayList<Tag> tag = new ArrayList<Tag>();

		while (rs.next()) {
			Tag t = new Tag();
			t.setIdTag(rs.getInt("idTag"));
			t.setNome(rs.getString("nome"));
			tag.add(t);
		}

		return tag;
	}

}
