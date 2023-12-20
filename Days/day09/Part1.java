package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 {
	
	static boolean checkIfAllZeros(String[] Line) {
		boolean allZeros = true;
		
		for (int i = 0; i < Line.length; i++) {
			if (Integer.parseInt(Line[i]) != 0) {
				allZeros = false;
			}
		}
		return allZeros;
	}
	
	static String[] diffDataset(String[] currentLine) {
		ArrayList<String> diffData = new ArrayList<String>();
		
		for (int i = 1; i < currentLine.length; i++) {
			Integer previousNum = Integer.parseInt(currentLine[i - 1]);
			Integer currentNum = Integer.parseInt(currentLine[i]);
			Integer diff = currentNum - previousNum;
			diffData.add(Integer.toString(diff));
		}
		return diffData.toArray(new String[diffData.size()]);
	}
	
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            Integer Sum = 0;
            String line;
            while((line = reader.readLine()) != null) {
                ArrayList<Integer> lastNums = new ArrayList<Integer>();
            	String[] currentLine = line.split(" ");
        		lastNums.add(Integer.parseInt(currentLine[currentLine.length - 1]));
            	String[] diff = diffDataset(currentLine);
            	
            	while (!checkIfAllZeros(diff)) {
            		lastNums.add(Integer.parseInt(diff[diff.length - 1]));
            		diff = diffDataset(diff);
            	}
        		lastNums.add(Integer.parseInt(diff[diff.length - 1]));
        		
        		Integer total = 0;
//        		System.out.println("Size of lastnums : " + lastNums.size());
//        		Find next Numbers
                for (int i = lastNums.size() - 1; i >= 0; i--) {
//                	System.out.println(lastNums.get(i));
                	total += lastNums.get(i);
    			}
//        		System.out.println("Total: " + total);
        		Sum += total;
            }

            System.out.println("Sum Is: " + Sum);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}