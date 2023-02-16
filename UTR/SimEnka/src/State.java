import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

public class State {
	private String name;
	
	
	private Hashtable<String, List<State>> nextState;
	private boolean isOn;
	private boolean epsilon;
	
	public State(String name, String[] input) {
		if(name == null) {
			throw new NullPointerException();
		};
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
	
	public List<State> getStates(String input){
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
	
	public void epsilonWasChecked() {
		epsilon = true;
	}
	
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
