package ES1_2019_EIC2_03.DefectsDetection.code;

public class DefectDetectionProgram {
	
	private int dci;
	private int dii;
	private int adci;
	private int adii;

	public DefectDetectionProgram(int dci, int dii, int adci, int adii) {
		this.dci = dci;
		this.dii = dii;
		this.adci = adci;
		this.adii = dci;
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
}