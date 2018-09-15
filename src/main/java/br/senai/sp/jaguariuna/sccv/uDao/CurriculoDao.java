package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;

public class CurriculoDao {

	private Connection conn;

	public CurriculoDao() {
		conn = ConnectionDB.getConnection();
	}

	public boolean criarCurriculo(CurriculumVitae c) throws SQLException {
		String sql = "INSERT INTO curriculum_vitae(data_criacao, id_curso, id_turma, semestre, id_usuario) VALUES (?, ?, ?, ?, ?);";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, Calendar.getInstance().getTimeInMillis());
		ps.setInt(2, c.getCurso().getId());
		ps.setInt(3, c.getTurma().getId());
		ps.setInt(4, c.getSemestre());
		ps.setInt(5, c.getUsuario().getId());

		return ps.executeUpdate() > 0;
	}

	public List<CurriculumVitae> listarCurriculo(Integer id_usuario) throws SQLException {
		String sql = "SELECT curriculum_vitae.*, tur.nome AS nomeTurma, cur.nome AS nomeCurso, curriculum_vitae.semestre FROM curriculum_vitae "
				+ "INNER JOIN turma AS tur ON tur.id = curriculum_vitae.id_turma "
				+ "INNER JOIN curso AS cur ON cur.id = curriculum_vitae.id_curso "
				+ "WHERE curriculum_vitae.id_usuario = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id_usuario);

		ResultSet rs = ps.executeQuery();
		List<CurriculumVitae> lista = new ArrayList<CurriculumVitae>();

		while (rs.next()) {
			CurriculumVitae c = new CurriculumVitae();

			c.getTurma().setNome(rs.getString("nomeTurma"));
			c.getCurso().setNome(rs.getString("nomeCurso"));
			c.setData_criacao(rs.getLong("data_criacao"));

			lista.add(c);
		}

		return lista;

	}

}
