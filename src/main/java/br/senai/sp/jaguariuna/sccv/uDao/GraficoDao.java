package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.jaguariuna.sccv.entities.ClasseGenericaGrafico;
import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;

public class GraficoDao {

	private Connection conn;

	public GraficoDao() {
		conn = ConnectionDB.getConnection();
	}

	public List<ClasseGenericaGrafico> quantideCategoria() throws SQLException {
		String sql = "SELECT c.nome, COUNT(*) as qtde " + " FROM curriculum_vitae cv "
				+ " INNER JOIN categoria c ON cv.id_categoria = c.id " + " GROUP BY c.id";

		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<ClasseGenericaGrafico> classeGenericaGraficos = new ArrayList<>();

		while (rs.next()) {
			ClasseGenericaGrafico cg = new ClasseGenericaGrafico();

			cg.setNome(rs.getString("nome"));
			cg.setValor(rs.getDouble("qtde"));

			classeGenericaGraficos.add(cg);
		}

		return classeGenericaGraficos;

	}

}
