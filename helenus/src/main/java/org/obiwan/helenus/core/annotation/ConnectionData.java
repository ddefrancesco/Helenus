/**
 * 
 */
package org.obiwan.helenus.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.cassandra.thrift.ConsistencyLevel;

/**
 * @author DeFrancescoD
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConnectionData {
	
	public String host() default "localhost";
	public int port() default 9160;
	public String pool() default "pool";
	public String keyspace() default "mykeyspace";
	public ConsistencyLevel consistencyLevel() default ConsistencyLevel.ONE;
}
