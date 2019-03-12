package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConexaoJDBC;
import model.Cadastro;

public class CadastroDAO {
	private final ConexaoJDBC conexao;
	
	public CadastroDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoJDBC();
	}
	
	public Long insert(Cadastro cadastro) throws SQLException, ClassNotFoundException {
		Long id = null;
		String sql = "INSERT INTO cadastro (nome) VALUES (?) RETURNING id";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sql);
			
			stmt.setString(1, cadastro.getNome());
			
			ResultSet resultado = stmt.executeQuery();
			if(resultado.next()) {
				id = resultado.getLong("id");
			}
			
			this.conexao.commit();
		}catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		
		return id;
	}
}
