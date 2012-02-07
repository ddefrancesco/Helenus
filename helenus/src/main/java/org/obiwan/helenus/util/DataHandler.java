/**
 * 
 */
package org.obiwan.helenus.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * @author DeFrancescoD
 *
 */
public class DataHandler {

	public static byte[] toByteArray (Object obj) 
	{
		
		
	  byte[] bytes = null;
	  if(obj instanceof String){
		  String val = (String)obj;
		  bytes = val.getBytes();
	  }
	  else if(obj instanceof Integer){
		  Integer val = (Integer)obj;
		  bytes = ByteBuffer.allocate(4).putInt(val).array();
		 
	  }
	  else if(obj instanceof Long){
		
		  Long val = (Long)obj;
		  bytes = ByteBuffer.allocate(4).putLong(val).array();
		}
		
	  else if(obj instanceof Float){
			
		  Float val = (Float)obj;
		  bytes = ByteBuffer.allocate(4).putFloat(val).array();	
		}
	  
	  else if(obj instanceof Double){
			
		  Double val = (Double)obj;
		  bytes = ByteBuffer.allocate(4).putDouble(val).array();	
		}	 
	  else if(obj instanceof BigInteger){
			
		  BigInteger val = (BigInteger)obj;
		 
		  bytes = ByteBuffer.allocate(4).putDouble(val.doubleValue()).array();	
		}
	  else if(obj instanceof Double){
			
		  Double val = (Double)obj;
		  bytes = ByteBuffer.allocate(4).putDouble(val).array();	
		}
	  else if(obj instanceof Boolean){
		  boolean[] boolArray = new boolean[4];
		  boolArray[0] = ((Boolean) obj).booleanValue();

		  bytes = toByta(boolArray);
		  
		  
		}	  
	  

//	else if (parameterTypes[j] == BigInteger.class) {
//
//		valClazz = new Value<BigInteger>();
//		valClazz.setData(new BigInteger("0").getClass());
//		valClazz.setTimestamp(System.nanoTime());
//	} 
//	else if (parameterTypes[j] == BigDecimal.class) {
//
//		valClazz = new Value<BigDecimal>();
//		valClazz.setData(new BigDecimal(0).getClass());
//		valClazz.setTimestamp(System.nanoTime());
//	}

	  return bytes;
	}
	
	private static byte[] toByta(boolean[] data) {
	    // Advanced Technique: The byte array containts information
	    // about how many boolean values are involved, so the exact
	    // array is returned when later decoded.
	    // ----------
	    if (data == null) return null;
	    // ----------
	    int len = data.length;
	    byte[] lena = toByta(len); // int conversion; length array = lena
	    byte[] byts = new byte[lena.length + (len / 8) + (len % 8 != 0 ? 1 : 0)];
	    // (Above) length-array-length + sets-of-8-booleans +? byte-for-remainder
	    System.arraycopy(lena, 0, byts, 0, lena.length);
	    // ----------
	    // (Below) algorithm by Matthew Cudmore: boolean[] -> bits -> byte[]
	    for (int i = 0, j = lena.length, k = 7; i < data.length; i++) {
	        byts[j] |= (data[i] ? 1 : 0) << k--;
	        if (k < 0) { j++; k = 7; }
	    }
	    // ----------
	    return byts;
	}
	
	private static byte[] toByta(int data) {
	    return new byte[] {
	        (byte)((data >> 24) & 0xff),
	        (byte)((data >> 16) & 0xff),
	        (byte)((data >> 8) & 0xff),
	        (byte)((data >> 0) & 0xff),
	    };
	}
}
