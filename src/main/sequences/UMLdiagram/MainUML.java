package UMLdiagram;

public class MainUML {

    public static void main(String[] args) {
        RenderToPng.fileRender("diagrammes/sequences/credit.txt");
        RenderToPng.fileRender("diagrammes/sequences/debitImpossible.txt");
        RenderToPng.fileRender("diagrammes/sequences/creerClient.txt");
        RenderToPng.fileRender("diagrammes/sequences/creerCompte.txt");
        RenderToPng.fileRender("diagrammes/sequences/fermerCompte.txt");
        RenderToPng.fileRender("diagrammes/sequences/suppressionClient.txt");
        RenderToPng.fileRender("diagrammes/sequences/virement.txt");
        RenderToPng.fileRender("diagrammes/sequences/virementInexistant.txt");
    }

}
