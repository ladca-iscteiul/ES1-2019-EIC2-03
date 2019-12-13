package ES1_2019_EIC2_03.DefectsDetection.code;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(JUnitPlatform.class)
@SuiteClasses({CostumRuleTest.class, DefectsDetectionProgramTest.class, DefectsTest.class, ExcelExporterTest.class})
public class AllTests {

}
