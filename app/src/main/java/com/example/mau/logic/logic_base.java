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

class logic_base{
    public String source;
    public ArrayList<String> my_stack;
    private Double_string remove_brackets(String source, int id){
        String reg = "\\(([^\\(]*?)\\)";
        Pattern r = Pattern.compile(reg);
        Matcher m = r.matcher(source);
        if(! m.find()){
            Double_string tmp = new Double_string();
            tmp.flag = false;
            return tmp;
        }
        Double_string tmp = new Double_string();
        tmp.flag = true;
        tmp.one = m.replaceFirst(Integer.toString(id));
        tmp.two = m.group(1);
        return tmp;
    }
    public String my_join(String one, String[] array){
        String result = "";
        for(int i = 0; i < array.length-1;i++){
            result = result + array[i] + one;
        }
        return result + array[array.length-1];
    }
    public logic_base(String input){
        this.my_stack = new ArrayList<String>();
        String my_final = input;
        this.source = input;
        while(true){
            Double_string tmp = this.remove_brackets(input, this.my_stack.size());
            if(tmp.flag == false){
                break;
            }
            input = tmp.one;
            my_final = input;
            this.my_stack.add(tmp.two);
        }
        this.my_stack.add(my_final);
    }
    public String get_result(){
        String root = this.my_stack.get(this.my_stack.size()-1);
        Pattern r0 = Pattern.compile("^\\s*([0-9]+)\\s*$");
        Matcher m0 = r0.matcher(root);
        if(m0.find()){
            root = this.my_stack.get(Integer.parseInt(m0.group(1)));
        }
        while(true){
            Pattern r = Pattern.compile("(\\d+)");
            Matcher m = r.matcher(root);
            if(! m.find()){
                break;
            }
            String new_string = "("+ this.my_stack.get(Integer.parseInt(m.group(1))) + ")";
            root = m.replaceFirst(new_string);
        }
        return root;
    }
    public void merge_items(String logic){
        Pattern r0 = Pattern.compile("(\\d+)");
        Pattern r1 = Pattern.compile("!\\s+(\\d+)");
        Boolean flag = false;
        for(int i=0;i<this.my_stack.size();i++){
            String target = this.my_stack.get(i);
            if(! target.contains(logic)){
                continue;
            }
            Matcher m1 = r1.matcher(target);
            if(m1.find()){
                continue;
            }
            Matcher m0 = r0.matcher(target);
            while(m0.find()){
                String j = m0.group(1);
                String child = this.my_stack.get(Integer.parseInt(j));
                if(! child.contains(logic)){
                    continue;
                }
                Pattern new_r = Pattern.compile("(^|\\s)" + j + "(\\s|$)");
                Matcher new_m = new_r.matcher(this.my_stack.get(i));
                this.my_stack.set(i, new_m.replaceFirst(" "+child+" ").trim());
                flag = true;
            }
        }
        if(flag){
            this.merge_items(logic);
        }
    }
}
