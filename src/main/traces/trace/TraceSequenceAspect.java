package trace;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import entity.StringXml;

@Component
@Aspect
public class TraceSequenceAspect {
    private static List<TraceSequence> tracesSequenceList = new ArrayList<>();
    
    @AfterReturning(pointcut = "call(*.*.new(..)) && !call(trace.*.new(..)) && !call(drawUml.*.new(..))", returning = "objReturn")
    public void newTrace(final JoinPoint thisJoinPoint, Object objReturn) {
        System.out.println("new");
        System.out.println((thisJoinPoint==null?"":thisJoinPoint.getSignature()));
        if(thisJoinPoint != null && thisJoinPoint.getThis() != null) {
            String stringThis = getString(thisJoinPoint.getThis());
            Signature sign = thisJoinPoint.getSignature();
            String returnValue = objReturn.toString();
            //String nameThis = thisJoinPoint.getThis().toString();
            /*for (Service a : AopProxyUtils.ultimateTargetClass(thisJoinPoint.getThis()).getAnnotationsByType(Service.class)) {
                nameThis += a.value();
            }*/
            //String nameTarget = thisJoinPoint.getTarget().toString();
            /*for (Service a : AopProxyUtils.ultimateTargetClass(thisJoinPoint.getTarget()).getAnnotationsByType(Service.class)) {
                nameTarget += a.value();
            }*/
            
            //String message = nameTarget + " -> " + nameThis + " : return" + "(" + Arrays.deepToString(returnValue) + ")";
            //System.out.println(message);
            
            tracesSequenceList.add(new TraceSequence(TraceType.NEW, stringThis, null, sign, thisJoinPoint.getSourceLocation(), returnValue));
        }
    }
    
    @Before("call(* *.*.*(..)) && !call(* *.*.toString(..))&& !call(* *.*.getString*(..)) && !call(* trace.*.*(..)) && !call(* drawUml.*.*(..))")
    //@Before("within(*.*) && call(* *.*.*(..)) && !call(* *.*.toString(..))")
    public void before(final JoinPoint thisJoinPoint) {
        System.out.println("before");
        System.out.println((thisJoinPoint==null?"":thisJoinPoint.getSignature()));
        if(thisJoinPoint != null && thisJoinPoint.getTarget() != null && thisJoinPoint.getThis() != null) {
            Signature sign = thisJoinPoint.getSignature();
            String arguments = Arrays.deepToString(thisJoinPoint.getArgs());
            //String nameThis = thisJoinPoint.getThis().toString();
            /*for (Service a : AopProxyUtils.ultimateTargetClass(thisJoinPoint.getThis()).getAnnotationsByType(Service.class)) {
                nameThis += a.value();
            }*/
            //String nameTarget = thisJoinPoint.getTarget().toString();
            /*for (Service a : AopProxyUtils.ultimateTargetClass(thisJoinPoint.getTarget()).getAnnotationsByType(Service.class)) {
                nameTarget += a.value();
            }*/
            //String message = nameThis + " -> " + nameTarget + " : " + sign.getName() + "(" + Arrays.deepToString(arguments) + ")";
            //System.out.println(message);
            
            String stringThis = getString(thisJoinPoint.getThis());
            String stringTarget = getString(thisJoinPoint.getTarget());
            
            tracesSequenceList.add(new TraceSequence(TraceType.ENTRY, stringThis, stringTarget, sign, thisJoinPoint.getSourceLocation(), arguments));
        }
    }
    
    @AfterReturning(pointcut = "call(* *.*.*(..)) && !call(* *.*.toString(..)) && !call(* *.*.getString*(..)) && !call(* trace.*.*(..)) && !call(* drawUml.*.*(..))", returning = "objReturn")
    //@AfterReturning(pointcut = "within(*.*) && call(* *.*.*(..)) && !call(* *.*.toString(..))", returning = "objReturn")
    public void after(final JoinPoint thisJoinPoint, Object objReturn) {
        System.out.println("after");
        System.out.println((thisJoinPoint==null?"":thisJoinPoint.getSignature()));
        if(thisJoinPoint != null && thisJoinPoint.getTarget() != null && thisJoinPoint.getThis() != null) {
            String stringThis = getString(thisJoinPoint.getThis());
            String stringTarget = getString(thisJoinPoint.getTarget());
            Signature sign = thisJoinPoint.getSignature();
            String returnValue = (objReturn==null)?null:objReturn.toString();
            //String nameThis = thisJoinPoint.getThis().toString();
            /*for (Service a : AopProxyUtils.ultimateTargetClass(thisJoinPoint.getThis()).getAnnotationsByType(Service.class)) {
                nameThis += a.value();
            }*/
            //String nameTarget = thisJoinPoint.getTarget().toString();
            /*for (Service a : AopProxyUtils.ultimateTargetClass(thisJoinPoint.getTarget()).getAnnotationsByType(Service.class)) {
                nameTarget += a.value();
            }*/
            
            //String message = nameTarget + " -> " + nameThis + " : return" + "(" + Arrays.deepToString(returnValue) + ")";
            //System.out.println(message);
            
            tracesSequenceList.add(new TraceSequence(TraceType.EXIT, stringThis, stringTarget, sign, thisJoinPoint.getSourceLocation(), returnValue));
        }
    }

    //@AfterThrowing(pointcut = "within(*.*) && call(* *.*.*(..)) && !call(* *.*.toString(..))", throwing = "exception")
    @AfterThrowing(pointcut = "call(* *.*.*(..)) && !call(* *.*.toString(..)) && !call(* *.*.getString*(..))&& !call(* trace.*.*(..)) && !call(* drawUml.*.*(..))", throwing = "exception")
    public void afterThrowing(final JoinPoint thisJoinPoint, Throwable exception) {
        System.out.println("throw");
        System.out.println((thisJoinPoint==null?"":thisJoinPoint.getSignature()));
        if(thisJoinPoint != null && thisJoinPoint.getTarget() != null && thisJoinPoint.getThis() != null) {
            String stringThis = getString(thisJoinPoint.getThis()); 
            String stringTarget = getString(thisJoinPoint.getTarget());
            Signature sign = thisJoinPoint.getSignature();
            String objetException = exception.getClass().getName();
            //String nameThis = thisJoinPoint.getThis().toString();
            /*for (Service a : AopProxyUtils.ultimateTargetClass(thisJoinPoint.getThis()).getAnnotationsByType(Service.class)) {
                nameThis += a.value();
            }*/
            //String nameTarget = thisJoinPoint.getTarget().toString();
            /*for (Service a : AopProxyUtils.ultimateTargetClass(thisJoinPoint.getTarget()).getAnnotationsByType(Service.class)) {
                nameTarget += a.value();
            }*/
            
            //String message = nameTarget + " -> " + nameThis + " : throws" + "(" + Arrays.deepToString(objetException) + ")";
            //System.out.println(message);
            
            tracesSequenceList.add(new TraceSequence(TraceType.EXCEPTION, stringThis, stringTarget, sign, thisJoinPoint.getSourceLocation(), objetException));
        }
    }
    
    private String getString(Object o) {
        Package p = o.getClass().getPackage();
        if (p!=null && p.getName().equals("entity")) {
            return ((StringXml) o).getString();
        }
        return AopProxyUtils.ultimateTargetClass(o).getName();
    }

    public static List<TraceSequence> getTracesSequenceList() {
        return tracesSequenceList;
    }
}
