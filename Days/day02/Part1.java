package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}

	public static void main(String[] args) {
//	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
		try {
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			String line;
			Integer sum = 0;
			while((line = reader.readLine()) != null) {
				String [] workingString = line.replaceAll(",", "").replaceAll(";", "").split(" ");
//				red, green, or blue
//				12 red cubes, 13 green cubes, and 14 blue cubes
				Integer id = Integer.parseInt(line.split(" ")[1].replace(":", ""));
				boolean overflow = false;
				for (int i = 0; i < workingString.length; i++) {
					if (isNumeric(workingString[i])) {
//						System.out.println("String: " + workingString[i] + " +1:" + workingString[i + 1]);
						if (workingString[i + 1].equals("blue") && Integer.parseInt(workingString[i]) > 14) {
							overflow = true;
						}
						if (workingString[i + 1].equals("red") && Integer.parseInt(workingString[i]) > 12) {
							overflow = true;
						}
						if (workingString[i + 1].equals("green") && Integer.parseInt(workingString[i]) > 13) {
							overflow = true;
						}
					}
				}
				if (overflow == false) {
					sum += id;
				}
				
			}
			System.out.println("Sum of possible ones are: " + sum);
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
