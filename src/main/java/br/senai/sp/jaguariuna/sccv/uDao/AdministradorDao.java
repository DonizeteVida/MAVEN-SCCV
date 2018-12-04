package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;
import br.senai.sp.jaguariuna.sccv.utils.StringToMD5;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.entities.UsuarioAdministrador;

public class AdministradorDao {

	private Connection conn;

	public AdministradorDao() {
		conn = ConnectionDB.getConnection();
	}

	public boolean updateUsuarioAdministrador(UsuarioAdministrador a, Boolean controle) throws SQLException {

		String sql = "UPDATE usuario_administrador as ua SET nome = ?, email = ?, senha = ?, nif = ?, id_status = ?, _super = ? WHERE ua.id = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, a.getNome());
		ps.setString(2, a.getEmail());
		ps.setString(3, a.getSenha());
		if (controle) {
			ps.setString(3, StringToMD5.convertStringToMd5(a.getSenha()));
		}
		ps.setString(4, a.getNif());
		ps.setInt(5, a.getStatus().getId());
		ps.setInt(6, a.get_super());
		ps.setInt(7, a.getId());

		return ps.executeUpdate() > 0;
	}

	public boolean inserirUsuarioAdministrador(UsuarioAdministrador a) throws SQLException {

		String sql = "INSERT INTO usuario_administrador(nome, email, senha, nif, _super)" + " VALUES(?,?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, a.getNome());
		ps.setString(2, a.getEmail());
		ps.setString(3, StringToMD5.convertStringToMd5(a.getSenha()));
		ps.setString(4, a.getNif());
		ps.setInt(5, a.get_super());

		return ps.executeUpdate() > 0;
	};

	public UsuarioAdministrador buscarAdministradorPorNif(String nif) throws SQLException {
		String sql = "SELECT ua.*, s.nome AS nomeStatus FROM usuario_administrador AS ua"
				+ " INNER JOIN status_ AS s ON s.id = ua.id_status" + " WHERE ua.nif = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nif);

		ResultSet rs = ps.executeQuery();

		UsuarioAdministrador u = null;
		if (rs.next()) {
			u = new UsuarioAdministrador();

			u.setId(rs.getInt("id"));
			u.setNome(rs.getString("nome"));
			u.set_super(rs.getInt("_super"));
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			u.setNif(rs.getString("nif"));
			u.getStatus().setId(rs.getInt("id_status"));
			u.getStatus().setNome(rs.getString("nomeStatus"));

		}

		return u;

	}

	public UsuarioAdministrador buscarAdmministradorPorEmail(String email) throws SQLException {
		String sql = "SELECT ua.*, s.nome AS nomeStatus FROM usuario_administrador AS ua"
				+ " INNER JOIN status_ AS s ON s.id = ua.id_status" + " WHERE ua.email = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email);

		ResultSet rs = ps.executeQuery();

		UsuarioAdministrador u = null;
		if (rs.next()) {
			u = new UsuarioAdministrador();

			u.setId(rs.getInt("id"));
			u.setNome(rs.getString("nome"));
			u.set_super(rs.getInt("_super"));
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			u.setNif(rs.getString("nif"));
			u.getStatus().setId(rs.getInt("id_status"));
			u.getStatus().setNome(rs.getString("nomeStatus"));

		}

		return u;
	}

	public List<UsuarioAdministrador> listarAdministrador() throws SQLException {

		String sql = "SELECT u.*, s.nome AS nomeStatus FROM usuario_administrador AS u "
				+ "INNER JOIN status_ AS s ON s.id = u.id_status";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<UsuarioAdministrador> lista = new ArrayList<UsuarioAdministrador>();

		while (rs.next()) {
			UsuarioAdministrador u = new UsuarioAdministrador();

			u.setId(rs.getInt("id"));
			u.setNome(rs.getString("nome"));
			u.set_super(rs.getInt("_super"));
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			u.setNif(rs.getString("nif"));
			u.getStatus().setId(rs.getInt("id_status"));
			u.getStatus().setNome(rs.getString("nomeStatus"));

			lista.add(u);
		}

		return lista;
	}

	public List<UsuarioAdministrador> listarAdministrador(String nome) throws SQLException {

		String sql = "SELECT u.*, s.nome AS nomeStatus FROM usuario_administrador AS u"
				+ " INNER JOIN status_ AS s ON s.id = u.id_status" + " WHERE u.nome LIKE ?;";

		String local = nome + "%";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, local);

		ResultSet rs = ps.executeQuery();

		List<UsuarioAdministrador> lista = new ArrayList<UsuarioAdministrador>();

		while (rs.next()) {
			UsuarioAdministrador u = new UsuarioAdministrador();

			u.setId(rs.getInt("id"));
			u.setNome(rs.getString("nome"));
			u.set_super(rs.getInt("_super"));
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			u.setNif(rs.getString("nif"));
			u.getStatus().setId(rs.getInt("id_status"));
			u.getStatus().setNome(rs.getString("nomeStatus"));

			lista.add(u);
		}

		return lista;
	}

	public List<UsuarioAdministrador> listarAdministradorPorNIF(String nome) throws SQLException {

		String sql = "SELECT u.*, s.nome AS nomeStatus FROM usuario_administrador AS u"
				+ " INNER JOIN status_ AS s ON s.id = u.id_status" + " WHERE u.nif LIKE ?;";

		String local = nome + "%";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, local);

		ResultSet rs = ps.executeQuery();

		List<UsuarioAdministrador> lista = new ArrayList<UsuarioAdministrador>();

		while (rs.next()) {
			UsuarioAdministrador u = new UsuarioAdministrador();

			u.setId(rs.getInt("id"));
			u.setNome(rs.getString("nome"));
			u.set_super(rs.getInt("_super"));
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			u.setNif(rs.getString("nif"));
			u.getStatus().setId(rs.getInt("id_status"));
			u.getStatus().setNome(rs.getString("nomeStatus"));

			lista.add(u);
		}

		return lista;
	}

	public boolean EditarAluno(Usuario u) throws SQLException {
		String sql = "UPDATE usuario SET email = ?, id_status = ?, WHERE usuario.id = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, u.getEmail());
		ps.setInt(2, u.getStatus().getId());

		return ps.executeUpdate() > 0;

	}

}
