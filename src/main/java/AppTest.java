package main.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AppTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	final void test() {
		String args[]= {};
		App.main(args);
	}

}
