package ES1_2019_EIC2_03.DefectsDetection.code;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ES1_2019_EIC2_03.DefectsDetection.code.CostumRule;
import ES1_2019_EIC2_03.DefectsDetection.code.Defects;

class CostumRuleTest {
	
	private CostumRule lm;
	private CostumRule fe;

	@BeforeEach
	void init()  {
		lm = new CostumRule(Defects.LONG_METHOD, "LOC > 80.0 AND CYCLO >10.0");
		fe = new CostumRule(Defects.FEATURE_ENVY, "ATFD > 4.0 AND LAA < 0.42");
	}
	
	@Test
	void testgetTotalMehtodsEvaluated(){
		lm.setStats(10, 20, 20, 20);
		assertEquals(70, lm.getTotalMehtodsEvaluated());
	}
	
	@Test
	void testgetCorrectEvaluations() {
		lm.setStats(10, 20, 20, 20);
		assertEquals(30, lm.getCorrectEvaluations());
	}
	
	@Test
	void testgetIncorrectEvaluations() {
		lm.setStats(10, 20, 20, 20);
		assertEquals(40, lm.getIncorrectEvaluations());
	}
	
	@Test
	void testgetDci() {
		lm.setStats(10, 20, 20, 20);
		assertEquals(10, lm.getDci());
	}

	@Test
	void testgetDii() {
		lm.setStats(10, 20, 20, 20);
		assertEquals(20, lm.getDii());
	}

	@Test
	void testgetAdci() {
		lm.setStats(10, 20, 20, 20);
		assertEquals(20, lm.getAdci());
	}

	@Test
	void testgetAdii() {
		lm.setStats(10, 20, 20, 20);
		assertEquals(20, lm.getAdii());
	}
	
	@Test
	void testCostumRule() {
		assertNotNull(lm);
		assertNotNull(fe);
	}
	@Test
	void testGetDefect() {
		assertEquals(Defects.LONG_METHOD, lm.getDefect());
		assertEquals(Defects.FEATURE_ENVY, fe.getDefect());
	}
	@Test
	void testGetRule() {
		assertEquals("LOC > 80.0 AND CYCLO >10.0", lm.getRule());
		assertEquals("ATFD > 4.0 AND LAA < 0.42", fe.getRule());

	}
	@Test
	void testIsValidRule() {
		assertTrue(CostumRule.isValidRule(lm.getRule()));
		assertTrue(CostumRule.isValidRule(fe.getRule()));

	}
	@Test
	void testEqualsObject() {
		Object o = new CostumRule(Defects.LONG_METHOD, "LOC > 80.0 AND CYCLO >10.0");
		Object u = new CostumRule(Defects.FEATURE_ENVY, "ATFD > 4.0 AND LAA < 0.42");
		assertTrue(o.equals(lm));
		assertTrue(u.equals(fe));
	}
	@Test
	void testToString() {
		assertEquals("LOC > 80.0 AND CYCLO >10.0",lm.getRule());
		assertEquals( "ATFD > 4.0 AND LAA < 0.42",fe.getRule());
	}
}
