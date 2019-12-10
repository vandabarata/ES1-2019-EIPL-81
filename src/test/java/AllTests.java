package test.java;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ TestCodeQualityRuleModel.class, TestEditRuleController.class, TestEditRulePopup.class,
		TestExcelImporterModel.class, TestExcelRow.class, TestGUIMainFrame.class, TestMainController.class,
		TestQualityIndicator.class, TestQualityRulesResultFrame.class })
public class AllTests {
	
}
