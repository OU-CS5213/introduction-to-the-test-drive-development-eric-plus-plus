import java.util.Arrays;

public class AWS {
	private static final int FILLER_VALUE = Integer.MIN_VALUE;
	private int[] values;
	
	public String greeting() {
		return "Hello";
	}

	public int[] getValues() {
		return Arrays.copyOf(values, values.length);
	}

	public void setValues(int[] values) {
		this.values = Arrays.copyOf(values, values.length);
	}

	@Override
	public String toString() {
		return "AWS [values=" + Arrays.toString(values) + "]";
	}

	public AWS(int[] values) {
		super();
		setValues(values);
	}

	public int remove(int i) {

		int value = FILLER_VALUE;
		if (i >= 0 && i < values.length) {
			value = values[i];
			for(int index = i; index < values.length - 1; ++index) {
				values[index] = values[index+1];
			}
			values[values.length-1] = FILLER_VALUE;
		}
		return value;
	}

	public void fillAndExpand(int position, int nt) {
		int numberOfTimes = Math.abs(nt);
		int[] newArray = new int[values.length + numberOfTimes];
		for(int i = 0; i <=position; ++i) {
			newArray[i] = values[i];
		}
		for(int i = position; i<=numberOfTimes + position; ++i) {
			newArray[i] = newArray[position];
		}
		// Make examples, so it is easier to visualize your methods
		//   p 1 2
		// a b c
		// a b b b c
		
		int x = numberOfTimes;
		for(int i = position+1; i <values.length; ++i ) {
			newArray[i+x] = values[i];
		}
		values = newArray;
	}
	
	public int removeBiggerThan(int threshold) {
		// remove all values bigger than a threshold that you send
		// return the number of removed values
		// use FILLER_VALUE to replace removed values
		int numRemoved = 0;
		
		for(int i = 0; i < values.length; ++i) {
			if(values[i] > threshold) {
				++numRemoved;
				values[i] = FILLER_VALUE;
			}
		}
		
		return numRemoved;
	}
	
	public void stepMultiplier() {
		// multiply all values less than 10 by 2,
		// and all values that are less than 20 and bigger than 10 by 4,
		// and all values that are less than 100 and bigger than 20 by 100
		// (these rules exclude the values 10, 20, and 100)
		for(int i = 0; i < values.length; ++i) {
			if(values[i] < 10) {
				values[i] *= 2;
			}
			else if(values[i] < 20 && values[i] > 10) {
				values[i] *= 4;
			}
			else if(values[i] < 100 && values[i] > 20) {
				values[i] *= 100;
			}
		}
	}
}
