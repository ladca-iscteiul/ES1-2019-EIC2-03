package ES1_2019_EIC2_03.DefectsDetection.code;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ES1_2019_EIC2_03.DefectsDetection.code.DefectDetectionProgram;

class DefectsDetectionProgramTest {
	
	private DefectDetectionProgram df;

	@BeforeEach
	 void init(){
		df= new DefectDetectionProgram(1,1,1,1);
	}

	@Test
	void testDefectDetectionProgram() {
		assertNotNull(df);
	}
	
	@Test
	void testGetTotalMehtodsEvaluated() {
		assertEquals(4, df.getTotalMehtodsEvaluated());
	}
	@Test
	void testGetCorrectEvaluations() {
		assertEquals(2, df.getCorrectEvaluations());
	}
	
	@Test
	void testGetIncorrectEvaluations() {
		assertEquals(2, df.getIncorrectEvaluations());
	}
	
	@Test
	void testGetDci() {
		assertEquals(1, df.getDci());
	}
	
	@Test
	void testGetDii() {
		assertEquals(1, df.getDii());
	}
	
	@Test
	void testGetAdci() {
		assertEquals(1, df.getAdci());
	}
	
	@Test
	public void testGetAdii() {
		assertEquals(1, df.getAdii());
	}


}
