package ES1_2019_EIC2_03.DefectsDetection.code;
import  ES1_2019_EIC2_03.DefectsDetection.code.*;

import junit.framework.TestCase;

public class ExcelExporterTest extends TestCase {
	
	ExcelExporter e;

	
	protected void setUp() throws Exception {
		e= new ExcelExporter();
		
	}

	public void testExcelExporter() {
		assertNotNull(e);
	}

	public void testNumRows() {
		assertEquals(420, e.NumRows());
	}

	public void testGetElementAt() {
		assertEquals("false", e.getElementAt(420,10));
	}

	public void testGetLine() {
		assertEquals("MethodID<-->package<-->class<-->method<-->LOC<-->CYCLO<-->ATFD<-->LAA<-->is_long_method<-->iPlasma<-->PMD<-->is_feature_envy<-->", e.getLine(0));
	}

	public void testDataToMatrix() {
		fail("Not yet implemented");
	}

	public void testGetInstance() {
		fail("Not yet implemented");
	}

}
