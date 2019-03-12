package controller;

import DAO.HistoricoDAO;
import model.Historico;

public class HistoricoController {
	
	public Long insertHistorico(Historico historico) {
		Long resp = null;
		try {
			HistoricoDAO historicoDAO = new HistoricoDAO();
			resp =  historicoDAO.insert(historico);
		} catch (Exception e) {}
		
		return resp;
	}
	
	public void joinResult(int id) {

		try {
			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicoDAO.joinResult(id);
		} catch (Exception e) {}
	}
}
