package trace;

import java.util.Arrays;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;

public class TraceSequence {
    private TraceType traceType;
    private String source;
    private String target;
    private Signature signature;
    private SourceLocation sourceLocation;
    private String args;

    public TraceSequence(TraceType traceType, String source, String target,
            Signature signature, SourceLocation sourceLocation, String args) {
        super();
        this.traceType = traceType;
        this.source = source;
        this.target = target;
        this.signature = signature;
        this.sourceLocation = sourceLocation;
        this.args = args;
    }

    public String asString() {
        switch (this.traceType) {
        case ENTRY:
            return source + " -> " + target + " : " + signature.getName() + "("
                    + args + ")";
        case EXIT:
            return target + " -> " + source + " : return" + "(" + args + ")";
        case EXCEPTION:
            return target + " -> " + source + " : throws" + "(" + args + ")";
        case NEW:
            return source + " -> " + args + " ** : new ";
        default:
            return "";
        }
    }
}
