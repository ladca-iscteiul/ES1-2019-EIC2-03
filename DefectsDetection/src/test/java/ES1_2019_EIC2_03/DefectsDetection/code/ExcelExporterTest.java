package ES1_2019_EIC2_03.DefectsDetection.code;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ES1_2019_EIC2_03.DefectsDetection.code.ExcelExporter;

class ExcelExporterTest {

	ExcelExporter e;
	
	@BeforeEach
	void init() {
		e= new ExcelExporter();	
	}
	
	@Test
	void testExcelExporter() {
		assertNotNull(e);
	}

	@Test
	void testNumRows() {
		assertEquals(420, e.NumRows());
	}

	@Test
	public void testGetElementAt() {
		assertEquals("false", e.getElementAt(420,10));
	}

	@Test
	void testGetLine() {
		assertEquals("MethodID<-->package<-->class<-->method<-->LOC<-->CYCLO<-->ATFD<-->LAA<-->is_long_method<-->iPlasma<-->PMD<-->is_feature_envy<-->", e.getLine(0));
	}

	@Test
	void testDataToMatrix() {
		assertNotNull(e.dataToMatrix());
	}

	@Test
	void testGetInstance() {
		assertNotNull(ExcelExporter.getInstance());
	}
}
