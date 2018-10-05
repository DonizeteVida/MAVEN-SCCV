package br.senai.sp.jaguariuna.sccv.uDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.jdbc.ConnectionDB;
import br.senai.sp.jaguariuna.sccv.utils.FormatarCPFouRGtoString;
import br.senai.sp.jaguariuna.sccv.utils.StringToMD5;

public class UsuarioDao {

	private Connection conn;

	public UsuarioDao() {
		conn = ConnectionDB.getConnection();
	}

	public boolean updateUsuario(Usuario u) throws SQLException {
		String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, idade = ?, cpf = ?, rg = ?, id_curso = ?, id_turma = ?, id_cidade = ?, id_estado = ?, id_categoria = ?, id_sexo = ?, peso = ?, nivel_ingles = ?, nivel_espanhol = ? WHERE usuario.id = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, u.getNome());
		ps.setString(2, u.getEmail());
		ps.setString(3, StringToMD5.convertStringToMd5(u.getSenha()));
		ps.setLong(4, u.getIdade().getTimeInMillis());
		ps.setString(5, FormatarCPFouRGtoString.format(u.getCpf()));
		ps.setString(6, FormatarCPFouRGtoString.format(u.getRg()));
		ps.setInt(7, u.getCurso().getId());
		ps.setInt(8, u.getTurma().getId());
		ps.setInt(9, u.getCidade().getId());
		ps.setInt(10, u.getEstado().getId());
		ps.setInt(11, u.getCategoria().getId());
		ps.setInt(12, u.getSexo().getId());
		ps.setInt(13, u.getPeso());
		ps.setInt(14, u.getNivel_ingles());
		ps.setInt(15, u.getNivel_espanhol());
		ps.setInt(16, u.getId());

