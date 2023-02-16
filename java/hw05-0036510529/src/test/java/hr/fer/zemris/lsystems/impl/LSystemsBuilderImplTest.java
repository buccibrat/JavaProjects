package hr.fer.zemris.lsystems.impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilder;

public class LSystemsBuilderImplTest {
	@Test
	public void generateTest() {
		String[] data = new String[] { "origin 0.05 0.4", "angle 0", "unitLength 0.9",
				"unitLengthDegreeScaler 1.0 / 3.0", "", "command F draw 1", "command + rotate 60",
				"command - rotate -60", "", "axiom F", "", "production F F+F--F+F" };
		LSystemBuilder builder = new LSystemBuilderImpl();
		builder = builder.configureFromText(data);
		LSystem system = builder.build();
		assertEquals("F", system.generate(0));
		assertEquals("F+F--F+F", system.generate(1));
		assertEquals("F+F--F+F+F+F--F+F--F+F--F+F+F+F--F+F", system.generate(2));
	}

	@Test
	public void hilberCurveTest() {
		String[] data = new String[] { "origin                 0.05 0.05", "angle                  0",
				"unitLength             0.9", "unitLengthDegreeScaler 1.0 / 2.0",

				"command F draw 1", "command + rotate 90", "command - rotate -90",

				"axiom L",

				"production L +RF-LFL-FR+", "production R -LF+RFR+FL-" };
		LSystemBuilder builder = new LSystemBuilderImpl();
		builder = builder.configureFromText(data);
		LSystem system = builder.build();
		assertEquals("L", system.generate(0));
		assertEquals("+RF-LFL-FR+", system.generate(1));
		assertEquals("+-LF+RFR+FL-F-+RF-LFL-FR+F+RF-LFL-FR+-F-LF+RFR+FL-+", system.generate(2));
	}

	@Test
	public void plant2Test() {
		String[] data = new String[] { "origin                 0.5 0.0", "angle                  90",
				"unitLength             0.3", "unitLengthDegreeScaler 1.0 /2.05",

				"command F draw 1", "command + rotate 25", "command - rotate -25", "command [ push", "command ] pop",
				"command G color 00FF00",

				"axiom GF",

				"production F FF+[+F-F-F]-[-F+F+F]" };
		LSystemBuilder builder = new LSystemBuilderImpl();
		builder = builder.configureFromText(data);
		LSystem system = builder.build();
		assertEquals("GF", system.generate(0));
		assertEquals("GFF+[+F-F-F]-[-F+F+F]", system.generate(1));
		assertEquals(
				"GFF+[+F-F-F]-[-F+F+F]FF+[+F-F-F]-[-F+F+F]+[+FF+[+F-F-F]-[-F+F+F]-FF+[+F-F-F]-[-F+F+F]-FF+[+F-F-F]-[-F+F+F]]-[-FF+[+F-F-F]-[-F+F+F]+FF+[+F-F-F]-[-F+F+F]+FF+[+F-F-F]-[-F+F+F]]",
				system.generate(2));
	}
}
