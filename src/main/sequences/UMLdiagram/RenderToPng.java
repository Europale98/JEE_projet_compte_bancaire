package UMLdiagram;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;
import net.sourceforge.plantuml.SourceStringReader;

public class RenderToPng {

    public static void stringRender(String umlSequence, String path) {
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
    
    public static void fileRender(String umlFile) {
        try {
            /*
            String source = "@startuml\n";
            source += umlSequence + "\n";
            source += "@enduml\n";*/
            final SourceFileReader reader = new SourceFileReader(new File(umlFile));
            //final SourceStringReader reader = new SourceStringReader(source);
            List<GeneratedImage> list = reader.getGeneratedImages();
            for (GeneratedImage i : list) {
                System.out.println(i.getPngFile().getPath());
            }
            //reader.generateImage(png);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
