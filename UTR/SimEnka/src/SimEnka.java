
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

public class SimEnka {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static HashMap<String, State> stateList = new HashMap<String, State>(); // Hahsmap with states

	public static void main(String[] args) {
		String firstFiveLines[] = new String[5];
		try {
			for (int i = 0; i < 5; i++) {
				firstFiveLines[i] = reader.readLine();
			}
			String[] states = firstFiveLines[1].split(",");
			for (int i = 0; i < states.length; i++) {
				stateList.put(states[i], new State(states[i], firstFiveLines[2].split(",")));
			}
//			stateList.put("#", new State("#", firstFiveLines[2].split(",")));
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
					LinkedList<State> nextStatesList = new LinkedList<State>();
					for (int i = 0; i < nextStates.length; i++) {

						Iterator<Entry<String, State>> it = stateList.entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry<String, State> next = it.next();
							if (next.getValue().getName().equals(nextStates[i])) {
								nextStatesList.add(next.getValue());
								break;
							}
						}
					}

					stateList.get(stateInput[0]).addNextState(stateInput[1], nextStatesList);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Podatci su pogresno uneseni!");
					System.exit(-1);
				}
				input = reader.readLine();
			} while (input != null && !input.equals(""));
			// Krecem analiztirat input
			String[] listOfInputs = firstFiveLines[0].split("\\|");
			for (int i = 0; i < listOfInputs.length; i++) {
				turnOffAllStates();
				String[] inputs = listOfInputs[i].split(",");
				State first = stateList.get(firstFiveLines[4]);
//				System.out.printf("%s|", first.getName());
				first.turnOn();
				List<State> epsilonStates = new LinkedList<State>();
				epsilonStates.add(first);
				epsilonStates = epsilonRecurzion(epsilonStates);
				Set<String> setFirst = new TreeSet<>();
				for (State sFirst : epsilonStates) {
					setFirst.add(sFirst.getName());
				}
				int index = 0;
				for (String s : setFirst) {
					if (setFirst.size() > 1 && index < setFirst.size() - 1) {
						System.out.printf("%s,", s);
					} else {
						System.out.printf("%s", s);
					}
					index++;
				}
				System.out.printf("|");
				for (int j = 0; j < inputs.length; j++) {
					List<State> endStates = goThroughStates(inputs[j]);
					Set<String> set = new TreeSet<>();
					for (State endState : endStates) {
						set.add(endState.getName());

					}
					index = 0;
					for (String s : set) {
						if (set.size() > 1 && index < set.size() - 1) {
							System.out.printf("%s,", s);
						} else {
							System.out.printf("%s", s);
						}
						index++;
					}
					if (!isOn()) {
						System.out.printf("#");
					}
					if (j < inputs.length - 1) {
						System.out.printf("|");
					} else {
						System.out.println("");
					}
				}
			}
		} catch (IOException e) {
		}
	}

	private static List<State> goThroughStates(String input) {

		Iterator<Entry<String, State>> it = stateList.entrySet().iterator();
		List<State> wereTurnedOn = getOnStates();
		for (State s : wereTurnedOn) {
			List<State> turnOn = s.getStates(input);
			if (turnOn != null) {
				for (State s2 : turnOn) {
					s2.turnOn();
				}
			}
		}
		List<State> turnedOn = new LinkedList<>();
		while (it.hasNext()) {
			State state = it.next().getValue();
			if (state.isOn()) {
				turnedOn.add(state);
			}
		}

		List<State> turnedOnWithEpsilons = epsilonRecurzion(turnedOn);

		return turnedOnWithEpsilons;
	}

	// Vrati sva ukljucena stanja i iskljuci ih
	private static List<State> getOnStates() {
		Iterator<Entry<String, State>> it = stateList.entrySet().iterator();
		List<State> wereTurnedOn = new LinkedList<>();
		while (it.hasNext()) {
			State state = it.next().getValue();
			if (state.isOn()) {
				wereTurnedOn.add(state);
				state.turnOf();
			}
		}
		return wereTurnedOn;
	}

	private static List<State> epsilonRecurzion(List<State> turnedOn) {
		int size = turnedOn.size();
		List<State> turnedOnCopy = new LinkedList<State>();
		turnedOnCopy.addAll(turnedOn);
		for (State s : turnedOn) {
			List<State> epsilonStates = s.getStates("$");
			if (epsilonStates != null) {
				for (State s2 : epsilonStates) {
					turnedOnCopy.add(s2);
					s2.turnOn();
				}
			}
		}
		List<State> listWithoutDuplicates = turnedOnCopy.stream().distinct().collect(Collectors.toList());
		if (size == listWithoutDuplicates.size()) {
			return listWithoutDuplicates;
		}
		return epsilonRecurzion(listWithoutDuplicates);
	}

	private static boolean isOn() {
		Iterator<Entry<String, State>> it = stateList.entrySet().iterator();
		while (it.hasNext()) {
			if (it.next().getValue().isOn()) {
				return true;
			}
		}
		return false;
	}

	private static void turnOffAllStates() {
		Iterator<Entry<String, State>> it = stateList.entrySet().iterator();
		while (it.hasNext()) {
			it.next().getValue().turnOf();
		}
	}

	private static class State {
		private String name;

		private Hashtable<String, List<State>> nextState;
		private boolean isOn;
		@SuppressWarnings("unused")
		private boolean epsilon;

		public State(String name, String[] input) {
			if (name == null) {
				throw new NullPointerException();
			}
			;
			this.name = name;
			nextState = new Hashtable<String, List<State>>(input.length);
			epsilon = false;
		}

		public String getName() {
			return name;
		}

		public void addNextState(String input, List<State> states) {
			nextState.put(input, states);
		}

		public List<State> getStates(String input) {
			return nextState.get(input);
		}

		public void turnOn() {
			isOn = true;
		}

		public void turnOf() {
			isOn = false;
		}

		public boolean isOn() {
			return isOn;
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
