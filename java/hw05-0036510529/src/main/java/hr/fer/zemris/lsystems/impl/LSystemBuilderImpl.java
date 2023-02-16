package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

import hr.fer.zemris.java.custom.collections.Dictionary;
import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilder;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.commands.ColorCommand;
import hr.fer.zemris.lsystems.impl.commands.DrawCommand;
import hr.fer.zemris.lsystems.impl.commands.PopCommand;
import hr.fer.zemris.lsystems.impl.commands.PushCommand;
import hr.fer.zemris.lsystems.impl.commands.RotateCommand;
import hr.fer.zemris.lsystems.impl.commands.ScaleCommand;
import hr.fer.zemris.lsystems.impl.commands.SkipCommand;

public class LSystemBuilderImpl implements LSystemBuilder {
	private Dictionary<Character, String> productions;
	private Dictionary<Character, Command> commands;
	private double unitLength = 0.1;
	private double unitLengthDegreeScaler = 1;
	private Vector2D origin = new Vector2D(0, 0);
	private double angle = 0;
	private String axiom = "";

	public LSystemBuilderImpl() {
		productions = new Dictionary<>();
		commands = new Dictionary<>();
	}

	@Override
	public LSystem build() {
		return new LSystemImpl();
	}

	@Override
	public LSystemBuilder configureFromText(String[] arg0) {
		for (String s : arg0) {
			String[] array = s.split("\\s+");
			if (array.length == 3 && array[0].equals("origin")) { //parses origin
				setOrigin(Double.parseDouble(array[1]), Double.parseDouble(array[2]));
			} else if (array.length == 2 && array[0].equals("angle")) { //parses angle, multiplies angle by Math.PI / 180 because methods work in radians
				setAngle(Double.parseDouble(array[1]) * Math.PI / 180);
			} else if (array.length == 2 && array[0].equals("unitLength")) { //parses unitLength
				setUnitLength(Double.parseDouble(array[1]));
			} else if (array.length >= 2 && array[0].equals("unitLengthDegreeScaler")) { //parses unitLengthDegreeScaler
				if (array.length == 4) { //If all arguments are separated by white spaces 
					double firstNumber = Double.parseDouble(array[1]);
					double secondNumber = Double.parseDouble(array[3]);
					setUnitLengthDegreeScaler(firstNumber / secondNumber);
				} else if(array.length == 3){ //if input is written in fraction
					if(array[1].endsWith("/")) { 
						double firstNumber = Double.parseDouble(array[1].substring(0, array[1].length() - 1));
						double secondNumber = Double.parseDouble(array[2]);
						setUnitLengthDegreeScaler(firstNumber / secondNumber);
					} else {
						double firstNumber = Double.parseDouble(array[1]);
						double secondNumber = Double.parseDouble(array[2].substring(1, array[2].length()));
						setUnitLengthDegreeScaler(firstNumber / secondNumber);
					}
				} else {
					setUnitLengthDegreeScaler(Double.parseDouble(array[1]));
				}
			} else if (array.length >= 3 && array[0].equals("command")) {//parses command
				if (array.length == 3) {
					registerCommand(array[1].charAt(0), array[2]);
				} else {
					registerCommand(array[1].charAt(0), array[2] + " " + array[3]);
				}
			} else if (array.length >= 2 && array[0].equals("axiom")) {//parses axiom
				StringBuilder bob = new StringBuilder();
				for (int i = 1; i < array.length; i++) {
					bob.append(array[i]);
				}
				setAxiom(bob.toString());
			} else if (array.length >= 2 && array[0].equals("production")) {//parses production
				StringBuilder bob = new StringBuilder();
				for (int i = 2; i < array.length; i++) {
					bob.append(array[i]);
				}
				registerProduction(array[1].charAt(0), bob.toString());
			}
		}
		return this;
	}

	/**
	 * Adds command to commands dictionary
	 */
	@Override
	public LSystemBuilder registerCommand(char arg0, String arg1) {
		String[] array = arg1.split(" ");
		Command command = null;

		if (array[0].equals("push")) {
			command = new PushCommand();
		} else if (array[0].equals("pop")) {
			command = new PopCommand();
		} else if (array[0].equals("draw")) {
			command = new DrawCommand(Double.parseDouble(array[1]));
		} else if(array[0].equals("skip")) {
			command = new SkipCommand(Double.parseDouble(array[1]));
		} else if(array[0].equals("scale")) {
			command = new ScaleCommand(Double.parseDouble(array[1]));
		} else if(array[0].equals("rotate")) {
			command = new RotateCommand(Double.parseDouble(array[1]) * Math.PI / 180);
		} else {
			command = new ColorCommand(array[1]);
		}
		commands.put(arg0, command);
		return this;
	}

	@Override
	public LSystemBuilder registerProduction(char arg0, String arg1) {
		productions.put(arg0, arg1);
		return this;
	}

	@Override
	public LSystemBuilder setAngle(double arg0) {
		angle = arg0;
		return this;
	}

	@Override
	public LSystemBuilder setAxiom(String arg0) {
		axiom = arg0;
		return this;
	}

	@Override
	public LSystemBuilder setOrigin(double arg0, double arg1) {
		origin = new Vector2D(arg0, arg1);
		return this;
	}

	@Override
	public LSystemBuilder setUnitLength(double arg0) {
		unitLength = arg0;
		return this;
	}

	@Override
	public LSystemBuilder setUnitLengthDegreeScaler(double arg0) {
		unitLengthDegreeScaler = arg0;
		return this;
	}

	private class LSystemImpl implements LSystem {

		public LSystemImpl() {
		}

		@Override
		public void draw(int arg0, Painter arg1) {
			char[] product = generate(arg0).toCharArray();
			TurtleState turtle = new TurtleState(origin, new Vector2D(1, 0).rotated(angle), Color.black);
			
			Context ctx = new Context();
			ctx.pushState(turtle);
			turtle.setShit(unitLength * Math.pow(unitLengthDegreeScaler, arg0));
			for(char c : product) {
				Command command = commands.get(c);
				if(command != null) {
					command.execute(ctx, arg1);
				}
			}
		}

		@Override
		public String generate(int arg0) {
			if (arg0 == 0) {
				return axiom;
			}

			char[] axiom = LSystemBuilderImpl.this.axiom.toCharArray();
			StringBuilder bob = null;
			for (int i = 0; i < arg0; i++) {
				bob = new StringBuilder();
				for (int j = 0; j < axiom.length; j++) {
					String production = productions.get(axiom[j]);
					if (production != null) {
						bob.append(production);
						continue;
					}
					bob.append(axiom[j]);
				}
				axiom = bob.toString().toCharArray();

			}
			return bob.toString();
		}
	}
}