package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {

	public static void main(String[] args) {
//	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
		try {
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			String line;
			Integer sum = 0;
			String[] nums = {
					"",
					"one",
					"two",
					"three",
					"four",
					"five",
					"six",
					"seven",
					"eight",
					"nine"	
			};
			
			while((line = reader.readLine()) != null) {
				String workingString = line;
				char first = ' ';
				char last = ' ';
				for (int i = 1; i < nums.length; i++) {
//					System.out.println("Nums: " + nums[i] + " i: " + i);
					workingString = workingString.replaceAll(nums[i], nums[i].charAt(0) + Integer.toString(i) + nums[i].charAt(nums[i].length() - 1)) ;
				}
//				System.out.println(workingString);
				for (int i = 0; i < workingString.length(); i++) {
//					System.out.println(workingString.charAt(i) + " ");
					if (Character.isDigit(workingString.charAt(i))) {
//						System.out.println(workingString.charAt(i));
						if (first == ' ') {
							first = workingString.charAt(i);
						}
						last = workingString.charAt(i);
					}
				}
				if (first != ' ' && last != ' ') {
					String tempSum = new StringBuilder().append(first).append(last).toString();
					sum += Integer.parseInt(tempSum);
				}
			}
			
			System.out.println("The Sum is: " + sum);
			
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
