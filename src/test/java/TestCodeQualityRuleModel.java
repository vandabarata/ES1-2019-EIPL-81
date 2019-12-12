package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.model.CodeQualityRule;

/**
 * Tests for the CodeQualityRuleModel
 *
 */
@RunWith(JUnitPlatform.class)
class TestCodeQualityRuleModel {

	/** A default rule */
	CodeQualityRule ruleDefault;

	/** A new rule added by the user */
	CodeQualityRule potatoRule;

	@BeforeEach
	void setup() {
		ruleDefault = new CodeQualityRule("is_long_method", "LOC > 80 && CYCLO > 10", true, false);
		potatoRule = new CodeQualityRule("is_potato", "ATFD > 4 && LAA < 0.42", false, false);
	}

	/**
	 * Validates if a default CodeQualityRule is being created successfully
	 */
	@Test
	void testCreationOfDefaultRule() {
		assertEquals("is_long_method", ruleDefault.getName());
		assertEquals("LOC > 80 && CYCLO > 10", ruleDefault.getRule());
		assertTrue(ruleDefault.isDefault());
		assertTrue(ruleDefault.isAdvanced());
	}

	/**
	 * Validates if a non default CodeQualityRule is being created successfully
	 */
	@Test
	void testCreationOfNonDefaultRule() {
		assertEquals("is_potato", potatoRule.getName());
		assertEquals("ATFD > 4 && LAA < 0.42", potatoRule.getRule());
		assertFalse(potatoRule.isDefault());
		assertFalse(potatoRule.isAdvanced());
	}

	/**
	 * Verifies if the name of a default rule can be changed with the setName method
	 */
	@Test
	void testNameChangeDefaultRule() {
		assertEquals("is_long_method", ruleDefault.getName());
		ruleDefault.setName("new_method");
		assertNotEquals("new_method", ruleDefault.getName());
		assertEquals("is_long_method", ruleDefault.getName());
	}

	/**
	 * Verifies if the rule content of a default rule can be changed with the
	 * setRule method
	 */
	@Test
	void testRuleEditionDefaultRule() {
		assertEquals("LOC > 80 && CYCLO > 10", ruleDefault.getRule());
		ruleDefault.setRule("CYCLO < 50");
		assertEquals("CYCLO < 50", ruleDefault.getRule());
		assertNotEquals("LOC > 80 && CYCLO > 10", ruleDefault.getRule());
	}

	/**
	 * Verifies if the type of a default rule can be changed with the setAdvanced
	 * method
	 */
	@Test
	void testTypeChangeDefaultRule() {
		assertTrue(ruleDefault.isAdvanced());
		ruleDefault.setAdvanced(false);
		assertEquals(true, ruleDefault.isAdvanced());
	}

	/**
	 * Verifies if the name of a non default rule can be changed with the setName
	 * method
	 */
	@Test
	void testNameChangeNonDefaultRule() {
		assertEquals("is_potato", potatoRule.getName());
		potatoRule.setName("new_method");
		assertEquals("new_method", potatoRule.getName());
		assertNotEquals("is_potato", potatoRule.getName());
	}

	/**
	 * Verifies if the rule content of a non default rule can be changed with the
	 * setRule method
	 */
	@Test
	void testRuleEditionNonDefaultRule() {
		assertEquals("ATFD > 4 && LAA < 0.42", potatoRule.getRule());
		potatoRule.setRule("CYCLO < 50");
		assertEquals("CYCLO < 50", potatoRule.getRule());
		assertNotEquals("ATFD > 4 && LAA < 0.42", potatoRule.getRule());
	}

	/**
	 * Verifies if the type of a non default rule can be changed with the
	 * setIsAdvanced method
	 */
	@Test
	void testTypeChangeNonDefaultRule() {
		assertFalse(potatoRule.isAdvanced());
		potatoRule.setAdvanced(true);
		assertTrue(potatoRule.isAdvanced());
	}

	/**
	 * Tests if the toString method returns the rule's name as intended
	 */
	@Test
	void testToString() {
		String potatoName = potatoRule.toString();
		assertEquals(potatoRule.getName(), potatoName);
	}

}
