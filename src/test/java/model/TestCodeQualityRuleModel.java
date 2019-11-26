package test.java.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import main.java.model.CodeQualityRule;

/**
 * @author Lino Silva
 *
 */
class TestCodeQualityRuleModel {

	/**
	 * This test validates if a default CodeQualityRule is being created successfully
	 */
	@Test
	void testCreationOfDefaultRule() {
		CodeQualityRule ruleDefault = new CodeQualityRule("is_long_method", "LOC > 80 && CYCLO > 10", true, false);
		
		assertEquals("is_long_method", ruleDefault.getName());
		assertEquals("LOC > 80 && CYCLO > 10", ruleDefault.getRule());
		assertEquals(true, ruleDefault.isDefault());
		assertEquals(false, ruleDefault.isAdvanced());
	}
	
	/**
	 * This test validates if a non default CodeQualityRule is being created successfully
	 */
	@Test
	void testCreationOfNonDefaultRule() {
		CodeQualityRule ruleNonDefault = new CodeQualityRule("is_banana", "ATFD > 4 && LAA < 0.42", false, false);
		
		assertEquals("is_banana", ruleNonDefault.getName());
		assertEquals("ATFD > 4 && LAA < 0.42", ruleNonDefault.getRule());
		assertEquals(false, ruleNonDefault.isDefault());
		assertEquals(false, ruleNonDefault.isAdvanced());
	}
	
	/**
	 * This test verifies if the name of a default rule can be changed with the 
	 * setName method
	 */
	@Test
	void testNameChangeDefaultRule() {
		CodeQualityRule ruleDefault = new CodeQualityRule("is_long_method", "LOC > 80 && CYCLO > 10", true, false);
		
		assertEquals("is_long_method", ruleDefault.getName());
		ruleDefault.setName("new_method");
		assertNotEquals("new_method", ruleDefault.getName());
		assertEquals("is_long_method", ruleDefault.getName());
	}
	
	/**
	 * This test verifies if the rule content of a default rule can be changed with the 
	 * setRule method
	 */
	@Test
	void testRuleChangeDefaultRule() {
		CodeQualityRule ruleDefault = new CodeQualityRule("is_long_method", "LOC > 80 && CYCLO > 10", true, false);
		
		assertEquals("LOC > 80 && CYCLO > 10", ruleDefault.getRule());
		ruleDefault.setRule("CYCLO < 50");
		assertNotEquals("CYCLO < 50", ruleDefault.getRule());
		assertEquals("LOC > 80 && CYCLO > 10", ruleDefault.getRule());
	}
	
	/**
	 * This test verifies if the type of a default rule can be changed with the 
	 * setIsAdvanced method
	 */
	@Test
	void testTypeChangeDefaultRule() {
		CodeQualityRule ruleDefault = new CodeQualityRule("is_long_method", "LOC > 80 && CYCLO > 10", true, false);
		
		assertEquals(false, ruleDefault.isAdvanced());
		ruleDefault.setIsAdvanced(true);
		assertNotEquals(true, ruleDefault.isAdvanced());
	}
	
	/**
	 * This test verifies if the name of a non default rule can be changed with the 
	 * setName method
	 */
	@Test
	void testNameChangeNonDefaultRule() {
		CodeQualityRule ruleNonDefault = new CodeQualityRule("is_banana", "ATFD > 4 && LAA < 0.42", false, false);
		
		assertEquals("is_banana", ruleNonDefault.getName());
		ruleNonDefault.setName("new_method");
		assertEquals("new_method", ruleNonDefault.getName());
		assertNotEquals("is_long_method", ruleNonDefault.getName());
	}
	
	/**
	 * This test verifies if the rule content of a non default rule can be changed with the 
	 * setRule method
	 */
	@Test
	void testRuleChangeNonDefaultRule() {
		CodeQualityRule ruleNonDefault = new CodeQualityRule("is_banana", "ATFD > 4 && LAA < 0.42", false, false);
		
		assertEquals("ATFD > 4 && LAA < 0.42", ruleNonDefault.getRule());
		ruleNonDefault.setRule("CYCLO < 50");
		assertEquals("CYCLO < 50", ruleNonDefault.getRule());
		assertNotEquals("ATFD > 4 && LAA < 0.42", ruleNonDefault.getRule());
	}
	
	/**
	 * This test verifies if the type of a non default rule can be changed with the 
	 * setIsAdvanced method
	 */
	@Test
	void testTypeChangeNonDefaultRule() {
		CodeQualityRule ruleNonDefault = new CodeQualityRule("is_banana", "ATFD > 4 && LAA < 0.42", false, false);
		
		assertEquals(false, ruleNonDefault.isAdvanced());
		ruleNonDefault.setIsAdvanced(true);
		assertEquals(true, ruleNonDefault.isAdvanced());
	}

}
