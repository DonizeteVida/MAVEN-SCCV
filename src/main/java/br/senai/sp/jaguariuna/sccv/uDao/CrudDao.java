package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;

import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;

public class CrudDao {

	private Connection conn;

	public CrudDao() {
		conn = ConnectionDB.getConnection();
	}

}
