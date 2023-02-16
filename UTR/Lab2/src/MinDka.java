
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinDka {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static HashMap<String, State> stateMap = new HashMap<String, State>(); // Hahsmap with states
	private static List<State> stateList = new ArrayList<>();
	private static List<String> inputs = new LinkedList<>();

	public static void main(String[] args) {
		String firstFourLines[] = new String[4];
		try {
			for (int i = 0; i < 4; i++) {
				firstFourLines[i] = reader.readLine();
			}

			List<String> goodStates = Arrays.asList(firstFourLines[2].split(","));
			inputs = Arrays.asList(firstFourLines[1].split(","));
			String[] states = firstFourLines[0].split(",");
			for (int i = 0; i < states.length; i++) {
				State state = new State(states[i], firstFourLines[1].split(","), i);
				stateMap.put(states[i], state);
				stateList.add(state);
				if (goodStates.contains(state.getName())) {
					state.setIsGood(true);
				}
			}
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
					stateMap.get(stateInput[0]).addNextState(stateInput[1], stateMap.get(temp[1]));
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Podatci su pogresno uneseni!");
					System.exit(-1);
				}
				input = reader.readLine();
			} while (input != null && !input.equals(""));

			// Mièem nedohvatljiva stanja

			List<State> listDohvatljivi = new ArrayList<>();
			listDohvatljivi.add(stateList.get(0));
			List<State> listCopy = null;
			int index = 0;

			do {
				listCopy = new ArrayList<>(listDohvatljivi);

				for (int i = 0; i < listDohvatljivi.size(); i++) {
					State s = listDohvatljivi.get(i);
					for (String in : inputs) {
						if (!listDohvatljivi.contains(s.getStates(in))) {
							listDohvatljivi.add(s.getStates(in));
						}
					}
				}

			} while (!listCopy.equals(listDohvatljivi));

			listDohvatljivi.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
			stateList = listDohvatljivi;
			boolean[][] minArray = new boolean[stateList.size() - 1][stateList.size() - 1];

			int jCounter = 0; // Koristi se za namještanje j tokom iteracije po tablici
			// Prvi prolaz koji usporeðuje dobra i loša stanja(Prihvatljiva i
			// neprihvatljiva).
			for (int i = 0; i < stateList.size() - 1; i++) {
				State state = stateList.get(i);
				for (int j = 0; j < stateList.size() - 1; j++) {
					State state2 = stateList.get(j + 1);
					if (state.getIsGood() != state2.getIsGood()) {
						minArray[i][j] = true;
					}
				}
			}

			jCounter = 1;
			HashMap<Wrapper, List<Wrapper>> wrappedMap = new HashMap<>();
			// Stvori se lista èekanja
			for (int i = 0; i < stateList.size() - 1; i++) {
				int j = jCounter;
				jCounter++;
				State state = stateList.get(i);
				for (; j < stateList.size() - 1; j++) {
					State state2 = stateList.get(j);
					for (String s : inputs) {
						if (state2.getStates(s).index < stateList.size() - 1
								&& state.getStates(s).index < stateList.size() - 1
								&& !minArray[state.getStates(s).index][state2.getStates(s).index]) {
							Wrapper newWrapper = new Wrapper(state, state2);
							Wrapper newWrapperKey = new Wrapper(state.getStates(s), state2.getStates(s));
							if (!wrappedMap.containsKey(newWrapperKey)) {
								wrappedMap.put(newWrapperKey, new LinkedList<Wrapper>());
								wrappedMap.get(newWrapperKey).add(newWrapper);
							} else {
								wrappedMap.get(newWrapperKey).add(newWrapper);
							}
						}
					}
				}
			}

			Set<Entry<Wrapper, List<Wrapper>>> set = null;
			Set<Entry<Wrapper, List<Wrapper>>> set2 = null;
//			List<List<Wrapper>> wrapperList = new LinkedList<>();
//			Iterator<Entry<Wrapper, List<Wrapper>>> it = wrappedMap.entrySet().iterator();
//			while (it.hasNext()) {
//				Map.Entry<Wrapper, List<Wrapper>> next = it.next();
//				wrapperList.add(next.getValue());
//			}
			// Riješi se lista èekanja
			boolean[][] minTest = new boolean[stateList.size() - 1][stateList.size() - 1];
			do {
				arrayCopy(minArray, minTest);
				set = wrappedMap.entrySet();
				for (Entry<Wrapper, List<Wrapper>> e : set) {
					Wrapper tempKey = e.getKey();
					if (tempKey.getS1().index < (stateList.size() - 2) && tempKey.getS2().index < (stateList.size() - 2)
							&& minArray[tempKey.getS1().index][tempKey.getS2().index + 1] == true) {
						for (Wrapper w : e.getValue()) {
							minArray[w.getS1().index][w.getS2().index + 1] = true;
						}
					}
				}

			} while (!Arrays.deepEquals(minArray, minTest));

			// Zamjena stanja

			Map<State, List<State>> minStates = new HashMap<State, List<State>>();
			jCounter = 0;
			for (int i = 0; i < stateList.size() - 1; i++) {
				State state = stateList.get(i);
				for (int j = 0; j < stateList.size() - 1; j++) {
					State state2 = stateList.get(j);
					if (minArray[i][j] == false) {
						if (minStates.containsKey(state)) {
							minStates.get(state).add(state2);
						} else if (minStates.containsKey(state2)) {
							minStates.get(state2).add(state);
						} else {
							boolean flag = false;
							for (Entry<State, List<State>> e : minStates.entrySet()) {
								if (e.getValue().contains(state) || e.getValue().contains(state2)) {
									e.getValue().add(state);
									e.getValue().add(state2);
									flag = true;
									break;
								}
							}

							if (flag == true) {
								continue;
							}

							if (!minStates.containsKey(stateList.get(i))) {
								List<State> tempList = new LinkedList<State>();
								tempList.add(state2);
								minStates.put(state, tempList);
							} else if (!minStates.containsKey(stateList.get(j))) {
								List<State> tempList = new LinkedList<State>();
								tempList.add(state);
								minStates.put(state2, tempList);
							}
						}
					}
				}
			}

			// Ispis novih stanja
			System.out.println("Do negdje sam došo, možda ne padnem lab!");

		} catch (

		IOException e) {
			System.out.println("A ne...");
		}
	}

	private static void arrayCopy(boolean[][] old, boolean[][] current) {
		for (int i = 0; i < old.length; i++) {
			for (int j = 0; j < old[i].length; j++) {
				current[i][j] = old[i][j];
			}
		}
	}

	private static boolean stateCompare(State s1, State s2) {
		boolean returnValue = true;
		for (String s : inputs) {
			if (!s1.getStates(s).equals(s2.getStates(s))) {
				returnValue = false;
			}
		}
		return returnValue;
	}

	// wrapps two states
	private static class Wrapper {
		State s1;
		State s2;

		public Wrapper(State s1, State s2) {
			this.s1 = s1;
			this.s2 = s2;
		}

		public State getS1() {
			return s1;
		}

		public State getS2() {
			return s2;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Wrapper))
				return false;
			Wrapper other = (Wrapper) obj;
			if (this.s1.equals(other.s1) && this.s2.equals(other.s2)
					|| this.s1.equals(other.s2) && this.s2.equals(other.s1))
				return true;
			return this == other;
		}
	}

	private static class State {
		private String name;
		private Hashtable<String, State> nextState;
		private boolean isOn;
		@SuppressWarnings("unused")
		private boolean epsilon;
		private boolean isGood;
		private int index;

		public State(String name, String[] input, int index) {
			if (name == null) {
				throw new NullPointerException();
			}
			;
			this.name = name;
			nextState = new Hashtable<String, State>(input.length);
			epsilon = false;
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public void setIsGood(boolean isGood) {
			this.isGood = isGood;
		}

		public boolean getIsGood() {
			return isGood;
		}

		public void addNextState(String input, State states) {
			nextState.put(input, states);
		}

		public State getStates(String input) {
			return nextState.get(input);
		}

		@SuppressWarnings("unused")
		public void epsilonWasChecked() {
			epsilon = true;
		}

		@SuppressWarnings("unused")
		public void resetEpsilon() {
			epsilon = false;
		}

		@Override
		public int hashCode() {
			return Objects.hash(name);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof State))
				return false;
			State other = (State) obj;
			return Objects.equals(name, other.name);
		}
	}
}
