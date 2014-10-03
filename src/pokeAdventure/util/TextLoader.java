package pokeAdventure.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class TextLoader {

	public static String[] loadFileToStrings(String fileName, String comment) {
		boolean activateComments = comment.length() != 0;

		ArrayList<String> content = new ArrayList<String>();
		BufferedReader reader = null;

		try {

			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			String s = null;

			while ((s = reader.readLine()) != null) {
				if (!(activateComments && s.startsWith(comment)))
					content.add(s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content.toArray(new String[] {});
	}

	public static String loadFileToString(String fileName) {
		String[] strings = loadFileToStrings(fileName, "");
		StringBuilder stringBuilder = new StringBuilder();
		for (String string : strings) {
			stringBuilder.append(string).append(System.getProperty("line.separator"));
		}
		return stringBuilder.toString();
	}
}
