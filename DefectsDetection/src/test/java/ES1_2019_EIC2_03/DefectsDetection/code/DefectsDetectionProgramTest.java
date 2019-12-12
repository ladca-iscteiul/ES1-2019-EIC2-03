package ES1_2019_EIC2_03.DefectsDetection.code;
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
		assertEquals(2, df.getCorrectEvaluations());
	}

	public void testGetIncorrectEvaluations() {
		assertEquals(2, df.getIncorrectEvaluations());
	}

	public void testGetDci() {
		assertEquals(1, df.getDci());
	}

	public void testGetDii() {
		assertEquals(1, df.getDii());
	}

	public void testGetAdci() {
		assertEquals(1, df.getAdci());
	}

	public void testGetAdii() {
		assertEquals(1, df.getAdii());
	}

}
