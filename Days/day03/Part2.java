package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part2 {
	
	public static Integer returnNumber(String WholeString, Integer index) {
		String workingString = WholeString;
		String Result = "";
		Integer pos = index;
		
//		System.out.println(pos);
		while (Character.isDigit(workingString.charAt(pos))) {
			if(pos == 0) {
				break;
			}
			pos--;
		}
		boolean done = false;
		if (!Character.isDigit(workingString.charAt(pos)) ) {
//			System.out.println("Enter pos");
			pos++;
		}
		while (!done) {
//			System.out.println("DBG: In done: " + workingString.charAt(pos));
			if (Character.isDigit(workingString.charAt(pos))){
				Result += workingString.charAt(pos);
				if (pos != workingString.length() - 1) {
					pos++;
					continue;
				}
			}
			done = true;
		}
//		System.out.println("Results: " + Result);
		if (Result == "") {
			System.out.println("Oh no: " + WholeString + " Index: " + index);
		}
		return Integer.parseInt(Result);
	}
	
	public static void main(String[] args) {
//	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
		ArrayList<String> data = new ArrayList<String>();
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			String line;
			Integer sum = 0;
			while((line = reader.readLine()) != null) {
				data.add(line);
			}
			reader.close();
			
			for (int i = 0; i < data.size(); i++) {
				String workingString = data.get(i);
//				System.out.println(workingString);
				String tempNum = null;
//				System.out.println("-> " + i);
				for (int j = 0; j < workingString.length(); j++) {
					boolean[] allowed = {true, true, true,
		                      true, true, true,
		                      true, true, true};
//					System.out.println(" " + j);
					if (workingString.charAt(j) == '*') {
//						System.out.println("In");
						char belowString = 0;
						char belowRightString = 0;
						char belowLeftString = 0;
						char aboveString = 0;
						char aboveRightString = 0;
						char aboveLeftString = 0;
						char rightString = 0;
						char leftString = 0;
						
						Integer Full = 0;
						
						ArrayList<Integer> nums = new ArrayList<Integer>();
//						if (tempNum == null) {
//							tempNum = Character.toString(workingString.charAt(j));
//						} else {
//							tempNum += workingString.charAt(j);
//						}
						
//						^
						if (i - 1 >= 0) {
//							System.out.println(data.get(i - 1));
							if (data.get(i - 1).charAt(j) != '.' && Character.isDigit(data.get(i - 1).charAt(j))) {
								aboveString = data.get(i - 1).charAt(j);
								if (Full != 2 && allowed[1]) {
									nums.add(returnNumber(data.get(i - 1), j));
									allowed[0] = false;
									allowed[2] = false;
									Full++;
								}
							}
						}
						
//						V
						if (i + 1 <= data.size() - 1) {
//							System.out.println(data.get(i + 1));
							if (data.get(i + 1).charAt(j) != '.' && Character.isDigit(data.get(i + 1).charAt(j))) {
								belowString = data.get(i + 1).charAt(j);
								if (Full != 2 && allowed[7]) {
									nums.add(returnNumber(data.get(i + 1), j));
									allowed[6] = false; 
									allowed[8] = false;
									Full++;
								}
							}
						}
						
//						>
						if (j + 1 <= workingString.length() - 1) {
							if (workingString.charAt(j + 1) != '.' && Character.isDigit(workingString.charAt(j + 1))) {
								rightString = workingString.charAt(j + 1);
								if (Full != 2 && allowed[5]) {
									nums.add(returnNumber(workingString, j + 1));
									Full++;
								}
							}
						}
						
//						<
						if (j - 1 >= 0) {
							if (workingString.charAt(j - 1) != '.' && Character.isDigit(workingString.charAt(j - 1))) {
								leftString = workingString.charAt(j - 1);
								if (Full != 2 && allowed[3]) {
									nums.add(returnNumber(workingString, j - 1));
									Full++;
								}
							}
						}
						
//						>V
						if (i + 1 <= data.size() - 1 && j + 1 <= data.get(i + 1).length() - 1) {
//							System.out.println(data.get(i + 1));
							if (data.get(i + 1).charAt(j + 1) != '.' && Character.isDigit(data.get(i + 1).charAt(j + 1))) {
								belowRightString = data.get(i + 1).charAt(j + 1);
								if (Full != 2 && allowed[8]) {
//									System.out.println(returnNumber(data.get(i + 1), j + 1));
									nums.add(returnNumber(data.get(i + 1), j + 1));
									Full++;
								}
							}
						}
						
//						>^
						if (i - 1 >= 0 && j + 1 <= data.get(i - 1).length() - 1 ) {
//							System.out.println(data.get(i + 1));
							if (data.get(i - 1).charAt(j + 1) != '.' && Character.isDigit(data.get(i - 1).charAt(j + 1))) {
								aboveRightString = data.get(i - 1).charAt(j + 1);
								if (Full != 2 && allowed[2]) {
									nums.add(returnNumber(data.get(i - 1), j + 1));
									Full++;
								}
							}
						}
//						<^
						if (i - 1 >= 0 && j - 1 >= 0) {
//							System.out.println(data.get(i + 1));
							if (data.get(i - 1).charAt(j - 1) != '.' && Character.isDigit(data.get(i - 1).charAt(j - 1))) {
								aboveLeftString = data.get(i - 1).charAt(j - 1);
								if (Full != 2 && allowed[0]) {
									nums.add(returnNumber(data.get(i - 1), j - 1));
									Full++;
								}
							}
						}
						
//						<V
						if (i + 1 <= data.size() - 1 && j - 1 >= 0 && Character.isDigit(data.get(i + 1).charAt(j - 1))) {
//							System.out.println(data.get(i + 1).charAt(j - 1));
							if (data.get(i + 1).charAt(j - 1) != '.') {
								belowLeftString = data.get(i + 1).charAt(j - 1);
								if (Full != 2 && allowed[6]) {
									nums.add(returnNumber(data.get(i + 1), j - 1));
									Full++;
								}
							}
						}
			
						
//						System.out.println("Searching " + workingString.charAt(j) + "------Allowed?: " + allowed + "-----------");
//						System.out.println(aboveLeftString + " " + aboveString + " " + aboveRightString + "\n" + leftString +" " +workingString.charAt(j) + " " + rightString + "\n" + belowLeftString + " " + belowString + " " + belowRightString );
//						System.out.println("------------------------------");
						if (Full == 2) {
//							System.out.println("Temp: " + tempString);
//							System.out.println("DBG Num: " + nums.size());
							Integer tempsum = nums.get(0) * nums.get(1);
							sum += tempsum;
						}
					}
				}	
			}
			System.out.println("Sums of allowed ones are: " + sum);
			
//			System.out.println("Connected Gears: " + connected);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
