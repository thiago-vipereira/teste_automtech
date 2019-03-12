package controller;

import DAO.CadastroDAO;
import model.Cadastro;

public class CadastroController {

	public Long insertCadastro(Cadastro cadastro) {
		Long resp = null;
		try {
			CadastroDAO cadastroDAO = new CadastroDAO();
			resp =  cadastroDAO.insert(cadastro);
		} catch (Exception e) {}
		
		return resp;
	}
}
