package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;



public class Part1 {
	
	static class CardLetters {
		char letter;
		Integer appears;
		
		public CardLetters(char letter,Integer appears) {
			this.letter = letter;
			this.appears = appears;
		}
	}

	static class Card {
		String Holding;
		Integer Power;
		Integer Bid;
		
		Integer Rank;
		
		public Card(String Holding, Integer Power, Integer Bid) {
			this.Holding = Holding;
			this.Power = Power;
			this.Bid = Bid;
//			this.Rank = Rank;
		}
	}
	
	
	public static Integer getPowerLevel(String Cards) {
//	7	Five of a kind, where all five cards have the same label: AAAAA
//	6	Four of a kind, where four cards have the same label and one card has a different label: AA8AA
//	5	Full house, where three cards have the same label, and the remaining two cards share a different label: 23332
//	4	Three of a kind, where three cards have the same label, and the remaining two cards are each different from any other card in the hand: TTT98
//	3	Two pair, where two cards share one label, two other cards share a second label, and the remaining card has a third label: 23432
//	2	One pair, where two cards share one label, and the other three cards have a different label from the pair and each other: A23A4
//	1	High card, where all cards' labels are distinct: 23456

		

		
//		Five of a kind
		if (Cards.matches("^(.)\\1{4}$")) {
			return 7;
		}
		
		ArrayList<CardLetters> letters = new ArrayList<CardLetters>();
		
		
		for (int i = 0; i < Cards.length(); i++) {
			final int finalI = i;
			if (letters.stream().anyMatch(letterpos -> letterpos.letter == Cards.charAt(finalI))){
				letters.stream().filter(letterpos -> letterpos.letter == Cards.charAt(finalI)).forEach(letterpos -> letterpos.appears++);
			} else {
				letters.add(new CardLetters(Cards.charAt(i),1));
			}
		}

//		Four of a kind
		if (letters.stream().anyMatch(letterpos -> letterpos.appears == 4)) {
			return 6;
		}

//		Full house
		if (letters.stream().anyMatch(letterpos -> letterpos.appears == 3) && letters.stream().anyMatch(letterpos -> letterpos.appears == 2)) {
			return 5;
		}

//		Three of a kind
		if (letters.stream().anyMatch(letterpos -> letterpos.appears == 3)) {
			return 4;
		}

//		Two pair
		if (letters.stream().filter(letterpos -> letterpos.appears == 2).count() == 2) {
			return 3;
		}

//		One pair
		if (letters.stream().anyMatch(letterpos -> letterpos.appears == 2)) {
			return 2;
		}
		
		return 1;
	}
	
	public static void main(String[] args) {
//	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			String line;
			ArrayList<Card> Cards = new ArrayList<Card>();
			
			
			while((line = reader.readLine()) != null) {
				String[] inputSplit = line.split(" ");
				
				String Card = inputSplit[0];
				String BidAmount = inputSplit[1];
				Cards.add(new Card(Card, getPowerLevel(Card), Integer.parseInt(BidAmount)));
			}
			
			HashSet<Card> cardSet = new HashSet<Card>(Cards);
			Cards.clear();
			Cards.addAll(cardSet);
			
			HashMap<String, Integer> powerMap = new HashMap<>();
			powerMap.put("A", 13);
			powerMap.put("K", 12);
			powerMap.put("Q", 11);
			powerMap.put("J", 10);
			powerMap.put("T", 9);
			powerMap.put("9", 8);
			powerMap.put("8", 7);
			powerMap.put("7", 6);
			powerMap.put("6", 5);
			powerMap.put("5", 4);
			powerMap.put("4", 3);
			powerMap.put("3", 2);
			powerMap.put("2", 1);

			Collections.sort(Cards, new Comparator<Card>() {
			    public int compare(Card s1, Card s2) {
			        int powerComparison = Integer.compare(s1.Power, s2.Power);
			        if (powerComparison != 0) {
			            return powerComparison;
			        } else {
			            for (int i = 0; i < Math.min(s1.Holding.length(), s2.Holding.length()); i++) {
			                int comparison = Integer.compare(powerMap.get(String.valueOf(s1.Holding.charAt(i))), powerMap.get(String.valueOf(s2.Holding.charAt(i))));
			                if (comparison != 0) {
			                    return comparison;
			                }
			            }
			            return Integer.compare(s1.Holding.length(), s2.Holding.length());
			        }
			    }
			});

			Integer totalWinnings = 0;
			for (int i = 0; i < Cards.size(); i++) {
				totalWinnings += Cards.get(i).Bid * (i + 1);
			}
			
			System.out.println("Total Winnings are: " + totalWinnings);
			
			
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
