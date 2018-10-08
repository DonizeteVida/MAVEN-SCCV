package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;
import br.senai.sp.jaguariuna.sccv.utils.StringToMD5;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.entities.UsuarioAdministrador;

public class AdministradorDao {

	private Connection conn;

	public AdministradorDao() {
		conn = ConnectionDB.getConnection();
	}

	public boolean updateUsuarioAdministrador(UsuarioAdministrador a) throws SQLException {

		String sql = "UPDATE usuario_administrador SET nome = ?, email = ?, senha = ?, nif = ?, id_status = ?, WHERE usuario.id = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, a.getNome());
		ps.setString(2, a.getEmail());
		ps.setString(3, StringToMD5.convertStringToMd5(a.getSenha()));
		ps.setString(4, a.getNif());
		ps.setInt(5, a.getStatus().getId());

		return ps.executeUpdate() > 0;
	}

	public boolean inserirUsuarioAdministrador(UsuarioAdministrador a) throws SQLException {

		String sql = "INSERT INTO usuario_administrador(nome, email, senha, idade, nif, status)"
				+ " VALUES(?,?,?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, a.getNome());
		ps.setString(2, a.getEmail());
		ps.setString(3, StringToMD5.convertStringToMd5(a.getSenha()));
		ps.setString(4, a.getNif());
		ps.setInt(5, a.getStatus().getId());

		return ps.executeUpdate() > 0;
	};

	public UsuarioAdministrador buscarAdministradorPorNif(String nif) throws SQLException {
		String sql = "SELECT * FROM usuario_administrador AS ua WHERE ua.nif = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nif);

		ResultSet rs = ps.executeQuery();

		UsuarioAdministrador ua = null;
		if (rs.next()) {
			ua = new UsuarioAdministrador();

			ua.setId(rs.getInt("id"));
			ua.setEmail(rs.getString("email"));
			ua.setNif(rs.getString("nif"));
			ua.setNome(rs.getString("nome"));
			ua.setSenha(rs.getString("senha"));

		}

		return ua;

	}

	public UsuarioAdministrador buscarAdmministradorPorEmail(String email) throws SQLException {
		String sql = "SELECT * FROM usuario_administrador AS ua WHERE ua.email = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email);

		ResultSet rs = ps.executeQuery();

		UsuarioAdministrador ua = null;
		if (rs.next()) {
			ua = new UsuarioAdministrador();

			ua.setId(rs.getInt("id"));
			ua.setEmail(rs.getString("email"));
			ua.setNif(rs.getString("nif"));
			ua.setNome(rs.getString("nome"));
			ua.setSenha(rs.getString("senha"));
		}

		return ua;
	}
	
	public boolean EditarAluno (Usuario u) throws SQLException {
		String sql = "UPDATE usuario SET email = ?, id_status = ?, WHERE usuario.id = ?;";
		
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, u.getEmail());
		ps.setInt(2, u.getStatus().getId());

		return ps.executeUpdate() > 0;
		
	}

}
