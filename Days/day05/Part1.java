package day05;

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
			ArrayList<Long> seeds = new ArrayList<Long>();
			
			ArrayList<Boolean> done = new ArrayList<Boolean>();
			
			boolean insideMap = false;
			while((line = reader.readLine()) != null) {
				if (line.startsWith("seeds:")) {
					String[] seedList = line.split(" ");
					for (int i = 1; i < seedList.length; i++) {
						seeds.add(Long.parseLong(seedList[i]));
						done.add(false);
					}
				}
				if (line.endsWith("map:")) {
					insideMap = true;
					continue;
				}
				if (insideMap == true && line.isEmpty()) {
					insideMap = false;
					for (int i = 0; i < seeds.size(); i++) {
						done.set(i, false);
					}
					continue;
				}
				if (insideMap == true) {
//					System.out.println(line);
					String[] mapValues = line.split(" ");
					Long from = Long.parseLong(mapValues[1]);
					Long to = Long.parseLong(mapValues[0]);
					Long range = Long.parseLong(mapValues[2]);
					for (int i = 0; i < seeds.size(); i++) {
						Long seed = seeds.get(i);
						if (seed >= from && seed < from + range && done.get(i) != true) {
//						System.out.println("map: from: " + from + " to: " + to + " range: " + range + " | " + seed +" -> "+(seed - from + to));
							seeds.set(i, (seed - from + to));
							done.set(i, true);
						}
					}
				}

			}
			Long lowest = null;
			for (int i = 0; i < seeds.size(); i++) {
				if (lowest == null || seeds.get(i) < lowest) {
					lowest = seeds.get(i);
				}
			}
			System.out.println("Lowest location is: " + lowest);
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
