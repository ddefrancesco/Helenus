/**
 * 
 */
package org.obiwan.helenus.core;

/**
 * @author DeFrancescoD
 *
 */
public abstract class AbstractConnection {
	
	protected String host;
	protected int port;
	protected String pool;
	protected String keyspace;
	
	public AbstractConnection(){}
	
	public AbstractConnection(String host,int port,String pool,String keyspace){
		this.host = host;
		this.port = port;
		this.pool = pool;
		this.keyspace = keyspace;
	}
	
	protected abstract void initConnectionPool(String host,String port,String pool,String keyspace);
	
	protected abstract void shutdownConnectionPool();
}
