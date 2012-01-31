/**
 * 
 */
package org.obiwan.helenus.util;

import org.obiwan.helenus.datamodel.ByteArrayValue;
import org.obiwan.helenus.datamodel.NumericValue;

/**
 * @author DeFrancescoD
 *
 */
public class DataHandler {

	public static byte[] toByteArray (Object obj) 
	{
		
		
	  byte[] bytes = null;
/*	  if(obj instanceof ByteArrayValue){
		  ByteArrayValue val = (ByteArrayValue)obj;
		  bytes = val.getData().getBytes();
	  }
	  else if(obj instanceof NumericValue){
		  NumericValue val = (NumericValue)obj;
		  bytes = val.toByteArray(val.getData());
	  }*/
/*	  ByteArrayOutputStream bos = new ByteArrayOutputStream();
	  try {
	    ObjectOutputStream oos = new ObjectOutputStream(bos); 
	    oos.writeObject(obj);
	    oos.flush(); 
	    oos.close(); 
	    bos.close();
	    bytes = bos.toByteArray ();
	  }
	  catch (IOException ex) {
	    //TODO: Handle the exception
	  }*/
	  return bytes;
	}
	
	
}
