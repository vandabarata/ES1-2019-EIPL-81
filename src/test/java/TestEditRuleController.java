package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.controller.EditRuleController;
import main.java.model.CodeQualityRule;

@RunWith(JUnitPlatform.class)
class TestEditRuleController {
	
	CodeQualityRule testRule;
	EditRuleController controller;

	@BeforeEach
	void setUp() throws Exception {
		testRule = new CodeQualityRule("test", "LOC > 10" , false, false);
	}

	@Test
	void testEditRuleControllerWithRule() {
		controller = new EditRuleController(testRule);
	}

	@Test
	void testEditRuleControllerNoRule() {
		controller = new EditRuleController();
	}
	
	@Test
	void testGetJavascriptString() {
		String ruleConditions = "LOC > 10 AND LAA == 20 OR LAA != 5";
		controller = new EditRuleController();
		String testJS = controller.getJavascriptIfStatementString(ruleConditions);
		assertEquals("LOC > 10 && LAA == 20 || LAA != 5", testJS);
	}

}
