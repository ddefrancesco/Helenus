/**
 * 
 */
package org.obiwan.helenus.datamodel;

import java.io.Serializable;

/**
 * @author DeFrancescoD
 *
 */
public class ColumnOrSuperColumn<T extends BaseColumn> implements Serializable{

	
	private static final long serialVersionUID = -6480196002664159277L;
	
	T column;

	public T getColumn() {
		return column;
	}

	public void setColumn(T column) {
		this.column = column;
	}

}
