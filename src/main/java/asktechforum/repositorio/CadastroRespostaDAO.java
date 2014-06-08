package asktechforum.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Resposta;
import asktechforum.interfaces.CadastroResposta;
import asktechforum.util.ConnectionUtil;

public class CadastroRespostaDAO implements CadastroResposta {

	private Connection con = null;

	public CadastroRespostaDAO() {
	}

	public String adicionarResposta(Resposta resposta) throws SQLException {
		String retorno = "cadastroSucesso";
		String sql = "insert into RESPOSTA(descricao, idUsuario, idPergunta, data, hora)values(?,?,?,?,?)";
		PreparedStatement stmt = null;
		
		try {
			this.con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement(sql);
			int index = 0;
			stmt.setString(++index, resposta.getDescricao());
			stmt.setInt(++index, resposta.getIdUsuario());
			stmt.setInt(++index, resposta.getIdPergunta());
			stmt.setDate(++index, resposta.getData());
			stmt.setTime(++index, resposta.getHora());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			this.con.close();
		}
		return retorno;
	}

	public void deletarResposta(int id) throws SQLException {
		String sql = "delete from RESPOSTA where idResposta = " + id;
		PreparedStatement stmt = null;
		
		try {
			this.con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			this.con.close();
		}
	}

	public Resposta consultarRespostaPorIdResposta(int id) throws SQLException {
		Resposta resposta = new Resposta();

		String sql = "select * from RESPOSTA where idResposta = " + id;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			this.con = ConnectionUtil.getConnection();
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
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			this.con.close();
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
			this.con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Resposta r = new Resposta();
				r.setData(rs.getDate("data"));
				r.setDescricao(rs.getString("descricao"));
				r.setHora(rs.getTime("hora"));
				r.setIdPergunta(rs.getInt("idPergunta"));
				r.setIdResposta(rs.getInt("idResposta"));
				r.setIdUsuario(rs.getInt("idUsuario"));
				resposta.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			this.con.close();
		}

		return resposta;
	}

	public ArrayList<Resposta> consultarTodasRespostas() throws SQLException {
		ArrayList<Resposta> resposta = new ArrayList<Resposta>();

		String sql = "select * from Resposta order by data, hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			this.con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Resposta r = new Resposta();
				r.setData(rs.getDate("data"));
				r.setDescricao(rs.getString("descricao"));
				r.setHora(rs.getTime("hora"));
				r.setIdPergunta(rs.getInt("idPergunta"));
				r.setIdResposta(rs.getInt("idResposta"));
				r.setIdUsuario(rs.getInt("idUsuario"));
				resposta.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			this.con.close();
		}

		return resposta;
	}

	public ArrayList<Resposta> consultarRespostaPorPergunta(int id)
			throws SQLException {
		ArrayList<Resposta> resposta = new ArrayList<Resposta>();		
		
		String sql = "SELECT u.nome, r.idResposta, r.descricao, r.idUsuario, r.idPergunta, r.data, r.hora , r.votos FROM usuario u, resposta r	" +
				"WHERE idPergunta=" + id + " and u.idUsuario = r.idUsuario order by data, hora ";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			this.con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Resposta r = new Resposta();
				r.setData(rs.getDate("data"));
				r.setDescricao(rs.getString("descricao"));
				r.setHora(rs.getTime("hora"));
				r.setIdPergunta(rs.getInt("idPergunta"));
				r.setIdResposta(rs.getInt("idResposta"));
				r.setIdUsuario(rs.getInt("idUsuario"));
				r.setNomeUsuario(rs.getString("nome"));
				r.setVotos(rs.getInt("votos"));
				resposta.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			this.con.close();
		}

		return resposta;
	}
	
	public void adcionarVotoResposta(int id) throws SQLException{
		String sql = "update resposta set votos = votos + 1 where idResposta = ?";
		PreparedStatement stmt = null;
		
		try {
			this.con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement(sql);
			
			int index = 0;
			stmt.setInt(++index, id);	

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			this.con.close();
		}
	}

	@Override
	public String alterarResposta(Resposta resposta) throws SQLException {
		String retorno = "cadastroSucesso";
		String sql = "update RESPOSTA set (descricao=?, idUsuario=?, idPergunta=?, data=?, hora=?) where idResposta = ?";
		PreparedStatement stmt = null;
		
		try {
			this.con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement(sql);
			int index = 0;
			stmt.setString(++index, resposta.getDescricao());
			stmt.setInt(++index, resposta.getIdUsuario());
			stmt.setInt(++index, resposta.getIdPergunta());
			stmt.setDate(++index, resposta.getData());
			stmt.setTime(++index, resposta.getHora());
			stmt.setInt(++index, resposta.getIdResposta());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			this.con.close();
		}
		return retorno;
	}

}
