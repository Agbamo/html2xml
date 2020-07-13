package html2xml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Munger {

	public static void main(String[] args) {

		// Declaramos las variables.
		String inputPath = "";
		boolean dropFormattingTags = false;
		boolean includeAttributes = false;

		// Leemos los argumentos.
		inputPath = args[0];
		if(args[1].equals("true")) {
			dropFormattingTags = true;
		}
		if(args[2].equals("true")) {
			includeAttributes = true;
		}

		// Leemos el fichero de entrada.
		String contents = "";
		try {
			contents = FileHandler.read(inputPath);
		}catch(Exception e) {
			System.out.println("Cannot read input file");
			System.exit(0);
		}

		// Utilizando Jsoup, parseamos el html a una estructura en árbol correctamente formada.
		Document doc = Jsoup.parse(contents);

		// Construimos el xml recorriendo el árbol de forma recursiva.
		XmlTemplate template = new XmlTemplate(doc, dropFormattingTags, includeAttributes);
		String xml = template.build();

		// Escribimos el fichero de salida.
		FileHandler.write(xml, inputPath);
	}
}