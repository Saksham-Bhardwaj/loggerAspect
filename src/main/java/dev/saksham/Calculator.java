package dev.saksham;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ClassLogger
public class Calculator {
    //@MethodLogger
    public int sum(int num1, int num2){
        return num1+num2;
    }

    //@MethodLogger
    public int mulitply(int num1, int num2){
        return num1*num2;
    }

    //@MethodLogger
    public int divide(int num1, int num2){
        return num1/num2;
    }

    //@MethodLogger
    public int subtract(int num1, int num2){
        return num1-num2;
    }

    //@MethodLogger
    public long getCurrTime(){
        return System.currentTimeMillis();
    }

    //@MethodLogger
    public List<String> tryList(List<String> s){
        return s;
    }

}
