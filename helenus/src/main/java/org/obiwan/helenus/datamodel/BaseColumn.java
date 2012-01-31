/**
 * 
 */
package org.obiwan.helenus.datamodel;

import java.io.Serializable;

/**
 * @author DeFrancescoD
 *
 */
public class BaseColumn  implements Serializable  {
	
	private static final long serialVersionUID = 3047129026270684008L;
	
	private String name;
	
	public BaseColumn(){};
	
	public BaseColumn(String name) {
		
		this.name = name;
	}

	}
