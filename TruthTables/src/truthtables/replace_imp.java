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

class replace_imp extends logic_base{
	public replace_imp(String input){
		super(input);
	}
	public Boolean run(){
		Boolean flag = false;
		for(int i=0;i<this.my_stack.size();i++){
			String ans = this.replace_imp_inner(this.my_stack.get(i));
			if(ans.length() == 0){
				continue;
			}
			this.my_stack.set(i,ans);
			flag = true;
		}
		return flag;
	}
	private String replace_imp_inner(String source){
		Pattern r = Pattern.compile("^(.*?)\\s+imp\\s+(.*?)$");
		Matcher m = r.matcher(source);
		if(! m.find()){
			return "";
		}
		String a = m.group(1);
		String b = m.group(2);
		if(a.contains("neg ")){
			return a.replace("! ", "") + " + " + b;
		}
		return "! " + a + " + " + b;
	}
}
