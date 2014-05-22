package asktechforum.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Pergunta;
import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.interfaces.CadastroPergunta;
import asktechforum.util.ConnectionUtil;
import asktechforum.util.Util;

public class CadastroPerguntasDAO implements CadastroPergunta {

	private Connection con;
	private Util util;

	public CadastroPerguntasDAO() {

		util = new Util();
	}

	public void adcionarPergunta(Pergunta pergunta) throws SQLException {

		con = ConnectionUtil.getConnection();
		String sql = "insert into PERGUNTA(titulo, data, hora, descricao, idUsuario, tag)values(?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			int index = 0;
			stmt.setString(++index, pergunta.getTitulo());
			stmt.setDate(++index, pergunta.getData());
			stmt.setTime(++index, pergunta.getHora());
			stmt.setString(++index, pergunta.getDescricao());
			stmt.setInt(++index, pergunta.getUsuario());
			stmt.setString(++index, pergunta.getTag());

			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}

	}

	public void deletarPergunta(int id) throws SQLException {

		con = ConnectionUtil.getConnection();
		String sql = "delete from PERGUNTA where idPergunta = " + id;
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

	public Pergunta consultarPerguntaPorIdPergunta(int id) throws SQLException {

		con = ConnectionUtil.getConnection();
		Pergunta pergunta = new Pergunta();

		String sql = "select * from PERGUNTA where idPergunta = " + id;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				pergunta.setDescricao(rs.getString("descricao"));
				pergunta.setIdPergunta(rs.getInt("idPergunta"));
				pergunta.setTitulo(rs.getString("titulo"));
				pergunta.setUsuario(rs.getInt("idUsuario"));
				pergunta.setData(rs.getDate("data"));
				pergunta.setHora(rs.getTime("hora"));
				pergunta.setTag(rs.getString("tag"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return pergunta;
	}

	public ArrayList<Pergunta> consultarPerguntaIdUsuario(int id)
			throws SQLException {
		con = ConnectionUtil.getConnection();
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();

		String sql = "select * from PERGUNTA where idUsuario = " + id
				+ " order by data, hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			pergunta = montarLista(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return pergunta;
	}

	public ArrayList<Pergunta> consultarTodasPerguntas() throws SQLException {
		con = ConnectionUtil.getConnection();
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();

		String sql = "select * from PERGUNTA order by data, hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			pergunta = montarLista(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return pergunta;
	}

	public ArrayList<Pergunta> consultarPerguntaPorData(Date data)
			throws SQLException {
		con = ConnectionUtil.getConnection();
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();

		String sql = "select * from PERGUNTA where idUsuario = " + data
				+ " order by hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			pergunta = montarLista(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return pergunta;
	}

	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTag(String tag)
			throws SQLException {
		con = ConnectionUtil.getConnection();
		ArrayList<ResultConsultarPergunta> pergunta = new ArrayList<ResultConsultarPergunta>();

		String sql = "select p.descricao, count(r.idResposta)  total, u.nome from pergunta p, resposta r, usuario u " +
		" where u.idUsuario = p.idUsuario " +
		" and p.idPergunta = r.idPergunta " +
		" and p.tag like '%" + tag + "%' "+ 
		" group by u.nome, p.idPergunta; ";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			ResultConsultarPergunta p;
			
			while(rs.next()){
				p = new ResultConsultarPergunta();
				p.setAutor(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setQtdResposta(rs.getInt("total"));
				pergunta.add(p);
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
			con = null;
		}

		return pergunta;
	}

	private ArrayList<Pergunta> montarLista(ResultSet rs) throws SQLException {
		con = ConnectionUtil.getConnection();
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();

		while (rs.next()) {
			Pergunta p = new Pergunta();
			p.setDescricao(rs.getString("descricao"));
			p.setIdPergunta(rs.getInt("idPergunta"));
			p.setTitulo(rs.getString("titulo"));
			p.setUsuario(rs.getInt("usuario"));
			p.setData(rs.getDate("data"));
			p.setHora(rs.getTime("hora"));
			p.setTag(rs.getString("tag"));
			pergunta.add(p);
		}

		return pergunta;
	}

}
