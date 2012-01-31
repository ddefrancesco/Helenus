/**
 * 
 */
package org.obiwan.helenus.core.annotation.test;

import java.io.UnsupportedEncodingException;

import org.obiwan.helenus.datamodel.RowKey;

/**
 * @author DeFrancescoD
 *
 */
public class RowKeyMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RowKey key = new RowKey();
		
		byte[] bArray = key.getUniqueKey();
		
		try {
			String string = new String(bArray,"UTF-8");
			
			System.out.println("toString semplice: "+ string);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
