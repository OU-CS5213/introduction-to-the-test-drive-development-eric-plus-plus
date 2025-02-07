import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AWSTest {

	private static final int FILLER_VALUE = Integer.MIN_VALUE;
	private int[] original={1, 2, 3};
	AWS originalAWS;
	
	@BeforeEach
	void setUp() throws Exception {
		 originalAWS = new AWS(this.original);
	}
	
	@Test
	void testGreeting() {
		int[] x = {1, 2, 3, 4, 5, 6, 7, 8};
		AWS aws = new AWS(x);
		
		assertEquals(aws.greeting(), "Hello");
	}

	@Test
	void testGetValues() {
		int[] x = {1, 2, 3, 4, 5, 6, 7, 8};
		AWS aws = new AWS(x);
		
		int[] actual = aws.getValues();
		
		assertEquals(x[0], actual[0]);
		assertEquals(x[1], actual[1]);
		assertEquals(x[2], actual[2]);
		assertEquals(x[3], actual[3]);
		assertEquals(x[4], actual[4]);
		assertEquals(x[5], actual[5]);
		assertEquals(x[6], actual[6]);
		assertEquals(x[7], actual[7]);
	}

	@Test
	void testSetValues() {
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		
		int[] expected = {4, 5, 6};
		aws.setValues(expected);
		
		int[] actual = aws.getValues();
		
		assertEquals(expected[0], actual[0]);
		assertEquals(expected[1], actual[1]);
		assertEquals(expected[2], actual[2]);
	}

	@Test
	void testToString() {
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);

		String expected = "AWS [values=[1, 2, 3]]";
		String actual = aws.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	void testAWS() {
		int[] expected = {1, 2, 3};
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		x[1] = 100;
		
		int[] actual = aws.getValues();
		assertEquals(actual[0], expected[0]);
		assertEquals(actual[1], expected[1]);
	}
	
	@Test
	void testRemove() {
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		
		int value = aws.remove(-1);
		int expected = FILLER_VALUE;
		assertEquals(expected, value);
		
		 value = aws.remove(x.length + 10);
		expected = FILLER_VALUE;
		assertEquals(expected, value);
		
		value = aws.remove(0);
		assertEquals(x[0], value);
		
		int[] r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);
		
		value = aws.remove(2);
		assertEquals(r[2], value);
		
		r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);
		
	}
	
	@Test
	void testFillAndExpand() {
		int position = 1;
		int numberOfTimes = 2;
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
		int first = org[0];
 		
		int expected = originalAWS.getValues().length + numberOfTimes;
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);
		
		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(expectedValue, b);
		assertEquals(expectedValue, c);
		assertEquals(first, result[0]);
	}
	
	@Test
	void testFillAndExpandWithNegative() {
		int position = 1;
		int numberOfTimes = -2;
		
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
 		int first = org[0];
		int expected = originalAWS.getValues().length + Math.abs(numberOfTimes);
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);
		
		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(expectedValue, b);
		assertEquals(expectedValue, c);
		 
		assertEquals(first, result[0]);
	}
	
	@Test
	void testRemoveBiggerThan() {
		int[] x = {1, 1, 2, 2, 3};
		int threshold = 1;

		AWS aws = new AWS(x);
		
		int[] expectedValues = {1, 1, FILLER_VALUE, FILLER_VALUE, FILLER_VALUE};
		int expectedNumRemoved = 3;
		
		int actualNumRemoved = aws.removeBiggerThan(threshold);
		int[] actualValues = aws.getValues();
		
		assertEquals(expectedNumRemoved, actualNumRemoved);
		assertEquals(expectedValues[0], actualValues[0]);
		assertEquals(expectedValues[1], actualValues[1]);
		assertEquals(expectedValues[2], actualValues[2]);
		assertEquals(expectedValues[3], actualValues[3]);
		assertEquals(expectedValues[4], actualValues[4]);
	}

	@Test
	void testStepMultiplier() {
		int[] x = {1, 2, 3, 10, 12, 13, 20, 60, 90, 100, 101};
		AWS aws = new AWS(x);
		
		int[] expected = {2, 4, 6, 10, 48, 52, 20, 6000, 9000, 100, 101};
		
		aws.stepMultiplier();
		
		int[] actual = aws.getValues();
		
		assertEquals(expected[0], actual[0]);
		assertEquals(expected[1], actual[1]);
		assertEquals(expected[2], actual[2]);
		assertEquals(expected[3], actual[3]);
		assertEquals(expected[4], actual[4]);
		assertEquals(expected[5], actual[5]);
		assertEquals(expected[6], actual[6]);
		assertEquals(expected[7], actual[7]);
		assertEquals(expected[8], actual[8]);
		assertEquals(expected[9], actual[9]);
		assertEquals(expected[10], actual[10]);
	}
}
