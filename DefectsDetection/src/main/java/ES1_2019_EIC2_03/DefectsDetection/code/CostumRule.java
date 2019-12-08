package ES1_2019_EIC2_03.DefectsDetection.code;

import java.util.Scanner;

import bsh.EvalError;
import bsh.Interpreter;

public class CostumRule {

	private Defects defect;
	private String rule;
	
//	public static void main(String[] args) {
//		CostumRule rule = new CostumRule("1",Defects.LONG_METHOD, "LOC == 3.0 AND CYCLO == 1.0 AND ATFD == 0.0 AND LAA == 1.0 ");
//		System.out.println(rule.getMethodEvaluation(1));
//	}
//	
	public CostumRule(Defects defect, String rule) {
		this.defect = defect;
		this.rule = rule;
	}
	
	public Defects getDefect() {
		return defect;
	}
	
	public String getRule() {
		return rule;
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
			interpreter.eval(code);
			res = true;
			
		} catch (EvalError e) {
			res = false;
		}

		return res;
	} 
	
	protected void readSavedFile() {
		// TODO Auto-generated method stub
		
	}
	
	protected void save() {
		// TODO Auto-generated method stub
		
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