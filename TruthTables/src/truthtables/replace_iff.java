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

class replace_iff extends logic_base{
	public replace_iff(String input){
		super(input);
	}
	public Boolean run(){
		int my_final = this.my_stack.size() -1;
		Boolean flag = this.replace_all_iff();
		this.my_stack.add(this.my_stack.get(my_final));
		return flag;
	}
	private Boolean replace_all_iff(){
		Boolean flag = false;
		for(int i=0;i<this.my_stack.size();i++){
			Triple_string ans = this.replace_iff_inner(this.my_stack.get(i),this.my_stack.size());
			if(!ans.flag){
				continue;
			}
			this.my_stack.set(i, ans.one);
			this.my_stack.add(ans.two);
			this.my_stack.add(ans.three);
			flag = true;
		}
		return flag;
	}
	private Triple_string replace_iff_inner(String source, int id){
		Pattern r = Pattern.compile("^(.*?)\\s+iff\\s+(.*?)$");
		Matcher m = r.matcher(source);
		if(! m.find()){
			Triple_string tmp = new Triple_string();
			tmp.flag = false;
			return tmp;
		}
		String a = m.group(1);
		String b = m.group(2);
		Triple_string tmp = new Triple_string();
		tmp.flag = true;
		tmp.one = Integer.toString(id) + " * " + Integer.toString(id+1);
		tmp.two = a + " imp " + b;
		tmp.three = b + " imp " + a;
		return tmp;
	}
}