		return ps.executeUpdate() > 0;
	}

	public boolean inserirUsuario(Usuario u) throws SQLException {
		String sql = "INSERT INTO usuario(nome, email, senha, idade, cpf, rg, id_curso, id_turma, id_cidade, id_estado, id_categoria, id_sexo, nivel_ingles, nivel_espanhol)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, u.getNome());
		ps.setString(2, u.getEmail());
		ps.setString(3, StringToMD5.convertStringToMd5(u.getSenha()));
		ps.setLong(4, u.getIdade().getTimeInMillis());
		ps.setString(5, FormatarCPFouRGtoString.format(u.getCpf()));
		ps.setString(6, FormatarCPFouRGtoString.format(u.getRg()));
		ps.setInt(7, u.getCurso().getId());
		ps.setInt(8, u.getTurma().getId());
		ps.setInt(9, u.getCidade().getId());
		ps.setInt(10, u.getEstado().getId());
		ps.setInt(11, u.getCategoria().getId());
		ps.setInt(12, u.getSexo().getId());
		ps.setInt(13, u.getNivel_ingles());
		ps.setInt(14, u.getNivel_espanhol());

		return ps.executeUpdate() > 0;
	};

	public Usuario buscaUsuarioPorEmail(String email) throws SQLException {
		String sql = "SELECT u.*, c.nome AS nomeCidade, e.nome AS nomeEstado, st.nome AS nomeStatus, cur.nome AS nomeCurso, tur.nome AS nomeTurma, cat.nome AS nomeCategoria, s.nome AS nomeSexo FROM usuario AS u "
				+ "INNER JOIN cidade AS c ON c.id = u.id_cidade " + "INNER JOIN estado AS e ON e.id = u.id_estado "
				+ "INNER JOIN status_ AS st ON st.id = u.id_status " + "INNER JOIN curso AS cur ON cur.id = u.id_curso "
				+ "INNER JOIN turma AS tur ON tur.id = u.id_turma "
				+ "INNER JOIN categoria AS cat ON cat.id = u.id_categoria "
				+ "INNER JOIN sexo AS s ON s.id = u.id_sexo " + "WHERE u.email = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email);

		ResultSet rs = ps.executeQuery();

		Usuario u = null;
		if (rs.next()) {
			u = new Usuario();

			u.setId(rs.getInt("id"));
			u.setNome(rs.getString("nome"));
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			u.getIdade().setTimeInMillis(rs.getLong("idade"));
			u.setCpf(rs.getString("cpf"));
			u.setRg(rs.getString("rg"));
			u.getStatus().setId(rs.getInt("id_status"));
			u.getStatus().setNome(rs.getString("nomeStatus"));
			u.getCurso().setId(rs.getInt("id_curso"));
			u.getCurso().setNome(rs.getString("nomeCurso"));
			u.getTurma().setId(rs.getInt("id_turma"));
			u.getTurma().setNome(rs.getString("nomeTurma"));
			u.getCidade().setId(rs.getInt("id_cidade"));
			u.getCidade().setNome(rs.getString("nomeCidade"));
			u.getEstado().setId(rs.getInt("id_estado"));
			u.getEstado().setNome(rs.getString("nomeEstado"));
			u.getCategoria().setId(rs.getInt("id_categoria"));
			u.getCategoria().setNome(rs.getString("nomeCategoria"));
			u.getSexo().setId(rs.getInt("id_sexo"));
			u.getSexo().setNome(rs.getString("nomeSexo"));
			u.setPeso(rs.getInt("peso"));
			u.setNivel_ingles(rs.getInt("nivel_ingles"));
			u.setNivel_espanhol(rs.getInt("nivel_espanhol"));

		}

		return u;
	}

	public Usuario buscaUsuarioPorCpf(String cpf) throws SQLException {
		String sql = "SELECT u.*, c.nome AS nomeCidade, e.nome AS nomeEstado, st.nome AS nomeStatus, cur.nome AS nomeCurso, tur.nome AS nomeTurma, cat.nome AS nomeCategoria, s.nome AS nomeSexo FROM usuario AS u "
				+ "INNER JOIN cidade AS c ON c.id = u.id_cidade " + "INNER JOIN estado AS e ON e.id = u.id_estado "
				+ "INNER JOIN status_ AS st ON st.id = u.id_status " + "INNER JOIN curso AS cur ON cur.id = u.id_curso "
				+ "INNER JOIN turma AS tur ON tur.id = u.id_turma "
				+ "INNER JOIN categoria AS cat ON cat.id = u.id_categoria "
				+ "INNER JOIN sexo AS s ON s.id = u.id_sexo " + "WHERE u.cpf = ?;";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, FormatarCPFouRGtoString.format(cpf));

		ResultSet rs = ps.executeQuery();

		Usuario u = null;
		if (rs.next()) {
			u = new Usuario();

			u.setId(rs.getInt("id"));
			u.setNome(rs.getString("nome"));
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			u.getIdade().setTimeInMillis(rs.getLong("idade"));
			u.setCpf(rs.getString("cpf"));
			u.setRg(rs.getString("rg"));
			u.getStatus().setId(rs.getInt("id_status"));
			u.getStatus().setNome(rs.getString("nomeStatus"));
			u.getCurso().setId(rs.getInt("id_curso"));
			u.getCurso().setNome(rs.getString("nomeCurso"));
			u.getTurma().setId(rs.getInt("id_turma"));
			u.getTurma().setNome(rs.getString("nomeTurma"));
			u.getCidade().setId(rs.getInt("id_cidade"));
			u.getCidade().setNome(rs.getString("nomeCidade"));
			u.getEstado().setId(rs.getInt("id_estado"));
			u.getEstado().setNome(rs.getString("nomeEstado"));
			u.getCategoria().setId(rs.getInt("id_categoria"));
			u.getCategoria().setNome(rs.getString("nomeCategoria"));
			u.getSexo().setId(rs.getInt("id_sexo"));
			u.getSexo().setNome(rs.getString("nomeSexo"));
			u.setPeso(rs.getInt("peso"));
			u.setNivel_ingles(rs.getInt("nivel_ingles"));
			u.setNivel_espanhol(rs.getInt("nivel_espanhol"));
		}

		return u;
	}

	public List<Usuario> listarUsuario() throws SQLException {
		String sql = "SELECT u.*, s.nome AS nomeSexo, c.nome AS nomeCidade, e.nome AS nomeEstado, sts.nome AS nomeStatus, ca.nome AS nomeCategoria, cur.nome AS nomeCurso, tur.nome AS nomeTurma FROM usuario AS u "
				+ "INNER JOIN sexo AS s ON s.id = u.id_sexo " + "INNER JOIN cidade AS c ON c.id = u.id_cidade "
				+ "INNER JOIN estado AS e ON e.id = u.id_estado " + "INNER JOIN status_ AS sts ON sts.id = u.id_status "
				+ "INNER JOIN categoria AS ca ON ca.id = u.id_categoria "
				+ "INNER JOIN curso AS cur ON cur.id = u.id_curso " + "INNER JOIN turma AS tur ON tur.id = u.id_turma;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		List<Usuario> lista = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario u = new Usuario();

			u.setId(rs.getInt("id"));
			u.setNome(rs.getString("nome"));
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			u.getIdade().setTimeInMillis(rs.getLong("idade"));
			u.setCpf(rs.getString("cpf"));
			u.setRg(rs.getString("rg"));
			u.setPeso(rs.getInt("peso"));
			u.getSexo().setId(rs.getInt("id_sexo"));
			u.getSexo().setNome(rs.getString("nomeSexo"));
			u.getCidade().setId(rs.getInt("id_cidade"));
			u.getCidade().setNome(rs.getString("nomeCidade"));
			u.getEstado().setId(rs.getInt("id_estado"));
			u.getEstado().setNome(rs.getString("nomeEstado"));
			u.getStatus().setId(rs.getInt("id_status"));
			u.getStatus().setNome(rs.getString("nomeStatus"));
			u.getCategoria().setId(rs.getInt("id_categoria"));
			u.getCategoria().setNome(rs.getString("nomeCategoria"));
			u.getCurso().setId(rs.getInt("id_curso"));
			u.getCurso().setNome(rs.getString("nomeCurso"));
			u.getTurma().setId(rs.getInt("id_turma"));
			u.getTurma().setNome(rs.getString("nomeTurma"));
			u.setNivel_ingles(rs.getInt("nivel_ingles"));
			u.setNivel_espanhol(rs.getInt("nivel_espanhol"));

			lista.add(u);

		}

		return lista;

	}

	public List<Usuario> listarUsuario(String nome) throws SQLException {
		String sql = "SELECT u.*, s.nome AS nomeSexo, c.nome AS nomeCidade, e.nome AS nomeEstado, sts.nome AS nomeStatus, ca.nome AS nomeCategoria, cur.nome AS nomeCurso, tur.nome AS nomeTurma FROM usuario AS u "
				+ "INNER JOIN sexo AS s ON s.id = u.id_sexo " + "INNER JOIN cidade AS c ON c.id = u.id_cidade "
				+ "INNER JOIN estado AS e ON e.id = u.id_estado " + "INNER JOIN status_ AS sts ON sts.id = u.id_status "
				+ "INNER JOIN categoria AS ca ON ca.id = u.id_categoria "
				+ "INNER JOIN curso AS cur ON cur.id = u.id_curso " + "INNER JOIN turma AS tur ON tur.id = u.id_turma "
				+ "WHERE u.nome like ?;";

		if (nome.equals("")) {
			return listarUsuario();
		}

		String local = "%" + nome + "%";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, local);

		ResultSet rs = ps.executeQuery();
		List<Usuario> lista = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario u = new Usuario();

			u.setId(rs.getInt("id"));
			u.setNome(rs.getString("nome"));
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			u.getIdade().setTimeInMillis(rs.getLong("idade"));
			u.setCpf(rs.getString("cpf"));
			u.setRg(rs.getString("rg"));
			u.setPeso(rs.getInt("peso"));
			u.getSexo().setId(rs.getInt("id_sexo"));
			u.getSexo().setNome(rs.getString("nomeSexo"));
			u.getCidade().setId(rs.getInt("id_cidade"));
			u.getCidade().setNome(rs.getString("nomeCidade"));
			u.getEstado().setId(rs.getInt("id_estado"));
			u.getEstado().setNome(rs.getString("nomeEstado"));
			u.getStatus().setId(rs.getInt("id_status"));
			u.getStatus().setNome(rs.getString("nomeStatus"));
			u.getCategoria().setId(rs.getInt("id_categoria"));
			u.getCategoria().setNome(rs.getString("nomeCategoria"));
			u.getCurso().setId(rs.getInt("id_curso"));
			u.getCurso().setNome(rs.getString("nomeCurso"));
			u.getTurma().setId(rs.getInt("id_turma"));
			u.getTurma().setNome(rs.getString("nomeTurma"));

			lista.add(u);

		}

		return lista;

	}

	public List<Usuario> listarUsuarioCpf(String nome) throws SQLException {
		String sql = "SELECT u.*, s.nome AS nomeSexo, c.nome AS nomeCidade, e.nome AS nomeEstado, sts.nome AS nomeStatus, ca.nome AS nomeCategoria, cur.nome AS nomeCurso, tur.nome AS nomeTurma FROM usuario AS u "
				+ "INNER JOIN sexo AS s ON s.id = u.id_sexo " + "INNER JOIN cidade AS c ON c.id = u.id_cidade "
				+ "INNER JOIN estado AS e ON e.id = u.id_estado " + "INNER JOIN status_ AS sts ON sts.id = u.id_status "
				+ "INNER JOIN categoria AS ca ON ca.id = u.id_categoria "
				+ "INNER JOIN curso AS cur ON cur.id = u.id_curso " + "INNER JOIN turma AS tur ON tur.id = u.id_turma "
				+ "WHERE u.cpf like ?;";

		String local = nome + "%";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, local);

		ResultSet rs = ps.executeQuery();
		List<Usuario> lista = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario u = new Usuario();

			u.setId(rs.getInt("id"));
			u.setNome(rs.getString("nome"));
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			u.getIdade().setTimeInMillis(rs.getLong("idade"));
			u.setCpf(rs.getString("cpf"));
			u.setRg(rs.getString("rg"));
			u.setPeso(rs.getInt("peso"));
			u.getSexo().setId(rs.getInt("id_sexo"));
			u.getSexo().setNome(rs.getString("nomeSexo"));
			u.getCidade().setId(rs.getInt("id_cidade"));
			u.getCidade().setNome(rs.getString("nomeCidade"));
			u.getEstado().setId(rs.getInt("id_estado"));
			u.getEstado().setNome(rs.getString("nomeEstado"));
			u.getStatus().setId(rs.getInt("id_status"));
			u.getStatus().setNome(rs.getString("nomeStatus"));
			u.getCategoria().setId(rs.getInt("id_categoria"));
			u.getCategoria().setNome(rs.getString("nomeCategoria"));
			u.getCurso().setId(rs.getInt("id_curso"));
			u.getCurso().setNome(rs.getString("nomeCurso"));
			u.getTurma().setId(rs.getInt("id_turma"));
			u.getTurma().setNome(rs.getString("nomeTurma"));
			u.setNivel_ingles(rs.getInt("nivel_ingles"));
			u.setNivel_espanhol(rs.getInt("nivel_espanhol"));

			lista.add(u);

		}

		return lista;

	}

}
