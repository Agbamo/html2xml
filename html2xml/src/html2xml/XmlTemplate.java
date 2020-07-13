package html2xml;

import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class XmlTemplate {

	Document doc;
	boolean dropFormattingTags;
	boolean includeAttributes;

	public XmlTemplate(Document doc, boolean dropFormattingTags, boolean includeAttributes) {
		super();
		this.doc = doc;
		this.dropFormattingTags = dropFormattingTags;
		this.includeAttributes = includeAttributes;
	}

	public String build() {
		// Añadimos el encabezado y llamamos al método navigateDoc(..)
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + separators(2) + navigateDoc(doc);
		return xml;
	}

	// navigate(..) recorre de forma recursiva los nodos del árbol de documento, 
	// escribiendo el archivo xml correspondiente.
	private String navigate(Node node, int depth) {
		String output = "";
		// Si el nodo es de tipo Element, lo casteamos y actuamos en consecuencia.
		if(node instanceof Element) {
			Element elementNode = (Element)node;
			// Si el usuario ha seleccionado eliminar las etiquetas de formato 
			// y es el caso, omitimos el tratamiento del nodo.
			if( !(dropFormattingTags && FormattingTags.getFormattingTags().contains(elementNode.tagName())) ) {
				// Comenzamos a escribir la etiqueta de apertura.
				output += tabs(depth) + "<"+elementNode.tagName();
				// Si el usuario ha seleccionado incluir atributos y los hay, los incluimos.
				if( (includeAttributes && (elementNode.attributes() != null) && (elementNode.attributes().size() != 0)) ) {
					Attributes attributes = elementNode.attributes();	
					output += " " + attributes.toString();
				}
				// Terminamos de escribir la etiqueta de apertura.
				output += ">" + separators(1);

				// Para cada uno de los nodos interiores, llamamos recursivamente al método navigate(..)
				List<Node> children = node.childNodes();
				Iterator<Node> iterator = children.iterator();
				while(iterator.hasNext()) {
					Node child = iterator.next();
					output += navigate(child, depth+1);
				}

				// Incluímos la etiqueta de cierre
				output += tabs(depth) + "</"+elementNode.tagName()+">" + separators(1);
			}	
			// Si el nodo es de tipo TextNode, lo casteamos y actuamos en consecuencia.
		}else if(node instanceof TextNode) {
			TextNode textNode = (TextNode)node;
			// Si el nodo está vacío, lo ignoramos.
			if(!textNode.text().isEmpty() && !textNode.text().equals(" ")) {
				output += tabs(depth) + "<text>" + textNode.text() + "</text>" + separators(1);
			}
		}
		return output;
	}

	// navigateDoc(..) incluye el tag <root> y llama al método navigate(..)
	// para que recorra los nodos inferiores.
	private String navigateDoc(Document doc) {
		String output = "";
		output += "<root>" + separators(1);
		List<Node> children = doc.childNodes();
		Iterator<Node> iterator = children.iterator();
		while(iterator.hasNext()) {
			Node child = iterator.next();
			output += navigate(child, 1) + separators(1);
		}
		output += "</root>";
		return output;
	}

	// separators(..) abstrae del sistema operativo la inclusión de separadores de línea.
	private String separators(int number) {
		String outPut = "";
		for (int i = 0; i<number; i++) {
			outPut += System.getProperty("line.separator");
		}
		return outPut;
	}

	// tabs(..) inserta el número de tabulaciones que recibe por parámetro.
	private String tabs(int number) {
		String outPut = "";
		for (int i = 0; i<number; i++) {
			outPut += "\t";
		}
		return outPut;
	}
}