

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimPa {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static String currentState;
	private static List<String> stack = new LinkedList<>();
	private static Map<String, Map<String, Map<String, String>>> map = new HashMap<>();
	private static List<String> outList = new LinkedList<String>();

	public static void main(String[] args) {

		String setupLines[] = new String[7];
		try {
			for (int i = 0; i < 7; i++) {
				setupLines[i] = reader.readLine();
			}
			String[] states = setupLines[1].split(",");

			String input = reader.readLine();

			// citam unos
			do {
				if (input.length() == 0) {
					break;
				}
				try {
					String[] temp = input.split("->");
					String[] stateInput = temp[0].split(",");
					String[] nextStates = temp[1].split(",");
					if (!map.containsKey(stateInput[0])) {
						map.put(stateInput[0], new HashMap<>());
					}
					if (!map.get(stateInput[0]).containsKey(stateInput[1])) {
						map.get(stateInput[0]).put(stateInput[1], new HashMap<>());
					}
					map.get(stateInput[0]).get(stateInput[1]).put(stateInput[2], nextStates[0] + "," + nextStates[1]);

				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Podatci su pogresno uneseni!");
					System.exit(-1);
				}
				input = reader.readLine();
			} while (input != null && !input.equals(""));

			// Krecem analiztirat input
			String[] inputArr = setupLines[0].split("\\|");
			StringBuilder oString = new StringBuilder(); // Cijeli output jednog seta inputa
			int inCount;
			for (String inputs : inputArr) {
				String[] s = inputs.split(",");
				inCount = 0;
				currentState = setupLines[5];
				stack.clear();
				for (char c : new StringBuilder(setupLines[6]).toString().toCharArray()) {
					stack.add(Character.toString(c));
				}
				oString = new StringBuilder();
				oString.append(currentState + "#" + setupLines[6] + "|"); // Pocetna stanje i pocetni znak na stogu

				for (String s2 : s) {

					doEpsilon(oString, inCount, s, setupLines[4]);
					if (stack.size() > 0) {
						String tmp = stack.get(0);
						stack = stack.subList(1, stack.size());
						if(!map.containsKey(currentState)) {
							oString.append("fail|0");
							break;
						}
						if ((!map.get(currentState).containsKey(s2) || !map.get(currentState).get(s2).containsKey(tmp))) {
							oString.append("fail" + "|" + "0");
							break;
						}
				
						String[] newOut = map.get(currentState).get(s2).get(tmp).split(",");

						currentState = newOut[0];
						if (newOut.length > 1 && !newOut[1].equals("$")) {
							for(char c : new StringBuilder(newOut[1]).reverse().toString().toCharArray()) {
								stack.add(0, Character.toString(c));
							}
						}

						if (inCount == s.length - 1 && setupLines[4].contains(currentState)) {
							oString.append(currentState + "#" + listToString(stack) + "|" + "1");

							break;
						} else if (inCount == s.length - 1 && !setupLines[4].contains(currentState)) {

							oString.append(currentState + "#" + listToString(stack) + "|");
							doEpsilon(oString, inCount, s, setupLines[4]);
							if(setupLines[4].contains(currentState)) {
								oString.append("1");
							} else {
								oString.append("0");
							}
							break;

						} else {
							oString.append(currentState + "#" + listToString(stack) + "|");
						}
					} else {
						if(inCount < s.length) {
							oString.append("fail|0");
							break;
						}
						if (inCount == s.length - 1 && setupLines[4].contains(currentState)) {
							oString.append(currentState + "#" + "$" + "|" + "1");

							break;
						} else if (inCount == s.length - 1 && !setupLines[4].contains(currentState)) {

							oString.append(currentState + "#" + "$" + "|");
							doEpsilon(oString, inCount, s, setupLines[4]);
							if(setupLines[4].contains(currentState)) {
								oString.append("1");
							} else {
								oString.append("0");
							}
							break;
						} else {
							oString.append((currentState + "#" + "$" + "|"));
						}
					}
					inCount++;
				}
				System.out.println(oString.toString());
			}

		} catch (IOException e) {
		}
	}

	private static void doEpsilon(StringBuilder oS, int counter, String[] s, String goodStates) {
		while (map.containsKey(currentState) && map.get(currentState).containsKey("$") && stack.size() > 0
				&& map.get(currentState).get("$").containsKey(stack.get(0))) {
			if(goodStates.contains(currentState) && counter == s.length - 1) { 
				return;
			}
			String topStack = stack.get(0);
			stack = stack.subList(1, stack.size());
			String[] tmpString = map.get(currentState).get("$").get(topStack).split(",");
			if (tmpString.length > 1 && !tmpString[1].equals("$")) {
				for(char c : new StringBuilder(tmpString[1]).reverse().toString().toCharArray()) {
					stack.add(0, Character.toString(c));
				}
			}
			currentState = tmpString[0];
			if(!stack.isEmpty()) {
				
			oS.append((currentState + "#" + listToString(stack) + "|"));
			} else {
				oS.append((currentState + "#" + "$" + "|"));
			}
			
		}
	}
	
	private static String listToString(List<String> list) {
		StringBuilder bob = new StringBuilder();
		for(String s : list) {
			bob.append(s);
		}
		
		return bob.toString();
	}
}
