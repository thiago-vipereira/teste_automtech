package project.teste;
import javax.swing.JOptionPane;

import controller.CadastroController;
import controller.HistoricoController;
import model.Cadastro;
import model.Historico;

public class Principal 
{
    public static void main( String[] args )
    {
        Cadastro cadastro = new Cadastro();
        CadastroController cadastroController = new CadastroController();
        
        Historico historico = new Historico();
        HistoricoController historicoController = new HistoricoController();
        
        String nome;
        String valor;
        
        do{
        	nome = JOptionPane.showInputDialog(null, "Insira o Nome:");
        }while(nome == null || nome == "");
        
        do{
        	valor = JOptionPane.showInputDialog(null, "Insira o Valor:");
        }while(valor == null || valor == "");
        
        cadastro.setNome(nome);
        Long idCadastro = cadastroController.insertCadastro(cadastro);
        
        historico.setIdCadastro(idCadastro);
        historico.setValor(valor);
        Long idHistorico =  historicoController.insertHistorico(historico);

        historicoController.joinResult(idHistorico.intValue());
        
        
    }
}
