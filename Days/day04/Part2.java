package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part2 {

	public static void main(String[] args) {
//	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
		try {
//			===== Initialize ArrayList ====
			
			Integer NumLines = 0;
			ArrayList<Integer> Cards = new ArrayList<Integer>();
			BufferedReader LineReader = new BufferedReader(new FileReader("input.txt"));
			
			while (LineReader.readLine() != null) NumLines++;
			
			LineReader.close();
			Cards.add(0);
			for (int i = 0; i < NumLines; i++) {
				Cards.add(1);
			}
			
//			===== End of Initialize ArrayList ====
			String line;
			Integer sum = 0;
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			
			while((line = reader.readLine()) != null) {
				Integer ID = Integer.parseInt(line.split(":")[0].split(" ")[line.split(":")[0].split(" ").length - 1]);
//				System.out.println("ID: " + ID);
				Integer Result = 0;
				String Numbers = line.split(": ")[1];
				String[] LeftSide = Numbers.split("\\|")[0].split(" ");
				String[] RightSide = Numbers.split("\\|")[1].split(" ");
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
						Result += 1;
//						System.out.println("Integer: " + integer + " Is in the winning.");
					}
				};
				if (Result != 0) {
//					System.out.println("------ID: " + ID);
//					System.out.println("Result: " + Result);
					for (int i = 1; i < Result + 1; i++) {
//						System.out.println("-> Itterating: " +  i);
						Cards.set(ID + i, Cards.get(ID + i) + 1);
						
						if (Cards.get(ID) > 1) {
							for (int j = 1; j < Cards.get(ID); j++) {
								Cards.set(ID + i, Cards.get(ID + i) + 1);
							}
						}
//						System.out.println("   -> " + (ID + i));
					}
//					System.out.println("------------");
				}
			}
			for (int i = 1; i < Cards.size(); i++) {
//				System.out.println("Card Size: " + Cards.get(i));
				sum += Cards.get(i);
			}
			
			System.out.println("Sum of Scratchcards: " + sum );
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
