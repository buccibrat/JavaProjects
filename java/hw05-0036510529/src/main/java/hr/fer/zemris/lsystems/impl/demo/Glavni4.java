package hr.fer.zemris.lsystems.impl.demo;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilderProvider;
import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;

public class Glavni4 {

	public static void main(String[] args) {
		LSystemViewer.showLSystem(createKochCurve2(LSystemBuilderImpl::new));

	}

	private static LSystem createKochCurve2(LSystemBuilderProvider provider) {
		String[] data = new String[] { 
				"origin                 0.5 0.0",
				"angle                  90",
				"unitLength             0.1",
				"unitLengthDegreeScaler 1.0 /2.05",

				"command F draw 1",
				"command + rotate 25.7",
				"command - rotate -25.7",
				"command [ push",
				"command ] pop",
				"command G color 00FF00",

				"axiom GF",

				"production F F[+F]F[-F]F" };
		return provider.createLSystemBuilder().configureFromText(data).build();
	}

}
