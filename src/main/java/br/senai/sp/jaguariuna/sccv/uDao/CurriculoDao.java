package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.jaguariuna.sccv.entities.CurriculumVitae;
import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;
import br.senai.sp.jaguariuna.sccv.subEntities.Experiencia;
import br.senai.sp.jaguariuna.sccv.subEntities.Formacao;

public class CurriculoDao {

	private Connection conn;

	public CurriculoDao() {
		conn = ConnectionDB.getConnection();
	}

	public List<Formacao> listarFormacoes(CurriculumVitae curriculumVitae) throws SQLException {
		String sql = "SELECT * FROM formacao WHERE id_curriculum_vitae = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, curriculumVitae.getId());

		ResultSet rs = ps.executeQuery();

		List<Formacao> formacoes = new ArrayList<>();

		while (rs.next()) {

			Calendar data_inicio = Calendar.getInstance();
			Calendar data_fim = Calendar.getInstance();

			data_inicio.setTimeInMillis(rs.getLong("data_inicio"));
			data_fim.setTimeInMillis(rs.getLong("data_fim"));

			formacoes.add(new Formacao(rs.getInt("id"), rs.getString("nome"), data_inicio, data_fim,
					rs.getString("escola"), rs.getInt("id_curriculum_vitae")));

		}

		return formacoes;
	}

	public boolean inserirFormacao(Formacao formacao, CurriculumVitae curriculumVitae) throws SQLException {
		String sql = "INSERT INTO formacao(nome, data_inicio, data_fim, id_curriculum_vitae) VALUES (?, ?, ?, ?);";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, formacao.getNome());
		ps.setLong(2, formacao.getData_inicio().getTimeInMillis());
		ps.setLong(3, formacao.getData_fim().getTimeInMillis());
		ps.setInt(4, curriculumVitae.getId());

		return ps.executeUpdate() > 0;
	}

	public List<Experiencia> listarExperiencias(CurriculumVitae curriculumVitae) throws SQLException {
		String sql = "SELECT * FROM experiencia WHERE id_curriculum_vitae = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, curriculumVitae.getId());

		ResultSet rs = ps.executeQuery();

		List<Experiencia> experiencias = new ArrayList<>();

		while (rs.next()) {
			Calendar data_inicio = Calendar.getInstance();
			Calendar data_fim = Calendar.getInstance();

			data_inicio.setTimeInMillis(rs.getLong("data_inicio"));
			data_fim.setTimeInMillis(rs.getLong("data_fim"));

			experiencias.add(
					new Experiencia(rs.getInt("id"), rs.getString("nome"), data_inicio, data_fim, rs.getString("cargo"),
							rs.getString("empresa"), rs.getString("funcoes"), rs.getInt("id_curriculum_vitae")));
		}

		return experiencias;
	}

	public boolean inserirExperiencia(Experiencia experiencia, CurriculumVitae curriculumVitae) throws SQLException {
		String sql = "INSERT INTO experiencia(nome, data_inicio, data_fim, id_curriculum_vitae) VALUES (?, ?, ?, ?);";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, experiencia.getNome());
		ps.setLong(2, experiencia.getData_inicio().getTimeInMillis());
		ps.setLong(3, experiencia.getData_fim().getTimeInMillis());
		ps.setInt(4, curriculumVitae.getId());

		return ps.executeUpdate() > 0;
	}

	public boolean criarCurriculo(CurriculumVitae c, Usuario u) throws SQLException {
		String sql = "INSERT INTO curriculum_vitae(data_criacao, id_curso, id_turma, semestre, id_usuario) VALUES (?, ?, ?, ?, ?);";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, Calendar.getInstance().getTimeInMillis());
		ps.setInt(2, c.getCurso().getId());
		ps.setInt(3, c.getTurma().getId());
		ps.setInt(4, c.getSemestre());
		ps.setInt(5, u.getId());

		return ps.executeUpdate() > 0;
	}

	public List<CurriculumVitae> listarCurriculo(Integer id_usuario) throws SQLException {
		String sql = "SELECT c.*, cur.nome AS nomeCurso, tur.nome AS nomeTurma, sts.nome AS nomeStatus FROM curriculum_vitae AS c "
				+ "INNER JOIN curso AS cur ON cur.id = c.id_curso " + "INNER JOIN turma AS tur ON tur.id = c.id_turma "
				+ "INNER JOIN status_ AS sts ON sts.id = c.id_status " + "WHERE c.id_usuario = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id_usuario);

		ResultSet rs = ps.executeQuery();

		List<CurriculumVitae> lista = new ArrayList<CurriculumVitae>();

		while (rs.next()) {
			CurriculumVitae c = new CurriculumVitae();

			c.setId(rs.getInt("id"));
			c.setPeso(rs.getInt("peso"));
			c.setData_criacao(rs.getLong("data_criacao"));
			c.getCurso().setId(rs.getInt("id_curso"));
			c.getCurso().setNome(rs.getString("nomeCurso"));
			c.getTurma().setId(rs.getInt("id_turma"));
			c.getTurma().setNome(rs.getString("nomeTurma"));
			c.setSemestre(rs.getInt("semestre"));
			c.getUsuario().setId(rs.getInt("id_usuario"));
			c.getStatus().setId(rs.getInt("id_status"));
			c.getStatus().setNome(rs.getString("nomeStatus"));

			lista.add(c);
		}

		return lista;

	}

	public List<CurriculumVitae> listarCurriculo(Integer id_usuario, Integer id_curso) throws SQLException {
		String sql = "SELECT c.*, cur.nome AS nomeCurso, tur.nome AS nomeTurma, sts.nome AS nomeStatus FROM curriculum_vitae AS c "
				+ "INNER JOIN curso AS cur ON cur.id = c.id_curso " + "INNER JOIN turma AS tur ON tur.id = c.id_turma "
				+ "INNER JOIN status_ AS sts ON sts.id = c.id_status " + "WHERE c.id_usuario = ? AND c.id_curso = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id_usuario);
		ps.setInt(2, id_curso);
		ResultSet rs = ps.executeQuery();
		List<CurriculumVitae> lista = new ArrayList<CurriculumVitae>();

		while (rs.next()) {
			CurriculumVitae c = new CurriculumVitae();

			c.setId(rs.getInt("id"));
			c.setPeso(rs.getInt("peso"));
			c.setData_criacao(rs.getLong("data_criacao"));
			c.getCurso().setId(rs.getInt("id_curso"));
			c.getCurso().setNome(rs.getString("nomeCurso"));
			c.getTurma().setId(rs.getInt("id_turma"));
			c.getTurma().setNome(rs.getString("nomeTurma"));
			c.setSemestre(rs.getInt("semestre"));
			c.getUsuario().setId(rs.getInt("id_usuario"));
			c.getStatus().setId(rs.getInt("id_status"));
			c.getStatus().setNome(rs.getString("nomeStatus"));

			lista.add(c);
		}

		return lista;

	}

}
