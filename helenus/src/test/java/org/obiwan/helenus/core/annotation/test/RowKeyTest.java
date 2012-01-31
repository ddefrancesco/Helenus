package org.obiwan.helenus.core.annotation.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.obiwan.helenus.datamodel.RowKey;

public class RowKeyTest {
	
	private RowKey key ;
	
	@Before
	public void setUp() throws Exception {
		key = new RowKey();
	}

	@Test
	public void testCalcUniqueKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcUniqueKeyByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() throws UnsupportedEncodingException {
		byte[] bArray = key.getUniqueKey();
		String string = new String(bArray,"UTF-8");
		
	}

}
