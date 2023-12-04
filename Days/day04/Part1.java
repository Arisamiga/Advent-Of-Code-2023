package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 {
	public static void main(String[] args) {
//	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
		try {
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			String line;
			Integer sum = 0;
			while((line = reader.readLine()) != null) {
//				System.out.println(line.split(":")[0].replace("Card ",""));
				Integer Result = 1;
				String Numbers = line.split(": ")[1];
				String[] LeftSide = Numbers.split("\\|")[0].split(" ");
				String[] RightSide = Numbers.split("\\|")[1].split(" ");
				boolean foundFirst = false;
				
				
				ArrayList<Integer> winningNumbers = new ArrayList<Integer>();
				
				ArrayList<Integer> ourNumbers = new ArrayList<Integer>();
				
				
				for (int i = 0; i < LeftSide.length; i++) {
					if (LeftSide[i] == "") continue;
					winningNumbers.add(Integer.parseInt(LeftSide[i]));
					
				}
				for (int i = 0; i < RightSide.length; i++) {
					if (RightSide[i] == "") continue;
					ourNumbers.add(Integer.parseInt(RightSide[i]));
					
				}
				
				for (Integer integer : ourNumbers) {
					if (winningNumbers.contains(integer)) {
						if (!foundFirst) {
							foundFirst = true;
							continue;
						}
						Result *= 2;
//						System.out.println("Integer: " + integer + " Is in the winning.");
					}
				};
				if (foundFirst) {
					sum += Result;
				}
			}
			System.out.println("Sum of winning numbers: " + sum );
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
