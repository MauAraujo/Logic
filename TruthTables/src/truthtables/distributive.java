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

class distributive extends logic_base{
	public distributive(String input){
		super(input);
	}
	public Boolean run(){
		Boolean flag = false;
		Pattern r = Pattern.compile("(\\d+)");
		int my_final = this.my_stack.size() -1;
		for(int i=0;i<this.my_stack.size();i++){
			String target = this.my_stack.get(i);
			if(! target.contains("+")){
				continue;
			}
			Matcher m = r.matcher(target);
			while(m.find()){
				String j = m.group(1);
				String child = this.my_stack.get(Integer.parseInt(j));
				if(!child.contains("*")){
					continue;
				}
				Pattern new_r = Pattern.compile("(^|\\s)" + j + "(\\s|$)");
				String[] items = child.split("\\s+\\*\\s+");
				String[] tmp_list = new String[items.length];
				for(int k = 0 ; k < items.length; k++){
					tmp_list[k] = Integer.toString(this.my_stack.size());
					Matcher new_m = new_r.matcher(target);
					this.my_stack.add(new_m.replaceAll(" "+items[k]+" ").trim());
				}
				this.my_stack.set(i,this.my_join(" * ",tmp_list));
				flag = true;
			}
			if(flag){
				break;
			}
		}
		this.my_stack.add(this.my_stack.get(my_final));
		return flag;
	}
}