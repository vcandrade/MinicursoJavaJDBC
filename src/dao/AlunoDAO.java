package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoDAO {

	private Connection conn;

	public AlunoDAO(Connection conn) {

		this.conn = conn;
	}

	public void cadastrar(Aluno aluno) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("insert into aluno (registro_academico, nome, curso, sexo) values (?, ?, ?, ?)");

			st.setInt(1, aluno.getRa());
			st.setString(2, aluno.getNome());
			st.setString(3, aluno.getCurso());
			st.setString(4, aluno.getSexo());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public List<Aluno> buscarTodos() throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from aluno order by nome");

			rs = st.executeQuery();

			List<Aluno> listaAlunos = new ArrayList<>();

			while (rs.next()) {

				Aluno aluno = new Aluno();

				aluno.setRa(rs.getInt("registro_academico"));
				aluno.setNome(rs.getString("nome"));
				aluno.setCurso(rs.getString("curso"));
				aluno.setSexo(rs.getString("sexo"));

				listaAlunos.add(aluno);
			}

			return listaAlunos;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}

	public void atualizar(Aluno aluno) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("update aluno set nome = ?, curso = ?, sexo = ? where registro_academico = ?");

			st.setString(1, aluno.getNome());
			st.setString(2, aluno.getCurso());
			st.setString(3, aluno.getSexo());
			st.setInt(4, aluno.getRa());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public int excluir(int ra) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from aluno where registro_academico = ?");

			st.setInt(1, ra);

			int linhasManipuladas = st.executeUpdate();

			return linhasManipuladas;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
}
