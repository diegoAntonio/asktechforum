package asktechforum.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Resposta;
import asktechforum.interfaces.CadastroResposta;
import asktechforum.util.ConnectionUtil;
import asktechforum.util.Util;

public class CadastroRespostaDAO implements CadastroResposta {

	private Connection con = null;
	private Util util = null;

	public CadastroRespostaDAO() {
		con = ConnectionUtil.getConnection();
		util = new Util();
	}

	public void adcionarResposta(Resposta resposta) throws SQLException {
		String sql = "insert into RESPOSTA(descricao, idUsuario, idPergunta, data, hora)values(?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			int index = 0;
			stmt.setString(++index, resposta.getDescricao());
			stmt.setInt(++index, resposta.getIdUsuario());
			stmt.setInt(++index, resposta.getIdPergunta());
			stmt.setDate(++index, resposta.getData());
			stmt.setTime(++index, resposta.getHora());

			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}

	}

	public void deletarResposta(int id) throws SQLException {
		String sql = "delete from RESPOSTA where idResposta = " + id;
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
	}

	public Resposta consultarRespostaPorIdResposta(int id) throws SQLException {
		Resposta resposta = new Resposta();

		String sql = "select * from RESPOSTA where idResposta = " + id;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				resposta.setData(rs.getDate("data"));
				resposta.setDescricao(rs.getString("descricao"));
				resposta.setHora(rs.getTime("hora"));
				resposta.setIdPergunta(rs.getInt("idPergunta"));
				resposta.setIdResposta(rs.getInt("idResposta"));
				resposta.setIdUsuario(rs.getInt("idUsuario"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return resposta;
	}

	public ArrayList<Resposta> consultarRespostaPorIdUsuario(int id)
			throws SQLException {

		ArrayList<Resposta> resposta = new ArrayList<Resposta>();

		String sql = "select * from RESPOSTA where idUsuario = " + id
				+ " order by data, hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			resposta = montarLista(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return resposta;
	}

	public ArrayList<Resposta> consultarTodasResposta() throws SQLException {
		ArrayList<Resposta> resposta = new ArrayList<Resposta>();

		String sql = "select * from Resposta order by data, hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			resposta = montarLista(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return resposta;
	}

	public ArrayList<Resposta> consultarRespostaPorPergunta(int id)
			throws SQLException {
		ArrayList<Resposta> resposta = new ArrayList<Resposta>();

//		String sql = "select * from Resposta where idPergunta = " + id
//				+ " order by data, hora";
		
		
		String sql = "SELECT u.nome, r.idResposta, r.descricao, r.idUsuario, r.idPergunta, r.data, r.hora FROM usuario u, resposta r	" +
				"WHERE idPergunta=" + id + " and u.idUsuario = r.idUsuario order by data, hora ";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			
			resposta = montarLista(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return resposta;
	}

	private ArrayList<Resposta> montarLista(ResultSet rs) throws SQLException {

		ArrayList<Resposta> resposta = new ArrayList<Resposta>();

		while (rs.next()) {
			Resposta r = new Resposta();
			r.setData(rs.getDate("data"));
			r.setDescricao(rs.getString("descricao"));
			r.setHora(rs.getTime("hora"));
			r.setIdPergunta(rs.getInt("idPergunta"));
			r.setIdResposta(rs.getInt("idResposta"));
			r.setIdUsuario(rs.getInt("idUsuario"));
			r.setNomeUsuario(rs.getString("nome"));
			resposta.add(r);
		}

		return resposta;

	}

}
