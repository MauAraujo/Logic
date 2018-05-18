package com.example.mau.logic;

import java.util.ArrayList;

public class Bridge {

    public static ArrayList<String> tokenize(String equation){
        String[] cad = equation.split("\\)+");

        System.out.println("Cad");
        for (String aCad : cad) System.out.println(aCad);

        return Build(cad);
    }

    public static ArrayList<String> Build(String[] cad){
        ArrayList<String> formulas = new ArrayList<String>();

        //Quitar parentesis ) y operadores al final
        for (String aCad : cad) {
            if (aCad.contains("(")) {
                formulas.add(aCad.replaceAll("[+*]*\\s*[+*]*\\(+", ""));
            }

            else if(aCad.matches("\\s*.*[a-zA-z]\\s*[+*]+\\s*[a-zA-Z]\\s*")){
                String[] split = aCad.split("[+*]");
                for (String x: split) {
                    if(!(x.isEmpty()) && !(x.equals(" ")))
                        formulas.add(x);
                }

            }

            else if(aCad.contains("+")){
                //if (aCad.matches(".+\\s*[+*]+\\s*")) {
                //formulas.add(aCad.replaceAll(".+[*+]", aCad.replace("+", "")));

                formulas.add(aCad.replace("+", ""));
            }
            else if(aCad.contains("*")){
                //if (aCad.matches(".+\\s*[+*]+\\s*")) {
                //formulas.add(aCad.replaceAll(".+[*+]", aCad.replace("+", "")));

                formulas.add(aCad.replace("*", ""));
            }

            else if(!(aCad.isEmpty())){
                formulas.add(aCad);
            }
        }

        System.out.println("formulas");
        for (String x:formulas) {
            System.out.println(x);
        }

        return formulas;
    }

    public static String removeParen(String complete_formula){
        String result;
        result = complete_formula.replace(")", "");
        result = result.replace("(", "");

        return result;
    }

    public static void main(String[] args){
        Bridge.tokenize("(! (a + b) * (c * d)) * d + f");
        //Bridge.tokenize("(! a + b) * c");
    }
}
