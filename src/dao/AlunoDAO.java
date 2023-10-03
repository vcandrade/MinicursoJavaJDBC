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

		
	}

	public List<Aluno> buscarTodos() throws SQLException {

		return null;
	}

	public void atualizar(Aluno aluno) throws SQLException {

		
	}

	public int excluir(int ra) throws SQLException {

		return 0;
	}
}
