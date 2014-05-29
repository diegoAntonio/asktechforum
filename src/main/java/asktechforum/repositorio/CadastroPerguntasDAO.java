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

public class CadastroPerguntasDAO implements CadastroPergunta {

	private Connection con = null;

	public CadastroPerguntasDAO() {
	}
	
	public String adcionarPergunta(Pergunta pergunta) throws SQLException {
		String retorno = "cadastroSucesso";

		String sql = "insert into PERGUNTA(titulo, data, hora, descricao, idUsuario, tag)values(?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		this.con = ConnectionUtil.getConnection();
		try {
			stmt = this.con.prepareStatement(sql);
			int index = 0;
			stmt.setString(++index, pergunta.getTitulo());
			stmt.setDate(++index, pergunta.getData());
			stmt.setTime(++index, pergunta.getHora());
			stmt.setString(++index, pergunta.getDescricao());
			stmt.setInt(++index, pergunta.getIdUsuario());
			stmt.setString(++index, pergunta.getTag());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			this.con.close();
		}
		return retorno;
	}

	public void deletarPergunta(int id) throws SQLException {

		String sql = "delete from PERGUNTA where idPergunta = " + id;
		PreparedStatement stmt = null;
		try {
			this.con = ConnectionUtil.getConnection();
			stmt = this.con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			this.con.close();
		}

	}

	public Pergunta consultarPerguntaPorIdPergunta(int id) throws SQLException {

		Pergunta pergunta = new Pergunta();

		String sql = "select * from PERGUNTA where idPergunta = " + id;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			this.con = ConnectionUtil.getConnection();
			stmt = this.con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				pergunta.setDescricao(rs.getString("descricao"));
				pergunta.setIdPergunta(rs.getInt("idPergunta"));
				pergunta.setTitulo(rs.getString("titulo"));
				pergunta.setIdUsuario(rs.getInt("idUsuario"));
				pergunta.setData(rs.getDate("data"));
				pergunta.setHora(rs.getTime("hora"));
				pergunta.setTag(rs.getString("tag"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			this.con.close();
		}

		return pergunta;
	}

	public ArrayList<Pergunta> consultarPerguntaIdUsuario(int id)
			throws SQLException {
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();

		String sql = "select * from PERGUNTA where idUsuario = " + id
				+ " order by data, hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		this.con = ConnectionUtil.getConnection();
		try {
			stmt = this.con.prepareStatement(sql);

			rs = stmt.executeQuery();

			pergunta = montarLista(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			this.con.close();
		}

		return pergunta;
	}

	public ArrayList<Pergunta> consultarTodasPerguntas() throws SQLException {
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();

		String sql = "select * from PERGUNTA order by data, hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			this.con = ConnectionUtil.getConnection();
			stmt = this.con.prepareStatement(sql);

			rs = stmt.executeQuery();

			pergunta = montarLista(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			this.con.close();
		}

		return pergunta;
	}

	public ArrayList<Pergunta> consultarPerguntaPorData(Date data)
			throws SQLException {
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();

		String sql = "select * from PERGUNTA where idUsuario = " + data
				+ " order by hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			this.con = ConnectionUtil.getConnection();
			stmt = this.con.prepareStatement(sql);

			rs = stmt.executeQuery();

			pergunta = montarLista(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			this.con.close();
		}

		return pergunta;
	}

	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTag(String tag)
			throws SQLException {
		ArrayList<ResultConsultarPergunta> pergunta = new ArrayList<ResultConsultarPergunta>();

		String sql = " select p.descricao, count(r.idResposta) total, u.nome, p.idPergunta, p.titulo, p.data, p.hora " +
				"  from usuario u left join pergunta p on u.idUsuario = p.idUsuario " +
				"		left join resposta r on p.idPergunta = r.idPergunta " +
				"		where p.tag like '%"+ tag +"%'  group by u.nome, p.idPergunta ; ";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			this.con = ConnectionUtil.getConnection();
			stmt = this.con.prepareStatement(sql);

			rs = stmt.executeQuery();

			ResultConsultarPergunta p;
			
			while(rs.next()){
				p = new ResultConsultarPergunta();
				p.setAutor(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setQtdResposta(rs.getInt("total"));
				p.setIdPergunta(rs.getInt("idPergunta"));
				p.setTitulo(rs.getString("titulo"));
				p.setData(rs.getDate("data"));
				p.setHora(rs.getTime("hora"));
				pergunta.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			this.con.close();
		}

		return pergunta;
	}

	
	
	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTodasTags()
			throws SQLException {
		ArrayList<ResultConsultarPergunta> pergunta = new ArrayList<ResultConsultarPergunta>();
		
		String sql = "SELECT p.descricao, COUNT( r.idResposta ) total, u.nome, p.idPergunta, p.titulo, p.tag, p.data, p.hora" +
		" FROM usuario u LEFT JOIN pergunta p ON u.idUsuario = p.idUsuario 	LEFT JOIN resposta r ON p.idPergunta = r.idPergunta " +
		" GROUP BY u.nome, p.idPergunta	ORDER BY p.data DESC LIMIT 0 , 15 ";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			this.con = ConnectionUtil.getConnection();
			stmt = this.con.prepareStatement(sql);

			rs = stmt.executeQuery();

			ResultConsultarPergunta p;
			
			while(rs.next()){
				p = new ResultConsultarPergunta();
				p.setAutor(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setQtdResposta(rs.getInt("total"));
				p.setIdPergunta(rs.getInt("idPergunta"));
				p.setTitulo(rs.getString("titulo"));
				p.setTag(rs.getString("tag"));
				p.setData(rs.getDate("data"));
				p.setHora(rs.getTime("hora"));
				pergunta.add(p);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			this.con.close();
		}

		return pergunta;
	}
	private ArrayList<Pergunta> montarLista(ResultSet rs)
			throws SQLException {
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();

		try {
			this.con = ConnectionUtil.getConnection();
			while (rs.next()) {
				Pergunta p = new Pergunta();
				p.setDescricao(rs.getString("descricao"));
				p.setIdPergunta(rs.getInt("idPergunta"));
				p.setTitulo(rs.getString("titulo"));
				p.setIdUsuario(rs.getInt("idUsuario"));
				p.setData(rs.getDate("data"));
				p.setHora(rs.getTime("hora"));
				p.setTag(rs.getString("tag"));
				pergunta.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			rs.close();
			this.con.close();
		}

		return pergunta;
	}

}
