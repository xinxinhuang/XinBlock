/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xinblock;

/**
 *
 * @author 721292
 */
public class Util {
	
	public static String zeros(int length) {
		StringBuilder builder = new StringBuilder();
		
		for (int i=0; i<length; i++) {
			builder.append("0");
		}
		return builder.toString();
	}

}
