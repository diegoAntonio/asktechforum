package asktechforum.config;

import java.util.ResourceBundle;

/**
 * 	Classe Singleton que apenas carrega os dados de conexao do banco.
 * 
 * @author diego.ferreira
 *	
 */
public class PropriedadesBancoLoader {
	private static PropriedadesBancoLoader propriedades;
	private static final String path = "asktechforum.properties.propriedadesBanco";
	private ResourceBundle resources;
	
	public static final String BANCO_URL = getInstance().getPropriedade("url");
	public static final String BANCO_DRIVER = getInstance().getPropriedade("driver");
	public static final String BANCO_USER_NAME = getInstance().getPropriedade("username");
	public static final String BANCO_PASSWD =getInstance().getPropriedade("passwd");
	
	
	private PropriedadesBancoLoader(){
		this.carregarPropriedades();
	}
	
	
	private void carregarPropriedades(){
		this.resources = ResourceBundle.getBundle(path);
	}
	
	
	private static PropriedadesBancoLoader getInstance(){
		if(propriedades == null){
			propriedades = new PropriedadesBancoLoader();
		}
		
		return propriedades;
	}
	
	private String getPropriedade(String key){
		String propriedade = this.resources.getString(key);
		
		return propriedade;
	}	

}
