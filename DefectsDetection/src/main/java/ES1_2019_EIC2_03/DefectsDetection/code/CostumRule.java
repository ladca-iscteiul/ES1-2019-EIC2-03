package ES1_2019_EIC2_03.DefectsDetection.code;

import java.util.Scanner;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * Classe que representa cada regra criada pelo User (Custom Rule)
 */
public class CostumRule {

	private Defects defect;
	private String rule;
	private int dci;
	private int dii ;
	private int adci;
	private int adii;
	
	/**Metodo construtor de Custom Rule (regra)
	*@param defect tipo_de_defeito 
	*@param rule regra
	*/
	public CostumRule(Defects defect, String rule) {
		this.defect = defect;
		this.rule = rule;
		this.dci = -1;
		this.dii = -1;
		this.adci = -1;
		this.adii = -1;
	}
	/**
	 * Retorna o numero de metodos avaliados presentes no ficheiro excel
	 * 
	 * @return numero total de metodos avaliados
	 */
	public int getTotalMehtodsEvaluated(){
		return dci+dii+adci+adii;
	}
	
	/**
	 * Retorna o numero de avaliacoes corretas
	 * 
	 * @return numero de avaliacoes corretas
	 */
	public int getCorrectEvaluations() {
		return dci+adci;
	}
	/**
	 * Retorna o numero de avaliacoes incorretas
	 * 
	 * @return numero de avaliacoes incorretas
	 */
	public int getIncorrectEvaluations() {
		return dii+adii;
	}
	/**
	 * Retorna o numero de Defeitos Corretamente Identificados (DCI)
	 * 
	 * @return dci
	 */
	public int getDci() {
		return dci;
	}
	/**
	 * Retorna o numero de Defeitos Incorretamente Identificados (DII)
	 * 
	 * @return dii
	 */
	public int getDii() {
		return dii;
	}
	/**
	 * Retorna o numero de Ausencias de Defeitos Corretamente Identificadas (ADCI)
	 * 
	 * @return adci
	 */
	public int getAdci() {
		return adci;
	}
	/**
	 * Retorna o numero de Ausencias de Defeitos Incorretamente Identificadas (ADII)
	 * 
	 * @return adii
	 */
	public int getAdii() {
		return adii;
	}
	/** Getter do defeito
	 * @return defeito*/
	public Defects getDefect() {
		return defect;
	}
	/** Getter da regra
	 * @return regra*/
	public String getRule() {
		return rule;
	}
	/**Setter dos Stats
	 * @param dci numero de Defeitos Corretamente Identificados
	 * @param dii numero de Defeitos Incorretamente Identificados
	 * @param adci numero de Ausencias de Defeitos Corretamente Identificadas
	 * @param adii numero de Ausencias de Defeitos Inorretamente Identificadas
	*/
	public void setStats(int dci, int dii, int adci, int adii) {
		this.dci = dci;
		this.dii = dii;
		this.adci = adci;
		this.adii = adii;
	}
	/**Metodo que ira ver se uma regra (introduzida pelo user) e valida, analisando a sintaxe
	 * @param args Regra
	 * @return validez_da_regra
	*/

	public static boolean isValidRule(String args) {
		
		Scanner s = new Scanner(args);
		
		String code = "";
		Interpreter interpreter = new Interpreter();
		Boolean res;

		while(s.hasNext()) {
			String word = s.next();
			
			if(word.equals("LOC")) {
				code += "1.0";
				continue;
			}
			if(word.equals("CYCLO")) {
				code += "1.0";
				continue;
			}
			
			if(word.equals("ATFD")) {
				code += "1.0";
				continue;
			}
			
			if(word.equals("LAA")) {
				code += "1.0";
				continue;
			}
			
			if(word.equals("AND")) {
				code += "&&";
				continue;
			}
			
			if(word.contentEquals("OR")) {
				code += "||";
				continue;
			}
			
			else
				code += word;
		}
		
		try {
			Double.parseDouble(code);
			return false;
		}catch(NumberFormatException n) {
			try {
				interpreter.eval(code);
				res = true;

			} catch (EvalError e) {
				res = false;
			}
		}
		return res;
	} 
	
	/** Metodo que analisa se se esta Custom Rule e igual a um Object 
	 *@param o objecto a comparar
	  */

	public boolean equals(Object o) {
		if (o == this) { 
			return true; 
		} 

		if (!(o instanceof CostumRule)) { 
			return false; 
		} 

		CostumRule teste = (CostumRule) o;

		if (teste.getDefect().equals(this.defect) && teste.getRule().equals(this.rule)){
			return true;
		}
		return false;

	}

	@Override 
	/**Metodo que devolve em String o conteudo da regra
	 * @return regra*/
	public String toString() {
		return rule;
	}
}