package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.AlunoDAO;
import dao.BancoDados;
import entities.Aluno;

public class AlunoService {

	public void cadastrar(Aluno aluno) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new AlunoDAO(conn).cadastrar(aluno);
	}

	public List<Aluno> buscarAlunos() throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new AlunoDAO(conn).buscarTodos();
	}
	
	public void atualizar(Aluno aluno) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new AlunoDAO(conn).atualizar(aluno);
	}

	public void excluir(int ra) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new AlunoDAO(conn).excluir(ra);
	}
}
