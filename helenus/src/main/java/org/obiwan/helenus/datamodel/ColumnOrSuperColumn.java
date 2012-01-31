/**
 * 
 */
package org.obiwan.helenus.datamodel;

import java.io.Serializable;

/**
 * @author DeFrancescoD
 *
 */
public class ColumnOrSuperColumn implements Serializable{

	
	private static final long serialVersionUID = -6480196002664159277L;
	
	Class<?> column;

	public Class<?> getColumn() {
		return column;
	}

	public void setColumn(Class<?> column) {
		this.column = column;
	}

}
