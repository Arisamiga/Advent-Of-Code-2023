package day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Part2 {
	static class Pair {
	    String Main;
	    String Left;
	    String Right;
	    
	    public Pair(String Main, String Left, String Right) {
	        this.Main = Main;
	        this.Left = Left;
	        this.Right = Right;
	    }
	}
	
	private static long gcd(long a, long b)
	{
	    while (b > 0)
	    {
	        long temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}

	private static long lcm(long a, long b)
	{
	    return a * (b / gcd(a, b));
	}
	
	private static long lcm(ArrayList<Long> steps)
	{
	    long result = steps.get(0);
	    for(int i = 1; i < steps.size(); i++) result = lcm(result, steps.get(i));
	    return result;
	}
	
	static int returnSteps(Map<String, Pair> pairs, ArrayList<Character> instructions, String Start) {
        String LookingforArea = Start;
        int steps = 0;
        int currentInstruction = 0;
        boolean Finished = false;

        while (!Finished) {
            
            Pair current = pairs.get(LookingforArea);
              
            String area = current.Main;
            if (area.equals(LookingforArea)) {
                if (currentInstruction == instructions.size()) currentInstruction = 0;
   
                
                if (area.endsWith("Z")) {
                    Finished = true;
                    break;
                }
                if (instructions.get(currentInstruction) == 'L') {
                    LookingforArea = current.Left.replaceAll(" ", "");
                    currentInstruction++;
                    steps++;
                }
                else if (instructions.get(currentInstruction) == 'R') {
                    LookingforArea = current.Right.replaceAll(" ", "");
                    currentInstruction++;
                    steps++;
                }
            }
        }
        return steps;
	}
	
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            ArrayList<String> lines = new ArrayList<String>();
            ArrayList<Character> instructions = new ArrayList<>();
            Map<String, Pair> pairs = new LinkedHashMap<>();

            String lineRead;
            while((lineRead = reader.readLine()) != null) {
                lines.add(lineRead);
                
                if(lineRead.contains("=")) {
                    String area = lineRead.split(" ")[0];
                    String leftValue = lineRead.split("[\\(\\,\\)]")[1];
                    String rightValue = lineRead.split("[\\(\\,\\)]")[2];
                    
                    pairs.put(area, new Pair(area,leftValue,rightValue));
                }
            }
            
            for (int i = 0; i < lines.get(0).length(); i++) {
                if (lines.get(0).charAt(i) == ' ') continue;
                instructions.add(lines.get(0).charAt(i));
            }
            
           long startTime = System.nanoTime();
            
           ArrayList<Long> steps = new ArrayList<Long>();
            
            for (String element : pairs.keySet()) {
				
				if (element.endsWith("A")) {
		            int result = returnSteps(pairs, instructions, element);
					steps.add((long) result);
				}
			}

            long results = lcm(steps);
            System.out.println("Paths lead to: " + results + " Steps");
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);  // compute the duration in nanoseconds
            System.out.println("Execution time in nanoseconds: " + duration);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}