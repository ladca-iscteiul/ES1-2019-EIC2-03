package ES1_2019_EIC2_03.DefectsDetection.code;

public enum Defects {
	LONG_METHOD("long_method()"), FEATURE_ENVY("feature_envy()");
	
	private final String name ;
	
	private Defects(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}