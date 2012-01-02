/**
 * 
 */
package org.obiwan.helenus.core.annotation;

import org.apache.cassandra.thrift.ConsistencyLevel;

/**
 * @author DeFrancescoD
 *
 */
public @interface ConnectionData {
	
	public String host() default "localhost";
	public int port() default 9160;
	public String pool() default "pool";
	public String keyspace() default "mykeyspace";
	public ConsistencyLevel consistencyLevel() default ConsistencyLevel.ONE;
}
