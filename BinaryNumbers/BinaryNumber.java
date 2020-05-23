/**
 * Created by: Kevin Ha
 * I pledge my honor that I have abided by the Stevens Honor System.
 * Section: CS 284 A
 */

package BinaryNumbers;

public class BinaryNumber {
	
	private int data[];
	private int length;
	
	public BinaryNumber(int length) {
		this.length = length;
		this.data = new int[length];
	}
	//Constructor that returns the inputed binary number into integers as individual array elements
	public BinaryNumber(String str) {
		this.length = str.length();
		this.data = new int[this.length];
		
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			int val = Character.getNumericValue(c);
			data[i] = val;
		}
	}
	
	
	public int getLength() {
		return length;
	}
	
	public int[] getInnerArray() {
		return data;
	}
	
	public int getDigit(int index) {
		
		if(index > getLength()) {
			throw new IndexOutOfBoundsException("Index you have entered is out of bounds.");
		}
		
		return this.data[index];
	}
	//Returns the decimal representation of a binary numbers
	public int toDecimal() {
		
		int decimalNum = 0;
		
		for(int i = 0; i < length; i++) {
			if(data[i] == 1) {
				decimalNum += (data[i]*Math.pow(2, getLength()-1-i));
			}
		}
		return decimalNum;
	}
	//Shifts binary number based on direction and amount, -1 equals left shift, 1 equals right shift
	public void bitShift(int direction, int amount) {
		if(direction > 1 || direction < -1 || direction == 0) {
			throw new IllegalArgumentException("Entered invalid direction amount");
		}
		
		if(amount < 0) {
			throw new IllegalArgumentException("Cannot enter negative number for amount");
		}
		
		if(amount == length && direction == 1) {
			throw new IllegalArgumentException("Amount cannot be equal to the length of the array for a right shift");
		}
		
		if(direction == -1) {
			int leftArray[] = new int[length + amount];
			for(int i = 0; i < length; i++) {
				leftArray[i] = data[i];
			}
			
			this.length +=amount;
			this.data = leftArray;
			
		}
		
		else {
			int rightArray[] = new int[length - amount];
			for(int i = 0; i < rightArray.length; i++) {
				rightArray[i] = data[i];
			}
			this.length -= amount;
			this.data = rightArray;
		}
			
	}
	//Calculates the bit wise or of two binary numbers
	static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		
		int bworArray[] = new int[bn1.length];
		//Checks to see if both binary numbers are of the same length.
		if(bn1.length != bn2.length) {
			throw new IllegalArgumentException("Binary lengths must be the same");
		}
		
		else {
			for(int i = 0; i < bn1.length; i++) {
				if(bn1.data[i] == 0 && bn2.data[i] == 0) {
					bworArray[i] = 0;
				}
				else {
					bworArray[i] = 1;
				}
			}
			
		}
		
		return bworArray;
	}
	//Calculates the bitwise and of two binary numbers
	static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		
		int bwandArray[] = new int[bn1.length];
		
		if(bn1.length != bn2.length) {
			throw new IllegalArgumentException("Binary lengths must be the same");
		}
		
		else {
			for(int i = 0; i < bn1.length; i++) {
				if(bn1.data[i] == 1 && bn2.data[i] == 1) {
					bwandArray[i] = 1;
				}
				else {
					bwandArray[i] = 0;
				}
			}
			
		}
		
		return bwandArray;
	}
	//Adds two binary numbers together
	public void add(BinaryNumber aBinaryNumber) {
		int tempArray[];
		//First two if statements check to see if the lengths are the same if not prepend zeros.
		if(this.length > aBinaryNumber.length) {
			int diff = this.length - aBinaryNumber.length;
			tempArray = new int[this.length];
			for(int i = diff; i < this.length; i++) {
				tempArray[i] = aBinaryNumber.data[i - diff];
			}
			aBinaryNumber.length += diff;
			aBinaryNumber.data = tempArray;
		}
		
		if(aBinaryNumber.length > this.length) {
			int diff = aBinaryNumber.length - this.length;
			tempArray = new int[aBinaryNumber.length];
			for(int i = diff; i < aBinaryNumber.length; i++) {
				tempArray[i] = this.data[i - diff];
			}
			this.length += diff;
			this.data = tempArray;
		}
		
			int carry = 0;
			int sum[] = new int[this.length];
			
			for(int i = this.length - 1; i>= 0; i--) {
				int add = (carry + this.data[i] + aBinaryNumber.data[i]);
				int sum2 = add % 2;
				carry = add / 2;
				sum[i] = sum2;
			}
			//Special case if the last number still has a carry of one.
			if(carry == 1) {
				int sumCarry[] = new int[this.length + 1];
			
				sumCarry[0] = 1;
				
				for(int x = this.length - 1; x >= 0; x--) {
					sumCarry[x + 1] = sum[x];
				}
				
				this.length++;	
				this.data = sumCarry;
			}
			
			else {
				this.data = sum;
			}
		}		
	//Returns the string representation of the binary number
	public String toString() {
		String binN = "";
		
		for(int i = 0; i < length; i++) {
			binN += (char)(data[i] + '0');
		}
		return binN;	
	}

	public static void main(String[] args) {
	     
	}

}
