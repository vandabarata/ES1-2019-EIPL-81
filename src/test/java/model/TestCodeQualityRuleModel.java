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
	 * This test validates if the a default CodeQualityRule is being created successfully
	 */
	@Test
	void testCreationOfDefaultRule() {
		CodeQualityRule ruleDefault = new CodeQualityRule("is_long_method", "LOC > 80 && CYCLO > 10", true, false);
		
		assertEquals("is_long_method", ruleDefault .getName());
		assertEquals("LOC > 80 && CYCLO > 10", ruleDefault.getRule());
		assertEquals(true, ruleDefault.isDefault());
		assertEquals(false, ruleDefault.isAdvanced());
	}
	
	/**
	 * This test validates if the a non default CodeQualityRule is being created successfully
	 */
	@Test
	void testCreationOfNonDefaultRule() {
		CodeQualityRule ruleNonDefault = new CodeQualityRule("is_banana", "ATFD > 4 && LAA < 0.42", false, false);
		
		assertEquals("is_banana", ruleNonDefault .getName());
		assertEquals("ATFD > 4 && LAA < 0.42", ruleNonDefault.getRule());
		assertEquals(false, ruleNonDefault.isDefault());
		assertEquals(false, ruleNonDefault.isAdvanced());
	}
	
	/**
	 * This test verifies if the content of a default rule can't be change with the 
	 * setters of the class
	 */
	@Test
	void testChangeContentOfDefaultRule() {
		CodeQualityRule ruleDefault = new CodeQualityRule("is_long_method", "LOC > 80 && CYCLO > 10", true, false);
		
		assertEquals("is_long_method", ruleDefault.getName());
		ruleDefault.setName("new_method");
		assertNotEquals("new_method", ruleDefault.getName());
		assertEquals("is_long_method", ruleDefault.getName());
		
		assertEquals("LOC > 80 && CYCLO > 10", ruleDefault.getRule());
		ruleDefault.setRule("CYCLO < 50");
		assertNotEquals("CYCLO < 50", ruleDefault.getRule());
		assertEquals("LOC > 80 && CYCLO > 10", ruleDefault.getRule());
		
		assertEquals(false, ruleDefault.isAdvanced());
		ruleDefault.setIsAdvanced(true);
		assertNotEquals(true, ruleDefault.isAdvanced());
		assertEquals(false, ruleDefault.isAdvanced());
	}
	
	/**
	 * This test verifies if the content of a default rule can be change with the 
	 * setters of the class successfully
	 */
	@Test
	void testChangeContentOfNonDefaultRule() {
		CodeQualityRule ruleNonDefault = new CodeQualityRule("is_banana", "ATFD > 4 && LAA < 0.42", false, false);
		
		assertEquals("is_banana", ruleNonDefault.getName());
		ruleNonDefault.setName("new_method");
		assertEquals("new_method", ruleNonDefault.getName());
		assertNotEquals("is_long_method", ruleNonDefault.getName());
		
		assertEquals("ATFD > 4 && LAA < 0.42", ruleNonDefault.getRule());
		ruleNonDefault.setRule("CYCLO < 50");
		assertEquals("CYCLO < 50", ruleNonDefault.getRule());
		assertNotEquals("ATFD > 4 && LAA < 0.42", ruleNonDefault.getRule());
		
		assertEquals(false, ruleNonDefault.isAdvanced());
		ruleNonDefault.setIsAdvanced(true);
		assertEquals(true, ruleNonDefault.isAdvanced());
		assertNotEquals(false, ruleNonDefault.isAdvanced());
	}

}
