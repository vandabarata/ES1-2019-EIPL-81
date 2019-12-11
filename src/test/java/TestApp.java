package test.java;

import org.junit.jupiter.api.Test;

import main.java.App;

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
