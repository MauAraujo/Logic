package com.example.mau.logic;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author camila
 */

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class mainclass {

    static ArrayList<EquationVariables> variableArray = new ArrayList<>();
    static ArrayList<Object> equationArray = new ArrayList<>();

    static Boolean merging(logic_base source){
        String old = source.get_result();
        source.merge_items("+");
        source.merge_items("*");
        return !old.equals(source.get_result());
    }
    static ArrayList<String> run (String input){
        ArrayList<String> all = new ArrayList<String>();
        //all.add(input);

        ordering zero = new ordering(input);
        while(zero.run()){
            zero = new ordering(zero.get_result());
        }
        merging(zero);

        replace_iff one = new replace_iff(zero.get_result());
        one.run();
        all.add(one.get_result());
        merging(one);

        replace_imp two = new replace_imp(one.get_result());
        two.run();
        all.add(two.get_result());
        merging(two);

        de_morgan three = new de_morgan(two.get_result());
        while(three.run()){
            three = new de_morgan(three.get_result());
        }
        all.add(three.get_result());
        merging(three);

        distributive four = new distributive(three.get_result());
        while(four.run()){
            four = new distributive(four.get_result());
        }
        merging(four);

        simplification five = new simplification(four.get_result());
        five.run();
        all.add(five.get_result());

        return all;
    }

    public static void read(String line) {
        //String line;
        //Scanner scanner=new Scanner(System.in);
        //line=scanner.nextLine();
        ArrayList<String> tmps = run(line);
        for(String tmp:tmps)
        {
            System.out.println(tmp);
        }

        String equation=tmps.get(tmps.size() - 1);
        equation = Bridge.tokenize(equation);

        equation = equation.replaceAll(" ", "");
        equation = equation.toLowerCase();
        int counter = 1;
        //loops through the equation and stores all characters between a and z in a variable array.
        for (int i = 0; i < equation.length();i++){
            if (equation.charAt(i)>='a' && equation.charAt(i)<='z') {
                boolean alreadyExists = false;
                EquationVariables temp = new EquationVariables(equation.charAt(i),true, counter);

                //checks for duplicate letters and doesn't add them to the array twice
                for (EquationVariables v : variableArray){
                    if (v.getName()==temp.getName()){
                        alreadyExists = true;
                        temp = v;
                    }
                }
                if (!alreadyExists){
                    variableArray.add(temp);
                    //doubles the significant bit for each variable that is added
                    //First variable has 1, second has 2, third has 4, and so on
                    counter = counter*2;
                }

                //stores the variable objects that are created in an equation array as well
                equationArray.add(temp);
            }else{
                //any non-letter characters get stored in an equation array
                equationArray.add(equation.charAt(i));
            }
        }

        if (variableArray.size() > 0){
            //Creates an instance of the truth table class with the proper parameters
            TruthTableGUI table = new TruthTableGUI(variableArray, equationArray);
            table.constructTable();
        }else{
            System.out.println("No variables found");
        }
    }
}
