package ES1_2019_EIC2_03.DefectsDetection.code;

import junit.framework.TestCase;

public class CostumRuleTest extends TestCase {

	private CostumRule lm;
	private CostumRule fe;

	protected void setUp() throws Exception {
		lm = new CostumRule(Defects.LONG_METHOD, "LOC > 80.0 AND CYCLO >10.0");
		fe = new CostumRule(Defects.FEATURE_ENVY, "ATFD > 4.0 AND LAA < 0.42");

	}

	public void testCostumRule() {
		assertNotNull(lm);
		assertNotNull(fe);
	}

	public void testGetDefect() {
		assertEquals(Defects.LONG_METHOD, lm.getDefect());
		assertEquals(Defects.FEATURE_ENVY, fe.getDefect());
	}

	public void testGetRule() {
		assertEquals("LOC > 80.0 AND CYCLO >10.0", lm.getRule());
		assertEquals("ATFD > 4.0 AND LAA < 0.42", fe.getRule());

	}

	public void testIsValidRule() {
		assertTrue(CostumRule.isValidRule(lm.getRule()));
		assertTrue(CostumRule.isValidRule(fe.getRule()));

	}

	public void testEqualsObject() {
		Object o = new CostumRule(Defects.LONG_METHOD, "LOC > 80.0 AND CYCLO >10.0");
		Object u = new CostumRule(Defects.FEATURE_ENVY, "ATFD > 4.0 AND LAA < 0.42");
		assertTrue(o.equals(lm));
		assertTrue(u.equals(fe));
	}

	public void testToString() {
		assertEquals("LOC > 80.0 AND CYCLO >10.0",lm.getRule());
		assertEquals( "ATFD > 4.0 AND LAA < 0.42",fe.getRule());



	}
}
