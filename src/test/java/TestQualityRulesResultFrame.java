package test.java;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.gui.QualityRulesResultFrame;
import main.java.model.QualityIndicator;

class TestQualityRulesResultFrame {

	/* QualityRuleResultFrame that will be user for each test */
	QualityRulesResultFrame qualityRulesResultFrame;

	@BeforeEach
	void setUp() throws Exception {
		qualityRulesResultFrame = new QualityRulesResultFrame();
	}

	/**
	 * Tests fillFrame method by creating the necessary parameters and then calling
	 * the method
	 */
	@Test
	final void testFillFrame() {
		assertNotNull(qualityRulesResultFrame);
		String[] header = { "header1", "header2" };
		String[][] content = { { "cell0", "cell1" }, { "cell2", "cell3" } };
		String[][] mockExcelData = { { "1", "TRUE", "FALSE", "TRUE", "TRUE", "TRUE", "TRUE" },
				{ "2", "FALSE", "TRUE", "TRUE", "TRUE", "TRUE", "TRUE" },
				{ "3", "FALSE", "FALSE", "FALSE", "FALSE", "FALSE", "FALSE" },
				{ "4", "TRUE", "TRUE", "FALSE", "FALSE", "FALSE", "FALSE" } };
		QualityIndicator quality = new QualityIndicator(mockExcelData);
		qualityRulesResultFrame.fillFrame(content, header, quality);
	}

	/**
	 * Tests if the QualityResultsFrame hide() method is disposing of the frame
	 * correctly
	 */
	@Test
	final void testHide() {
		qualityRulesResultFrame.hide();
	}

	/**
	 * Tests updateFrame by creating and filling the qualityRulesResultFrame first
	 * and then calling the updateFrame method
	 */
	@Test
	final void testUpdateFrame() {
		String[] header = { "header1", "header2" };
		String[][] content = { { "cell0", "cell1" }, { "cell2", "cell3" } };
		String[][] mockExcelData = { { "1", "TRUE", "FALSE", "TRUE", "TRUE", "TRUE", "TRUE" },
				{ "2", "FALSE", "TRUE", "TRUE", "TRUE", "TRUE", "TRUE" },
				{ "3", "FALSE", "FALSE", "FALSE", "FALSE", "FALSE", "FALSE" },
				{ "4", "TRUE", "TRUE", "FALSE", "FALSE", "FALSE", "FALSE" } };
		QualityIndicator quality = new QualityIndicator(mockExcelData);
		qualityRulesResultFrame.fillFrame(content, header, quality);
		qualityRulesResultFrame.updateFrame();
	}

}
