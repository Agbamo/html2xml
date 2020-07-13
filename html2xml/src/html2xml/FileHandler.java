package html2xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {
	static String read(String path) throws Exception {
		String contents = "";

		// Se abre y lee el fichero 
		File inputFile;
		contents = new String(Files.readAllBytes(Paths.get(path)));

		return contents;
	}
	static void write(String xml, String htmlPath) {
		// Calculamos la ruta de salida.
		String outputPath = htmlPath.substring(0, htmlPath.length()-5) + ".xml";
		// Escribimos el archivo
		try {
			File file = new File(outputPath);
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.");
			}
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(xml);
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		System.out.println(xml);
	}
}