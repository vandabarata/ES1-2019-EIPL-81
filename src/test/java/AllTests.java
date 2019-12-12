package test.java;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/**
 * JUnit Test Suite containing all test classes for easier tracking and test
 * running
 */
@RunWith(JUnitPlatform.class)
@SelectClasses({ TestApp.class, TestCodeQualityRuleModel.class, TestEditRuleController.class, TestEditRulePopup.class,
		TestExcelImporterModel.class, TestExcelRow.class, TestGUIMainFrame.class, TestMainController.class,
		TestQualityIndicator.class, TestQualityRulesResultFrame.class })
public class AllTests {

}
