package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;
import br.senai.sp.jaguariuna.sccv.subEntities.ClasseGenerica;

public class ClasseGenericaDao {

	private Connection conn;

	public ClasseGenericaDao() {
		conn = ConnectionDB.getConnection();
	}

	public List<ClasseGenerica> buscaSexo() throws SQLException {
		String sql = "SELECT * FROM sexo;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> sexos = new ArrayList<ClasseGenerica>();

		while (rs.next()) {
			sexos.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return sexos;
	}

	public List<ClasseGenerica> buscaCategoria() throws SQLException {
		String sql = "SELECT * FROM categoria;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> cursos = new ArrayList<ClasseGenerica>();

		while (rs.next()) {
			cursos.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return cursos;
	}

	public boolean updateCategoria(ClasseGenerica cg) throws SQLException {
		String sql = "UPDATE categoria as ca SET nome = ? WHERE ca.id = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, cg.getNome());
		ps.setInt(2, cg.getId());

		return ps.executeUpdate() > 0;
	}

	public boolean inserirCategoria(ClasseGenerica cg) throws SQLException {
		String sql = "INSERT INTO categoria(nome) VALUES(?)";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, cg.getNome());

		return ps.executeUpdate() > 0;

	}

	public List<ClasseGenerica> buscaCurso(Integer id_categoria) throws SQLException {
		String sql = "SELECT * FROM curso WHERE id_categoria = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id_categoria);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> cursos = new ArrayList<ClasseGenerica>();

		while (rs.next()) {
			cursos.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return cursos;
	}

	public boolean updateCurso(ClasseGenerica cg) throws SQLException {
		String sql = "UPDATE curso as cur SET nome = ? WHERE cur.id = ?";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, cg.getNome());
		ps.setInt(2, cg.getId());

		return ps.executeUpdate() > 0;
	}

	public boolean inserirCurso(Integer id_categoria, ClasseGenerica cg) throws SQLException {
		String sql = "INSERT INTO curso(nome, id_categoria) VALUES(?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, cg.getNome());
		ps.setInt(2, id_categoria);

		return ps.executeUpdate() > 0;
	}

	public List<ClasseGenerica> buscaTurma(Integer id) throws SQLException {
		String sql = "SELECT t.id, t.nome FROM turma AS t WHERE id_curso = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> turmas = new ArrayList<ClasseGenerica>();
		while (rs.next()) {
			turmas.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return turmas;
	}

	public boolean updateTurma(ClasseGenerica cg) throws SQLException {
		String sql = "UPDATE turma as tu SET nome = ? WHERE tu.id = ?";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, cg.getNome());
		ps.setInt(2, cg.getId());

		return ps.executeUpdate() > 0;
	}

	public boolean inserirTurma(Integer id_curso, ClasseGenerica cg) throws SQLException {
		String sql = "INSERT INTO turma(nome, id_curso) VALUES(?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, cg.getNome());
		ps.setInt(2, id_curso);

		return ps.executeUpdate() > 0;
	}

	public List<ClasseGenerica> buscaEstado() throws SQLException {
		String sql = "SELECT e.id, e.nome FROM estado AS e;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> estados = new ArrayList<ClasseGenerica>();
		while (rs.next()) {
			estados.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return estados;
	}

	public List<ClasseGenerica> buscaCidade(Integer id) throws SQLException {
		String sql = "SELECT c.id, c.nome FROM cidade AS c WHERE c.estado = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> cidades = new ArrayList<ClasseGenerica>();
		while (rs.next()) {
			cidades.add(new ClasseGenerica(rs.getInt("id"), rs.getString("nome")));
		}

		return cidades;
	}

}
