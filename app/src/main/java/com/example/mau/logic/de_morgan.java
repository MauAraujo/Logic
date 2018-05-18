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

class de_morgan extends logic_base{
    public de_morgan(String input){
        super(input);
    }
    public Boolean run(){
        Boolean flag = false;
        Pattern r = Pattern.compile("!\\s+(\\d+)");
        int my_final = this.my_stack.size() - 1;
        for(int i=0;i<this.my_stack.size();i++){
            String target = this.my_stack.get(i);
            Matcher m = r.matcher(target);
            if(! m.find()){
                continue;
            }
            String child = this.my_stack.get(Integer.parseInt(m.group(1)));
            this.my_stack.set(i,m.replaceFirst(Integer.toString(this.my_stack.size())));
            this.my_stack.add(this.doing_de_morgan(child));
            flag = true;
            break;
        }
        this.my_stack.add(this.my_stack.get(my_final));
        return flag;
    }
    private String doing_de_morgan(String source){
        String[] items = source.split("\\s+");
        ArrayList<String> new_items = new ArrayList<String>();
        for(int i = 0;i<items.length;i++){
            if(items[i].contains("+")){
                new_items.add("*");
            }
            else if(items[i].contains("*")){
                new_items.add("+");
            }
            else if(items[i].contains("!")){
                new_items.add("!");
            }
            else if(items[i].trim().length() > 0){
                new_items.add("!");
                new_items.add(items[i]);
            }
        }
        ArrayList<String> tmps = new ArrayList<String>();
        for(int i =0; i< new_items.size();i++){
            if(new_items.get(i).equals("!")){
                if(new_items.get(i+1).equals("!")){
                    new_items.set(i,"");
                    new_items.set(i+1,"");
                }
            }
            if(new_items.get(i).length() < 1){
                continue;
            }
            tmps.add(new_items.get(i));
        }
        String[] array = tmps.toArray(new String[tmps.size()]);
        return this.my_join(" ",array);
    }
}