package lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimTS {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static String currentState;
	private static List<String> stack = new LinkedList<>();
	private static Map<String, Map<String, String[]>> map = new HashMap<>();
	private static List<String> outList = new LinkedList<String>();

	public static void main(String[] args) {

		String setupLines[] = new String[8];
		try {
			for (int i = 0; i < 8; i++) {
				setupLines[i] = reader.readLine();
			}
			String[] states = setupLines[0].split(",");

			String input = reader.readLine();
			
			// citam unos
			do {
				if (input.length() == 0) {
					break;
				}
				try {
					String[] temp = input.split("->");
					String[] stateInput = temp[0].split(",");
					String[] nextState = temp[1].split(",");
					if (!map.containsKey(stateInput[0])) {
						map.put(stateInput[0], new HashMap<>());
					}
					if (!map.get(stateInput[0]).containsKey(stateInput[1])) {
						map.get(stateInput[0]).put(stateInput[1], nextState);
					}
					

				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Podatci su pogresno uneseni!");
					System.exit(-1);
				}
				input = reader.readLine();
			} while (input != null && !input.equals(""));

			// Krecem analiztirat input
			
			
			String state = setupLines[6];
			StringBuilder traka = new StringBuilder(setupLines[4]);
			int index = Integer.parseInt(setupLines[7]);
			while(true) {
				String inChar = Character.toString(traka.charAt(index));
				
				if(!map.containsKey(state) || !map.get(state).containsKey(inChar)) {
					break;
				}
				String[] array = map.get(state).get(inChar);
				state = array[0];
				traka.setCharAt(index, array[1].charAt(0));
				if(array[2].equals("R")) {
					index++;
				} else {
					index--;
				}
			}
			int prihvatljiv = 0;
			if(setupLines[5].contains(state)) {
				prihvatljiv = 1;
			}
			System.out.println(state + "|" + index + "|" + traka.toString() + "|" + prihvatljiv);
		} catch (IOException e) {
		}
	}

}
