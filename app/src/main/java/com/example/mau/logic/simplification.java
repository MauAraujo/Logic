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

class simplification extends logic_base{
    public simplification(String input){
        super(input);
    }
    public Boolean run(){
        String old = this.get_result();
        for(int i=0;i<this.my_stack.size();i++){
            this.my_stack.set(i,this.reducing_or(this.my_stack.get(i)));
        }
        String my_final = this.my_stack.get(this.my_stack.size()-1);
        int the_size = this.my_stack.size();
        this.my_stack.set(the_size-1,this.reducing_and(this.my_stack.get(the_size-1)));
        return old.length() != this.get_result().length();
    }
    public String reducing_and(String target){
        if(!target.contains("\\*")){
            return target;
        }
        Set<String> items = new HashSet<String>(Arrays.asList(target.split("\\s+\\*\\s+")));
        for(String item : items){
            if(items.contains("! "+item)){
                return "";
            }
            Pattern r = Pattern.compile("\\d+$");
            Matcher m = r.matcher(item);
            if(!m.find()){
                continue;
            }
            String value = this.my_stack.get(Integer.parseInt(item));
            if(Collections.frequency(this.my_stack, value) > 1){
                this.my_stack.set(Integer.parseInt(item),"");
            }
        }
        for(int i=0;i<this.my_stack.size()-1;i++){

            if(this.my_stack.get(i).length() == 0){
                items.remove(Integer.toString(i));
            }
        }
        return this.my_join(" * ", items.toArray(new String[items.size()]));
    }
    public String reducing_or(String target){
        if(!target.contains("+")){
            return target;
        }
        Set<String> items = new HashSet<String>(Arrays.asList(target.split("\\s+\\+\\s+")));
        for(String item : items){
            if(items.contains("! "+item)){
                return "";
            }
        }
        return this.my_join(" + ", items.toArray(new String[items.size()]));
    }
}
