package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 {
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
				boolean allowed = false;
				for (int j = 0; j < workingString.length(); j++) {
					if (Character.isDigit(workingString.charAt(j))) {
						char belowString = 0;
						char belowRightString = 0;
						char belowLeftString = 0;
						char aboveString = 0;
						char aboveRightString = 0;
						char aboveLeftString = 0;
						char rightString = 0;
						char leftString = 0;
						if (tempNum == null) {
							tempNum = Character.toString(workingString.charAt(j));
						} else {
							tempNum += workingString.charAt(j);
						}
						
//						^
						if (i - 1 >= 0) {
//							System.out.println(data.get(i - 1));
							if (data.get(i - 1).charAt(j) != '.') {
								aboveString = data.get(i - 1).charAt(j);
								allowed = true;
							}
						}
						
//						V
						if (i + 1 <= data.size() - 1) {
//							System.out.println(data.get(i + 1));
							if (data.get(i + 1).charAt(j) != '.') {
								belowString = data.get(i + 1).charAt(j);
								allowed = true;
							}
						}
						
//						>
						if (j + 1 <= workingString.length() - 1) {
							if (workingString.charAt(j + 1) != '.' && !Character.isDigit(workingString.charAt(j + 1))) {
								rightString = workingString.charAt(j + 1);
								allowed = true;
							}
						}
						
//						<
						if (j - 1 >= 0) {
							if (workingString.charAt(j - 1) != '.' && !Character.isDigit(workingString.charAt(j - 1))) {
								leftString = workingString.charAt(j - 1);
								allowed = true;
							}
						}
						
//						>V
						if (i + 1 <= data.size() - 1 && j + 1 <= data.get(i + 1).length() - 1) {
//							System.out.println(data.get(i + 1));
							if (data.get(i + 1).charAt(j + 1) != '.' && !Character.isDigit(data.get(i + 1).charAt(j + 1))) {
								belowRightString = data.get(i + 1).charAt(j + 1);
								allowed = true;
							}
						}
						
//						>^
						if (i - 1 >= 0 && j + 1 <= data.get(i - 1).length() - 1 ) {
//							System.out.println(data.get(i + 1));
							if (data.get(i - 1).charAt(j + 1) != '.' && !Character.isDigit(data.get(i - 1).charAt(j + 1))) {
								aboveRightString = data.get(i - 1).charAt(j + 1);
								allowed = true;
							}
						}
						
//						<V
						if (i + 1 <= data.size() - 1 && j - 1 >= 0 && !Character.isDigit(data.get(i + 1).charAt(j - 1))) {
//							System.out.println(data.get(i + 1).charAt(j - 1));
							if (data.get(i + 1).charAt(j - 1) != '.') {
								belowLeftString = data.get(i + 1).charAt(j - 1);
								allowed = true;
							}
						}
						
//						<^
						if (i - 1 >= 0 && j - 1 >= 0) {
//							System.out.println(data.get(i + 1));
							if (data.get(i - 1).charAt(j - 1) != '.' && !Character.isDigit(data.get(i - 1).charAt(j - 1))) {
								aboveLeftString = data.get(i - 1).charAt(j - 1);
								allowed = true;
							}
						}
//						System.out.println("Searching " + workingString.charAt(j) + "------Allowed?: " + allowed + "-----------");
//						System.out.println(aboveLeftString + " " + aboveString + " " + aboveRightString + "\n" + leftString +" " +workingString.charAt(j) + " " + rightString + "\n" + belowLeftString + " " + belowString + " " + belowRightString );
//						System.out.println("------------------------------");
						
						if (j != workingString.length() - 1) {
							continue;
						}
					}
					if (tempNum != null && allowed == true) {
//						Means no more numbers
//						System.out.println("-> TempSum: " + tempNum);
						sum += Integer.parseInt(tempNum);
					}
					tempNum = null;
					allowed = false;
				}	
			}
			System.out.println("Sums of allowed ones are: " + sum);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
