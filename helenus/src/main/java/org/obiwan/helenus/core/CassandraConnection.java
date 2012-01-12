/**
 * 
 */
package org.obiwan.helenus.core;

import org.scale7.cassandra.pelops.Cluster;
import org.scale7.cassandra.pelops.Pelops;

/**
 * @author DeFrancescoD
 *
 */
public class CassandraConnection extends AbstractConnection {
	


	/**
	 * Default constructor
	 */
	public CassandraConnection(){}
	

	/**
	 * 
	 * @param host
	 * @param port
	 * @param pool
	 * @param keyspace
	 */
	public CassandraConnection(String host, int port, String pool,
			String keyspace) {
		super(host, port, pool, keyspace);
	}

	/* (non-Javadoc)
	 * @see org.obiwan.helenus.core.AbstractConnection#initConnectionPool(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	protected void initConnectionPool(String host, String port, String pool,
			String keyspace) {
		Cluster cluster = new Cluster(host, new Integer(port).intValue());
		Pelops.addPool(pool, cluster, keyspace);
	}


	public void shutdownConnectionPool() {
		Pelops.shutdown();
	}
	
	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getPool() {
		return pool;
	}

	public String getKeyspace() {
		return keyspace;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public void setPool(String pool) {
		this.pool = pool;
	}


	public void setKeyspace(String keyspace) {
		this.keyspace = keyspace;
	}
}
