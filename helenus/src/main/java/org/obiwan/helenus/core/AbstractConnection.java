/**
 * 
 */
package org.obiwan.helenus.core;

/**
 * @author DeFrancescoD
 *
 */
public abstract class AbstractConnection {
	
	protected abstract void initConnectionPool(String host,String port,String pool,String keyspace);

}
