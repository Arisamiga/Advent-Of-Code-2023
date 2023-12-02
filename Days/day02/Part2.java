package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
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
				Integer red = 0;
				Integer green = 0;
				Integer blue = 0;
				for (int i = 0; i < workingString.length; i++) {
					if (isNumeric(workingString[i])) {
//						System.out.println("String: " + workingString[i] + " +1:" + workingString[i + 1]);
						if (workingString[i + 1].equals("blue") && Integer.parseInt(workingString[i]) > blue) {
							blue = Integer.parseInt(workingString[i]);
						}
						if (workingString[i + 1].equals("red") && Integer.parseInt(workingString[i]) > red) {
							red = Integer.parseInt(workingString[i]);
						}
						if (workingString[i + 1].equals("green") && Integer.parseInt(workingString[i]) > green) {
							green = Integer.parseInt(workingString[i]);
						}
					}
				}
//				System.out.println("Red: " + red + " Green: " + green + " Blue: " + blue);
				sum += red * green * blue;
				
			}
			System.out.println("Sum of Minimum cubes needed are: " + sum);
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
