package dtu.example;

import org.junit.jupiter.api.AfterEach;

/**
 * A test class to check which tests are run with which framework.
 * If run as a JUnit 5 (Jupiter) test, then both tests are run.
 * If run as a JUnit 4 test, then only the JUnit 4 tests are run.
 *
 * Remove the tests in your own projects.
 */
public class TestJUnit4AndJUnit5 {
	
	@org.junit.Before // JUnit 4 (JUnit 5 uses @org.junit.jupiter.api.BeforeEach)
	public void setUp() {
		System.out.println("setUp runs before a JUnit 4 tests");
	}

	@org.junit.After // JUnit 4 (JUnit 5 uses @org.junit.jupiter.api.AfterEach)
	public void tearDown() {
		System.out.println("tearDown runs after a JUnit 4 tests");
	}

	@org.junit.Test // JUnit 4
	public void junit4Test() {
		System.out.println("JUnit 4");
		org.junit.Assert.assertTrue(true); // JUnit 4
	}
	
	@org.junit.jupiter.api.Test // Junit 5
	public void junit5Test() {
		System.out.println("JUnit 5");
		org.junit.jupiter.api.Assertions.assertTrue(true); // JUnit 5
	}

}
