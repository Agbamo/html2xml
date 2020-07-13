package html2xml;

import java.util.ArrayList;
import java.util.Arrays;

public class FormattingTags {

	// Lista con las etiquetas a ignorar si el usuario selecciona "ignorar etiquetas de formato"
	private static ArrayList<String> formattingTags = new ArrayList<>(Arrays. asList("abbr",
			"address", "b", "bdi", "bdo", "big", "blockquote", "center", "cite", "code",
			"del", "dfn", "em", "font", "i", "ins", "kbd", "mark", "meter", "pre",
			"progress", "q", "rp", "rt", "ruby", "s", "samp", "small",  "strike",
			"strong", "sub", "sup", "template", "time", "tt", "u", "var", "wbr"));

	public static ArrayList<String> getFormattingTags() {
		return formattingTags;
	}	
}