package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Part2 {
	
	static class Seed {
		long Start;
		long Range;
		String type;
		public Seed(long Start, long Range, String type) {
			this.Start = Start;
			this.Range = Range;
			this.type = type;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Seed seed = (Seed) o;
			return Start == seed.Start &&
				Objects.equals(type, seed.type);
		}

		@Override
		public int hashCode() {
			return Objects.hash(Start, type);
		}
	}

	static class Map {
		long Destination;
		long Source;
		long range;
		String type;
		
		public Map(long Destination,long Source, long range, String type) {
			this.Destination = Destination;
			this.Source = Source;
			this.range = range;
			this.type = type;
		}
		
		public boolean contains(Seed seed) {
			return (
					(this.Source <= seed.Start && seed.Start < (this.Source + this.range))
					|| (this.Source <= seed.Range && seed.Range < (this.Source + this.range)));
		}
		public Long[] translate(Seed seed) {
			Long offsetStart = seed.Start - this.Source;
			Long offsetEnd = (seed.Start + seed.Range) - this.Source;
			Long[] result = {
					this.Destination + offsetStart,
					this.Destination + offsetEnd
					};
			return result;
		}
	}
	
	public static void main(String[] args) {
//	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		ArrayList<Seed> Seeds = new ArrayList<Seed>();
		ArrayList<Map> currentMap = new ArrayList<Map>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			String line;
			while((line = reader.readLine()) != null) {
				if (line.startsWith("seeds:")) {
					String[] seedList = line.split(" ");
					
					for (int i = 1; i < seedList.length; i += 2) {
						Seeds.add(new Seed(Long.parseLong(seedList[i]), Long.parseLong(seedList[i]) + Long.parseLong(seedList[i + 1]), "seed"));
//						System.out.println("Added Seeds");
					}
				}
				
				if (line.endsWith("map:")){
					String row;
					String type = line.split("-")[line.split("-").length - 1].replace(" map:", "");

					
					currentMap.clear();
//					System.out.println(type);
					while ((row = reader.readLine()) != null && !row.isEmpty()) {
						String[] mapRow = row.split(" ");
						currentMap.add(new Map(Long.parseLong(mapRow[0]), Long.parseLong(mapRow[1]), Long.parseLong(mapRow[2]), type));
					}
						ArrayList<Seed> nextSeeds = new ArrayList<Seed>();
						int i = 0;
						while (i < Seeds.size()) {
							if (Seeds.get(i).type.equals(type)) {
								System.out.println("Same type??");
								
								i++;
								continue;
							}
//							System.out.println("Types: " + Seeds.get(i).type + " -> " + type);
							
							Boolean mapped = false;
							
							for (Map current: currentMap) {
								if (current.contains(Seeds.get(i))) {
									mapped = true;
									if (Seeds.get(i).Start < current.Source) {
										Seeds.add(new Seed(Seeds.get(i).Start, Seeds.get(i).Start - 1, Seeds.get(i).type));
										Seeds.set(i, new Seed(current.Source, Seeds.get(i).Range, Seeds.get(i).type));
									}
									if (Seeds.get(i).Range > (current.Source + current.range - 1)) {
										Seeds.add(new Seed((current.Source + current.range - 1) + 1, Seeds.get(i).Range, Seeds.get(i).type));
										Seeds.set(i, new Seed(Seeds.get(i).Start, (current.Source + current.range - 1), Seeds.get(i).type));
									}
							
									Long[] currentTranslation = current.translate(Seeds.get(i));
									nextSeeds.add(new Seed(currentTranslation[0], currentTranslation[1], type));
									break;
								}
							}
							
							if (mapped == false) {
								nextSeeds.add(new Seed(Seeds.get(i).Start, Seeds.get(i).Range, type));
							}
							
							i++;
						}
						Seeds = nextSeeds;
				}
			}
			
			HashSet<Seed> seedSet = new HashSet<Seed>(Seeds);
			Seeds.clear();
			Seeds.addAll(seedSet);
			
			Collections.sort(Seeds, new Comparator<Seed>() {
				public int compare(Seed s1, Seed s2) {
					return Long.compare(s1.Start, s2.Start);
				}
			});
			
			try {
			    PrintWriter writer = new PrintWriter("results.txt", "UTF-8");
			    for (Seed seed : Seeds) {
			        if(seed.Start >= 0 && seed.Range >= 0) {
			            writer.println(seed.Start);
			        }
			    }

			    writer.close();
			} catch (IOException e) {
			    System.out.println("An error occurred while writing to the file.");
			    e.printStackTrace();
			}
//			System.out.println("Lowest: " + Lowest);
//			System.out.println("Seed: Start " + LowestSeed.Start + " Range: " + LowestSeed.Range + " Type: " + LowestSeed.type);
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
