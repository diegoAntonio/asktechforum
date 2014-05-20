package asktechforum.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Util {
	
	/**
	 * Converte do formato Data SQL 
	 * para String 
	 * 
	 * @param formato formato da saida tipo dia mes anos(dd/MM/yyyy)
	 * @param data data para fazer cach e formata
	 * @return a data formata em string
	 */
	public String converterDataToString(String formato, Date data){
		String d = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		d = sdf.format(data);
		
		return d;
		
	}
	
	/**
	 * Converte String para Data SQL 
	 * @param formato formato formato da saida tipo dia mes anos(dd/MM/yyyy)
	 * @param data String com a data a ser formata e convertida
	 * @return retorna a data formatada e convertida para o tipo Date
	 * @throws ParseException
	 */
	public Date converterStringToDate(String formato, String data) throws ParseException{
		Date d = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		
		d = (Date) sdf.parse(data);
		
		return d;
	}
	
	/**
	 * Verifica se o valor passado ta no formato
	 * de uma data valida. ex dd/mm/yyyy
	 * @param valor valor para a verificao
	 * @return true se o valor esta no formato de data valida
	 */
	public boolean ehFormatoData(String valor){
		boolean eformatoData = false;
		
		return eformatoData;
	}
	
	/**
	 * Verifica se o valor passado ta no formato
	 * de uma hora valida. ex hh:mm
	 * @param valor valor para verificao
	 * @return true se o valor esta no formato de hora valida
	 */
	public boolean ehFormatoHora(String valor){
		boolean eformatoHora = false;
		
		return eformatoHora;
	}
	
	/**
	 * Verificar se contem apenas numero
	 * @param valor valor a ser verificado
	 * @return retorna true se sim
	 */
	public boolean ehNumero(String valor){
		boolean numero = false;
		
		return numero;
	}
	
	/**
	 * verifica se a string possui apenas letras
	 * @param valor valor a ser verificado
	 * @return retorna true se sim
	 */
	public boolean ehLetra(String valor){
		boolean letra = false;
		
		return letra;
	}

}