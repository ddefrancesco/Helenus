/**
 * 
 */
package org.obiwan.helenus.datamodel;

/**
 * @author <a href="mailto:ddefrancesco@gmail.com">Daniele De Francesco</a> 
 *
 */
public class ByteArrayColumn extends GenericColumn<ByteArrayValue> {

	private static final long serialVersionUID = 269314193399193178L;

	/**
	 * Default constructor
	 */
	public ByteArrayColumn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param value
	 */
	public ByteArrayColumn(String name, ByteArrayValue value) {
		super(name, value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public ByteArrayColumn(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
}
