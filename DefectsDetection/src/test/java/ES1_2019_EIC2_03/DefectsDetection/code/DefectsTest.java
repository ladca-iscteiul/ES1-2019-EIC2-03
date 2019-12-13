package ES1_2019_EIC2_03.DefectsDetection.code;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ES1_2019_EIC2_03.DefectsDetection.code.Defects;

class DefectsTest {

	private Defects d ;
	private Defects e ;
	
	@BeforeEach
	void init() throws Exception {
		d = Defects.LONG_METHOD;
		e = Defects.FEATURE_ENVY;
	}

	@Test
	void testToString() {
		assertEquals("long_method()", d.toString());
		assertEquals( "feature_envy()",e.toString());

	}

}
