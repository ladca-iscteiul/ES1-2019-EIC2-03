package ES1_2019_EIC2_03.DefectsDetection.code;

import junit.framework.TestCase;

public class DefectsTest extends TestCase {
	
	private Defects d ;
	private Defects e ;
	
	protected void setUp() throws Exception {
		 d = Defects.LONG_METHOD;
		 e = Defects.FEATURE_ENVY;
	}	
	
	public void testToString() {
		assertEquals("long_method()", d.toString());
		assertEquals( "feature_envy()",e.toString());
	}

}
