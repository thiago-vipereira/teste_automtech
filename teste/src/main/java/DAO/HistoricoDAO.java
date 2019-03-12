package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import connection.ConexaoJDBC;
import model.Historico;

public class HistoricoDAO {
	private final ConexaoJDBC conexao;
	
	public HistoricoDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoJDBC();
	}
	
	public Long insert(Historico historico) throws SQLException, ClassNotFoundException {
		Long id = null;
		String sql = "INSERT INTO historico (id_cadastro, valor) VALUES (?, ?) RETURNING id_cadastro";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sql);
			
			stmt.setLong(1, historico.getIdCadastro());
			stmt.setString(2, historico.getValor());
			
			ResultSet resultado = stmt.executeQuery();
			
			if(resultado.next()) {
				id = resultado.getLong("id_cadastro");
			}
			
			this.conexao.commit();
		}catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		
		return id;
	}

	public void joinResult(int value) throws SQLException, ClassNotFoundException {
		
		//Long id = null;
		String sql = "SELECT cadastro.id, cadastro.nome, historico.id_cadastro, historico.valor FROM cadastro "
				+ "INNER JOIN historico ON(cadastro.id = historico.id_cadastro)";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sql);
			
			ResultSet resultado = stmt.executeQuery();
			ResultSetMetaData rsmd = resultado.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
			if(resultado != null) {
				System.out.println("id | Nome | id_cadastro | Valor");
			}
			while (resultado.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(" | ");
			        String columnValue = resultado.getString(i);
			        System.out.print(columnValue);
			    }
			    System.out.println("");
			}
			
			this.conexao.commit();
		}catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
}
