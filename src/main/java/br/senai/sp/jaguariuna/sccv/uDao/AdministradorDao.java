package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;
import br.senai.sp.jaguariuna.sccv.utils.StringToMD5;
import br.senai.sp.jaguariuna.sccv.entities.UsuarioAdministrador;


public class AdministradorDao {

	private Connection conn;
	
	public AdministradorDao() {
		conn = ConnectionDB.getConnection();
	}
	
	public boolean updateUsuarioAdministrador (UsuarioAdministrador a ) throws SQLException {
		 
		String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, nif = ?, id_status = ?, WHERE usuario.id = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, a.getNome());
		ps.setString(2, a.getEmail());
		ps.setString(3, StringToMD5.convertStringToMd5(a.getSenha()));
		ps.setString(4,a.getNif() );
		ps.setInt(5, a.getStatus().getId());
		
		return ps.executeUpdate() > 0;
	}
	
	public boolean inserirUsuarioAdministrador(UsuarioAdministrador a) throws SQLException{
		String sql = "INSERT INTO usuario(nome, email, senha, idade, nif, status)"
				+ " VALUES(?,?,?,?,?,?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, a.getNome());
		ps.setString(2, a.getEmail());
		ps.setString(3, StringToMD5.convertStringToMd5(a.getSenha()));
		ps.setString(4,a.getNif() );
		ps.setInt(5, a.getStatus().getId());
		
		return ps.executeUpdate() > 0;
	};
	
	

	
}
