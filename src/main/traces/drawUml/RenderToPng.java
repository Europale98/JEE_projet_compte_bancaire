package drawUml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import net.sourceforge.plantuml.SourceStringReader;
import trace.TraceSequence;

public class RenderToPng {

    public void render(final List<TraceSequence> traceSequenceList) {
        try {
            final OutputStream png = new FileOutputStream("out.png");
            String source = "@startuml\n";
            for (final TraceSequence trace : traceSequenceList) {
                source += trace.asString() + "\n";
            }

            source += "@enduml\n";
            System.out.println("\n\n\n" + source);
            final SourceStringReader reader = new SourceStringReader(source);
            reader.generateImage(png);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /*try {
            final OutputStream png = new FileOutputStream("out.png");
            String source = "@startuml\n";
            source += "CLient1 -> Client2 : virement\n";
            source += "return resultVirement\n";

            source += "@enduml\n";
            System.out.println("\n\n\n" + source);
            final SourceStringReader reader = new SourceStringReader(source);
            reader.generateImage(png);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
