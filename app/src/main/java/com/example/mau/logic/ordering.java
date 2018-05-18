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

class ordering extends logic_base{
    public ordering(String input){
        super(input);
    }
    public Boolean run(){
        Boolean flag = false;
        for(int i=0;i<this.my_stack.size();i++){
            String old = this.my_stack.get(i);
            String new_string = this.add_brackets(old);
            if(!old.equals(new_string)){
                this.my_stack.set(i,new_string);
                flag = true;
            }
        }
        return flag;
    }
    private String add_brackets(String source){
        int count = 0;
        Pattern reg = Pattern.compile("\\s+(\\*|\\+|imp|iff)\\s+");
        Matcher m = reg.matcher(source);
        while(m.find()){ count+=1; }
        if(count < 2){
            return source;
        }
        Pattern reg_and = Pattern.compile("(!\\s+)?\\S+\\s+\\*\\s+(!\\s+)?\\S+");
        m = reg_and.matcher(source);
        if(m.find()){
            return m.replaceFirst("("+m.group(0)+")");
        }
        Pattern reg_or = Pattern.compile("(!\\s+)?\\S+\\s+\\+\\s+(!\\s+)?\\S+");
        m = reg_or.matcher(source);
        if(m.find()){
            return m.replaceFirst("("+m.group(0)+")");
        }
        Pattern reg_imp = Pattern.compile("(!\\s+)?\\S+\\s+imp\\s+(!\\s+)?\\S+");
        m = reg_imp.matcher(source);
        if(m.find()){
            return m.replaceFirst("("+m.group(0)+")");
        }
        Pattern reg_iff = Pattern.compile("(!\\s+)?\\S+\\s+iff\\s+(!\\s+)?\\S+");
        m = reg_iff.matcher(source);
        if(m.find()){
            return m.replaceFirst("("+m.group(0)+")");
        }
        return source;
    }
}
