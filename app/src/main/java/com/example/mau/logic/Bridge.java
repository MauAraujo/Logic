package com.example.mau.logic;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Bridge {

    public static String tokenize(String equation){
        String[] cad = equation.split("\\(+");

        for (String aCad : cad) System.out.println(aCad);

        return Build(cad);
    }

    public static String Build(String[] cad){
        StringBuilder maker = new StringBuilder();
        ArrayList<String> correct = new ArrayList<String>();

        for (String aCad : cad) {
            if (aCad.contains(")")) {
                correct.add(aCad.replaceAll("\\)+", ""));
            }
        }

        for (String x:correct) {
            System.out.println(x);
        }

        for(int i = 0; i < correct.size(); i++) {
            maker.append(correct.get(i));
        }

        System.out.println(maker.toString());

        return maker.toString();
    }

    public static void main(String[] args){
        Bridge.tokenize("(! a + b) * (! c + (d * x)) + (! f + (g + (h * ! h))) + (p'* ! q * r)");
    }
}
