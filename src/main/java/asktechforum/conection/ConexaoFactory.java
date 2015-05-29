package asktechforum.conection;

import asktechforum.config.PropriedadesBancoLoader;

/**
 * 	Classe que implementa o padrao factory method
 * criando conexoes com o banco por um parametro
 * passado.
 * 
 * @author Pamela
 *
 */
public class ConexaoFactory {
	private ConexaoAbs conexao;
	
	public ConexaoAbs criarConexao(int tipoConexao){
		conexao = null;
		
		if(tipoConexao == PropriedadesBancoLoader.CONEXAO_LOCAL){
			conexao = new ConexaoLocal();
		}else if(tipoConexao == PropriedadesBancoLoader.CONEXAO_REMOTA){
			conexao = new ConexaoRemota();
		}else{
			System.out.println("Não foi possível criar conexão. Tipo de conexão inválido.");
		}
		
		return conexao;
	}
}
