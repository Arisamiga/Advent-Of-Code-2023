package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Part2 {
	
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
		
		public Card(String Holding, Integer Power, Integer Bid) {
			this.Holding = Holding;
			this.Power = Power;
			this.Bid = Bid;
		}
	}

	public static int evaluateHandPower(String cards) {
	    ArrayList<CardLetters> letters = new ArrayList<CardLetters>();

	    for (int i = 0; i < cards.length(); i++) {
	        final int finalI = i;

	        if (letters.stream().anyMatch(letterpos -> letterpos.letter == cards.charAt(finalI))){
	            letters.stream().filter(letterpos -> letterpos.letter == cards.charAt(finalI)).forEach(letterpos -> letterpos.appears++);
	        } else {
	            letters.add(new CardLetters(cards.charAt(i),1));
	        }
	    }

	    HashSet<CardLetters> lettersSet = new HashSet<CardLetters>(letters);
	    letters.clear();
	    letters.addAll(lettersSet);

	    Collections.sort(letters, new Comparator<CardLetters>() {
	        public int compare(CardLetters s1, CardLetters s2) {
	            return Integer.compare(s2.appears, s1.appears);
	        }
	    });

	    // Evaluate the power of the hand
	    if (cards.matches("^(.)\\1{4}$")) {
	        return 7;
	    }
	    if (letters.stream().anyMatch(letterpos -> letterpos.appears == 5)) {
	        return 7;
	    }
	    if (letters.stream().anyMatch(letterpos -> letterpos.appears == 4)) {
	        return 6;
	    }
	    if (letters.stream().anyMatch(letterpos -> letterpos.appears == 3) && letters.stream().anyMatch(letterpos -> letterpos.appears == 2)) {
	        return 5;
	    }
	    if (letters.stream().anyMatch(letterpos -> letterpos.appears == 3)) {
	        return 4;
	    }
	    if (letters.stream().filter(letterpos -> letterpos.appears == 2).count() == 2) {
	        return 3;
	    }
	    if (letters.stream().anyMatch(letterpos -> letterpos.appears == 2)) {
	        return 2;
	    }

	    return 1;
	}
	
	
	
	public static Integer getPowerLevel(String Cards) {
//	7	Five of a kind, where all five cards have the same label: AAAAA
//	6	Four of a kind, where four cards have the same label and one card has a different label: AA8AA
//	5	Full house, where three cards have the same label, and the remaining two cards share a different label: 23332
//	4	Three of a kind, where three cards have the same label, and the remaining two cards are each different from any other card in the hand: TTT98
//	3	Two pair, where two cards share one label, two other cards share a second label, and the remaining card has a third label: 23432
//	2	One pair, where two cards share one label, and the other three cards have a different label from the pair and each other: A23A4
//	1	High card, where all cards' labels are distinct: 23456

		final String Card = Cards;
		
		ArrayList<CardLetters> letters = new ArrayList<CardLetters>();
		
		Integer jokers = 0;
		
		for (int i = 0; i < Card.length(); i++) {
			final int finalI = i;
			
			if (Card.charAt(i) == 'J') {
				jokers++;

			}
		}
		
		HashSet<CardLetters> lettersSet = new HashSet<CardLetters>(letters);
		letters.clear();
		letters.addAll(lettersSet);
		
		Collections.sort(letters, new Comparator<CardLetters>() {
			public int compare(CardLetters s1, CardLetters s2) {
				return Integer.compare(s2.appears, s1.appears);
			}
		});
		
		
	    if (jokers > 0) {
	        int highestPower = 0;
	        char[] cardValues = {'A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J'};

	        // Generate all possible combinations of card values for the number of jokers
	        List<List<Character>> combinations = generateCombinations(cardValues, jokers);

	        for (List<Character> combination : combinations) {
	            String replacedCards = Cards;
	            for (char cardValue : combination) {
	                replacedCards = replacedCards.replaceFirst("J", String.valueOf(cardValue));
	            }

	            // Evaluate the power of the hand with the jokers replaced by the current combination of card values
	            int power = evaluateHandPower(replacedCards); 

	            if (power > highestPower) {
	                highestPower = power;
	            }
	        }

	        // Return the highest power found
	        return highestPower;
	    } else {
	        int power = evaluateHandPower(Cards);
	        return power;
	    }
	}

	// Helper method to generate all possible combinations of a given size from a given array
	public static List<List<Character>> generateCombinations(char[] array, int size) {
	    List<List<Character>> combinations = new ArrayList<>();
	    generateCombinations(array, size, 0, new ArrayList<Character>(), combinations);
	    return combinations;
	}

	private static void generateCombinations(char[] array, int size, int start, List<Character> combination, List<List<Character>> combinations) {
	    if (combination.size() == size) {
	        combinations.add(new ArrayList<>(combination));
	        return;
	    }
	    for (int i = start; i < array.length; i++) {
	        combination.add(array[i]);
	        generateCombinations(array, size, i, combination, combinations);
	        combination.remove(combination.size() - 1);
	    }
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
			powerMap.put("J", 0);

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
