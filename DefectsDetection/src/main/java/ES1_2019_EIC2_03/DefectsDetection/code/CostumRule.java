package ES1_2019_EIC2_03.DefectsDetection.code;

import java.util.Scanner;

import bsh.EvalError;
import bsh.Interpreter;

public class CostumRule {

	private Defects defect;
	private String rule;
	private int dci;
	private int dii ;
	private int adci;
	private int adii;
	
	public CostumRule(Defects defect, String rule) {
		this.defect = defect;
		this.rule = rule;
		this.dci = -1;
		this.dii = -1;
		this.adci = -1;
		this.adii = -1;
	}
	
	public int getTotalMehtodsEvaluated(){
		return dci+dii+adci+adii;
	}
	
	public int getCorrectEvaluations() {
		return dci+adci;
	}
	
	public int getIncorrectEvaluations() {
		return dii+adii;
	}
	
	public int getDci() {
		return dci;
	}

	public int getDii() {
		return dii;
	}

	public int getAdci() {
		return adci;
	}

	public int getAdii() {
		return adii;
	}
	
	public Defects getDefect() {
		return defect;
	}
	
	public String getRule() {
		return rule;
	}
	
	public void setStats(int dci, int dii, int adci, int adii) {
		this.dci = dci;
		this.dii = dii;
		this.adci = adci;
		this.adii = adii;
	}
	
	//Ver se a regra introduzida pelo user é válida
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
	
	@Override
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
	public String toString() {
		return rule;
	}
}