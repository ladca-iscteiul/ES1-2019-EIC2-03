package ES1_2019_EIC2_03.DefectsDetection.code;

	/**
 * Classe representante das Ferramentas dadas, que vai tambem calcular a
 * qualidade das ferramentas de classificacao de erros.
 **/
public class DefectDetectionProgram {
	
	private int dci;
	private int dii;
	private int adci;
	private int adii;


/** Construtor: recebe os stats de cada ferramenta como argumento 
	 * @param dci numero de Defeitos Corretamente Identificados
	 * @param dii numero de Defeitos Incorretamente Identificados
	 * @param adci numero de Ausencias de Defeitos Corretamente Identificadas
	 * @param adii numero de Ausencias de Defeitos Inorretamente Identificadas
	 * */
	public DefectDetectionProgram(int dci, int dii, int adci, int adii) {
		this.dci = dci;
		this.dii = dii;
		this.adci = adci;
		this.adii = adii;
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
}