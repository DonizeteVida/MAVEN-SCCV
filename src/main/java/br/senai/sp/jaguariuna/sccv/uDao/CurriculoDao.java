package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.jaguariuna.sccv.entities.Curriculo;
import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;

public class CurriculoDao {

	private Connection conn;

	public CurriculoDao() {
		conn = ConnectionDB.getConnection();
	}

	public List<Curriculo> listarCurriculo(Integer id_usuario) throws SQLException {
		String sql = "SELECT curriculum_vitae.*, tur.nome AS nomeTurma, cur.nome AS nomeCurso, curriculum_vitae.semestre FROM curriculum_vitae "
				+ "INNER JOIN turma AS tur ON tur.id = curriculum_vitae.id_turma "
				+ "INNER JOIN curso AS cur ON cur.id = curriculum_vitae.id_curso " + "WHERE curriculum_vitae.id_usuario = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id_usuario);

		ResultSet rs = ps.executeQuery();
		List<Curriculo> lista = new ArrayList<Curriculo>();

		while (rs.next()) {
			Curriculo c = new Curriculo();

			c.getTurma().setNome(rs.getString("nomeTurma"));
			c.getCurso().setNome(rs.getString("nomeCurso"));
			c.setData_criacao(rs.getInt("data_criacao"));
			
			lista.add(c);
		}

		return lista;

	}

}
