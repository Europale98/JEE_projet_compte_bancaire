package UMLdiagram;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.sourceforge.plantuml.SourceStringReader;

public class RenderToPng {

    public static void render(String umlSequence, String path) {
        try {
            final OutputStream png = new FileOutputStream(path + ".png");
            
            String source = "@startuml\n";
            source += umlSequence + "\n";
            source += "@enduml\n";
            
            final SourceStringReader reader = new SourceStringReader(source);
            reader.generateImage(png);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
