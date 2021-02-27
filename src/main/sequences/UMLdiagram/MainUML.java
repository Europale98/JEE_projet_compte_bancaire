package UMLdiagram;

public class MainUML {

    public static void main(String[] args) {
        String sequence = CompteUML.CompteUML.creditUML();
        RenderToPng.render(sequence, "diagrammes/sequences/credit");
    }

}
