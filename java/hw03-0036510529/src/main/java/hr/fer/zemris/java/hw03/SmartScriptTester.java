package hr.fer.zemris.java.hw03;

import java.nio.file.Files;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import hr.fer.zemris.java.custom.scripting.elems.*;
import hr.fer.zemris.java.custom.scripting.nodes.*;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

public class SmartScriptTester {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Number of arguments is invalid");
			System.exit(-1);
		}
		String filepath = args[0];
		try {
			String docBody = new String(Files.readAllBytes(Paths.get(filepath)), StandardCharsets.UTF_8);

			SmartScriptParser parser = null;
			try {
				parser = new SmartScriptParser(docBody);
			} catch (SmartScriptParserException e) {
				e.printStackTrace();
				System.out.println("Unable to parse document!");
				System.exit(-1);
			} catch (Exception e) {
				System.out.println("If this line ever executes, you have failed this class!");
				System.exit(-1);
			}

			DocumentNode document = parser.getDocumentNode();
			String originalDocumentBody = createOriginalDocumentBody(document);
			System.out.println(originalDocumentBody);
		} catch (IOException e) {
			System.out.println("Input is invalid");
			System.exit(-1);
		}
	}

	private static String createOriginalDocumentBody(Node document) {
		StringBuilder absoluteString = new StringBuilder();
		if (document instanceof TextNode) {
			ElementString element = ((TextNode) document).getText();
			String text = element.asText();
			StringBuilder sb = new StringBuilder();
			sb.append(text);
			for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == '\\' || text.charAt(i) == '"') {
					sb.insert(i, '\\');
				}
			}
			return sb.toString();
		} else if (document instanceof EchoNode) {
			Element[] elements = ((EchoNode) document).getElements();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("{$=");
			for (int i = 0; i < elements.length; i++) {
				if (elements[i] instanceof ElementString) {
					stringBuilder.append(tagString(elements[i].asText()));
				} else {
					stringBuilder.append(elements[i].asText());
				}
				stringBuilder.append(" ");
			}
			stringBuilder.append("$}");
			return stringBuilder.toString();
		} else if (document instanceof ForLoopNode) {
			absoluteString.append("{$ FOR ");
			absoluteString.append(((ForLoopNode) document).getVariable().asText() + " "
					+ ((ForLoopNode) document).getStartExpression().asText() + " "
					+ ((ForLoopNode) document).getEndExpression().asText() + " "
					+ ((ForLoopNode) document).getStepExpression().asText());
			absoluteString.append(" $}");
			for (int i = 0; i < document.numberOfChildern(); i++) {
				absoluteString.append(createOriginalDocumentBody(document.getChild(i)));
			}
			absoluteString.append("{$END$}");
			return absoluteString.toString();
		} else {
			for (int i = 0; i < document.numberOfChildern(); i++) {
				absoluteString.append(createOriginalDocumentBody(document.getChild(i)));
			}
			return absoluteString.toString();
		}

	}

	private static String tagString(String text) {

		StringBuilder sb = new StringBuilder();
		sb.append(text);
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '\\' || text.charAt(i) == '{') {
				sb.insert(i, '\\');
			}
		}
		return sb.toString();
	}

	private String loader(String filename) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename)) {
			byte[] buffer = new byte[1024];
			while (true) {
				int read = is.read(buffer);
				if (read < 1)
					break;
				bos.write(buffer, 0, read);
			}
			return new String(bos.toByteArray(), StandardCharsets.UTF_8);
		} catch (IOException ex) {
			return null;
		}
	}

}
