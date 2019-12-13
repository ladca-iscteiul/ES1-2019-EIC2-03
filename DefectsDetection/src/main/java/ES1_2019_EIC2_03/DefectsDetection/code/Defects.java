package ES1_2019_EIC2_03.DefectsDetection.code;
/** 
* Enumerado usado para representar os defeitos
*
*/
public enum Defects {
	/**Defeito Long Method*/
	LONG_METHOD("long_method()"),
	
	/**Defeito Feature Envy*/
	FEATURE_ENVY("feature_envy()");
	
	private final String name ;
	
	
	private Defects(String name) {
		this.name = name;
	}
	
	@Override
	/**Metodo que devove o nome do Defeito em String*/
	public String toString() {
		return name;
	}

}