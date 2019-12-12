package test.java;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.App;

/**
 * Tests for the main app
 *
 */
@RunWith(JUnitPlatform.class)
class TestApp {

	/**
	 * Attempts to initialise the main app Fails if anything goes wrong during this
	 * process
	 */
	@Test
	final void test() {
		String args[] = {};
		App.main(args);
	}

}
