package asktechforum.conection;


public class ConexaoFactory {
	
	private final int LOCAL = 1, REMOTA = 2;
	private ConexaoAbs conexao;
	
	public ConexaoAbs criarConexao(int tipoConexao){
		conexao = null;
		
		if(tipoConexao == LOCAL){
			conexao = new ConexaoLocal();
		}else if(tipoConexao == REMOTA){
			conexao = new ConexaoRemota();
		}else{
			System.out.println("Não foi possível criar conexão. Tipo de conexão inválido.");
		}
		
		return conexao;
	}
}
