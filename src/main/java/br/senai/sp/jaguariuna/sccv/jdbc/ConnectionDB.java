package br.senai.sp.jaguariuna.sccv.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private static Connection con;
	private static String dns = "jdbc:mysql://127.0.0.1:3306/SCCV?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String user = "root";
	private static String pass = "root";

	public static Connection getConnection() {
		try {
			if (con == null || con.isClosed()) {
				getGet();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	private static void getGet() {
		try {
			DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
			con = DriverManager.getConnection(dns, user, pass);
			System.out.println("DB conectado com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao conectar no DB");
			e.printStackTrace();
		}
	}
}
