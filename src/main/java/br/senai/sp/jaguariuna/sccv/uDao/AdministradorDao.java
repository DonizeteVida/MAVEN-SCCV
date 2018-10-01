package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;
import br.senai.sp.jaguariuna.sccv.utils.StringToMD5;
import br.senai.sp.jaguariuna.sccv.entities.UsuarioAdministrador;

public class AdministradorDao {

	private Connection conn;

	public AdministradorDao() {
		conn = ConnectionDB.getConnection();
	}

	public boolean updateUsuarioAdministrador(UsuarioAdministrador a) throws SQLException {

<<<<<<< HEAD
		String sql = "UPDATE usuario_administrador SET nome = ?, email = ?, senha = ?, nif = ?, id_status = ?, WHERE usuario.id = ?;";
=======
		String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, nif = ?, id_status = ?, WHERE usuario.id = ?;";
>>>>>>> 5ffda01bc0fee6c1b89dee1b7548b7de9cccff76

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, a.getNome());
		ps.setString(2, a.getEmail());
		ps.setString(3, StringToMD5.convertStringToMd5(a.getSenha()));
		ps.setString(4, a.getNif());
		ps.setInt(5, a.getStatus().getId());

		return ps.executeUpdate() > 0;
	}

	public boolean inserirUsuarioAdministrador(UsuarioAdministrador a) throws SQLException {
<<<<<<< HEAD
		String sql = "INSERT INTO usuario_administrador(nome, email, senha, idade, nif, status)"
				+ " VALUES(?,?,?,?,?,?)";
=======
		String sql = "INSERT INTO usuario(nome, email, senha, idade, nif, status)" + " VALUES(?,?,?,?,?,?)";
>>>>>>> 5ffda01bc0fee6c1b89dee1b7548b7de9cccff76

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, a.getNome());
		ps.setString(2, a.getEmail());
		ps.setString(3, StringToMD5.convertStringToMd5(a.getSenha()));
		ps.setString(4, a.getNif());
		ps.setInt(5, a.getStatus().getId());

		return ps.executeUpdate() > 0;
	};

<<<<<<< HEAD
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
=======
//	public AdministradorDao buscarAdministradorPorNif(String nif) throws SQLException{
//		String sql = "SELECT tudo = ?, WHERE a.nif = ?;";
//				
//		PreparedStatement ps = 	conn.prepareStatement(sql);
////		ps.setString(4,getNif(nif));
//		
//		ResultSet rs = ps.executeQuery();
//		
//		AdministradorDao a = null;
//		if(rs.next()) {
//			a = new AdministradorDao();
//			
//			Calendar c = Calendar.getInstance();
//			c.setTimeInMillis(rs.getLong("idade"));
//			
//			
//		}
//		
//		
//		
//	}

	public AdministradorDao buscarAdministradorPorNif(String nif) throws SQLException {
		String sql = "SELECT tudo = ?, WHERE a.nif = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);
		// ps.setString(4,getNif(nif));

		ResultSet rs = ps.executeQuery();

		AdministradorDao a = null;
		if (rs.next()) {
			a = new AdministradorDao();

			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(rs.getLong("idade"));

		}

		return a;

	}

>>>>>>> 5ffda01bc0fee6c1b89dee1b7548b7de9cccff76
}
