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

	/* (non-Javadoc)
	 * @see org.obiwan.helenus.core.AbstractConnection#initConnectionPool(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	protected void initConnectionPool(String host, String port, String pool,
			String keyspace) {
		Cluster cluster = new Cluster(host, new Integer(port).intValue());
		Pelops.addPool(pool, cluster, keyspace);
	}

}
