package test.java.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.controller.EditRuleController;
import main.java.model.CodeQualityRule;

class EditRuleControllerTest {
	
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
		fail("Not yet implemented");
	}

}
