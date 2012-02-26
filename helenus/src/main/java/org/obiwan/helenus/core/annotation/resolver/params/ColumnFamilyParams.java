/**
 * 
 */
package org.obiwan.helenus.core.annotation.resolver.params;

import org.obiwan.helenus.core.enumeration.Type;

/**
 * @author DeFrancescoD
 *
 */
public class ColumnFamilyParams {
	
		private String family;
		private String[] columnNames;
		private Type[] types;
		private boolean superColumn;
		
		public String getFamily() {
			return family;
		}
		public void setFamily(String family) {
			this.family = family;
		}
		public String[] getColumnNames() {
			return columnNames;
		}
		public void setColumnNames(String[] columnNames) {
			this.columnNames = columnNames;
		}
		public Type[] getTypes() {
			return types;
		}
		public void setTypes(Type[] types) {
			this.types = types;
		}
		public boolean isSuperColumn() {
			return superColumn;
		}
		public void setSuperColumn(boolean superColumn) {
			this.superColumn = superColumn;
		}
		
	
}
