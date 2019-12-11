package ES1_2019_EIC2_03.DefectsDetection.tests;
import ES1_2019_EIC2_03.DefectsDetection.code.*;
import junit.framework.TestCase;

public class DefectsDetectionProgramTest extends TestCase {
	
	private DefectDetectionProgram df;
	protected void setUp() throws Exception {
		df= new DefectDetectionProgram(1,1,1,1);
	}

	public void testDefectDetectionProgram() {
		assertNotNull(df);
	}

	public void testGetTotalMehtodsEvaluated() {
		assertEquals(4, df.getTotalMehtodsEvaluated());
	}

	public void testGetCorrectEvaluations() {
		fail("Not yet implemented");
	}

	public void testGetIncorrectEvaluations() {
		fail("Not yet implemented");
	}

	public void testGetDci() {
		fail("Not yet implemented");
	}

	public void testGetDii() {
		fail("Not yet implemented");
	}

	public void testGetAdci() {
		fail("Not yet implemented");
	}

	public void testGetAdii() {
		fail("Not yet implemented");
	}

}
