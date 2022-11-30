package Calculator.Main.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public  String solveFunction(String type,String num1,String num2){
        if(num2.equals("a"))num2=num1;
        if(num1.charAt(num1.length()-1)=='.')num1=num1.substring(0,num1.length()-1);
        if(num2.charAt(num2.length()-1)=='.')num2=num2.substring(0,num2.length()-1);

        if(type.equals("percent")){
            return percent(num1);
        } else if (type.equals("reverse")) {
            return reverse(num1);
        }else if(type.equals("square")){
            return square(num1);
        } else if (type.equals("root")) {
            return root(num1);
        } else if (type.equals("divide")) {
            return divide(num1,num2);
        } else if (type.equals("multiple")) {
            return multiple(num1,num2);
        }else if(type.equals("subtract")){
            return subtract(num1,num2);
        }else if(type.equals("add")){
            return add(num1,num2);
        }else if(type.equals("convertSign"))return convertSign(num1);
        return "-1";
    }
    public String percent(String num){
        return divide(num,"100");
    }
    public String clear(){
        return "0";
    }
    public String delete(String num){
        if(num.length()==1){return "0";}
        if(num.length()==2&& num.charAt(0)=='-') return "0";
        return num.substring(0,num.length()-1);
    }
    public String reverse(String num){

        return divide("1.0",num);
    }
    public String  square(String num){
        return multiple(num,num);
    }
    public  String root(String num){

        if(num.charAt(0)=='-'){
            return "Invalid input";
        }
        double nu=Math.sqrt(Double.parseDouble(num));
        if(Math.abs(nu-Math.round(nu))<1e-10){
            return String.valueOf(Math.round(nu));
        }
        return String.valueOf(nu);
    }

    public String divide(String num1,String num2){
         if(num2.equals("0")){
            return "Cannot divide by zero";
        }
        double nu1=Double.parseDouble(num1),nu2=Double.parseDouble(num2);
        nu1=nu1/nu2;
        if(Math.abs(nu1-Math.round(nu1))<1e-10){
            return String.valueOf(Math.round(nu1));
        }
        return String.valueOf(nu1);
    }
    public String multiple(String num1,String num2){

        boolean decimal= num1.indexOf('.') != -1 || num2.indexOf('.')!=-1;

        if(decimal || Long.parseLong(num1) > 1e9|| Long.parseLong(num2)>1e9 ){
            double nu1=Double.parseDouble(num1),nu2=Double.parseDouble(num2);
            if(nu1*nu1== Double.POSITIVE_INFINITY)return "Over flow";
            return String.valueOf(nu1*nu2);
        }else{
            Long nu1=Long.parseLong(num1),nu2=Long.parseLong(num2);
            return String.valueOf(nu1*nu2);
        }
    }
    public  String subtract(String num1,String num2){
        boolean decimal= num1.indexOf('.') != -1 ||num2.indexOf('.')!=-1;

        if(decimal){
            double nu1=Double.parseDouble(num1),nu2=Double.parseDouble(num2);
            return String.valueOf(nu1-nu2);
        }else{
            Long nu1=Long.parseLong(num1),nu2=Long.parseLong(num2);
            return String.valueOf(nu1-nu2);
        }
    }
    public String add( String num1,String num2){

        boolean decimal= num1.indexOf('.') != -1 ||num2.indexOf('.')!=-1;

        if(decimal){
            double nu1=Double.parseDouble(num1),nu2=Double.parseDouble(num2);
            return String.valueOf(nu1+nu2);
        }else{
            Long nu1=Long.parseLong(num1),nu2=Long.parseLong(num2);
            return String.valueOf(nu1+nu2);
        }
    }
    public String convertSign(String num){
        if(num.equals("0"))return "0";

        if(num.charAt(0)=='-'){
            return num.substring(1);
        }else{
            return "-"+num;
        }
    }
    public String addDigit(String num,String digit){

        if(num.equals("0")){
            if(digit.equals("D")){
                return "0.";
            }
            else
                return digit;
        }else if(!digit.equals("D")){
            return num+ digit;
        }
        else if(num.indexOf('.') == -1)
        {
            return num+'.';
        }
        return num;
    }
}
