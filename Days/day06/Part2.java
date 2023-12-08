package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part2 {
	
	public static void main(String[] args) {
//	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
		try {
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			String line;
			ArrayList<Long> Times = new ArrayList<Long>();
			ArrayList<Long> Distances = new ArrayList<Long>();
			while((line = reader.readLine()) != null) {
				if (line.startsWith("Time:")) {
					String[] timeInput = line.replace("Time:", "").split(" ");
					String FinalString = "";
					for (int i = 0; i < timeInput.length; i++) {
						if (!timeInput[i].isEmpty()) {
							FinalString += timeInput[i];
						}
					}
					Times.add(Long.parseLong(FinalString));
				}
				if (line.startsWith("Distance:")) {
					String[] distanceInput = line.replace("Distance:", "").split(" ");
					String FinalString = "";
					for (int i = 0; i < distanceInput.length; i++) {
						if (!distanceInput[i].isEmpty()) {
							FinalString += distanceInput[i];
						}
					}
					Distances.add(Long.parseLong(FinalString));
				}
			}
			
			Integer Total = 1;
			
			Integer sum = 0;
			
			for (int i = 0; i < Times.size(); i++) {
//				one millimeter per millisecond
				Integer millimeters = 0;
				Integer ms = 0;
				
				Long Time = Times.get(i);
				Long Distance = Distances.get(i);
				
//				Integer lastOneUsed = Distance - Time;
//				
//				Integer lastGoesDown = Distance - lastOneUsed;
//				
//				System.out.println("Lastou: " + lastOneUsed + " Lastgd: " + lastGoesDown);
				for (int j = 1; j < Time; j++) {
					if (j * (Time - j)  > Distance) {
						sum += 1;
					}
				}
				System.out.println("Game: " + i +  " Sum of possible Distances: " + sum);
				Total *= sum;
				sum=0;
			}
			System.out.println("Total: " + Total);
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
