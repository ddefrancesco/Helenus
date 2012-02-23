/**
 * 
 */
package org.obiwan.helenus.core.factory;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import org.apache.cassandra.thrift.ConsistencyLevel;
import org.obiwan.helenus.core.CassandraConnection;
import org.obiwan.helenus.core.annotation.ConnectionData;
import org.obiwan.helenus.core.exception.AnnotationNotPresentException;
import org.scale7.cassandra.pelops.Cluster;
import org.scale7.cassandra.pelops.Pelops;

/**
 * @author <a href="mailto:ddefrancesco@gmail.com">Daniele De Francesco</a> 
 *
 */
public class ConnectionFactory implements Serializable {

	private static final long serialVersionUID = 8730049052048530698L;


	
	public static CassandraConnection initInstance(Class clazz) throws AnnotationNotPresentException{
		
		CassandraConnection conn = new CassandraConnection();

		ConsistencyLevel CL = ConsistencyLevel.ONE;
		
		if(clazz.isAnnotationPresent(ConnectionData.class)) {
			Annotation[] annotations = clazz.getAnnotations();
			System.out.println("No. of annotations: " + annotations.length);
			for (Annotation annotation : annotations) {
				ConnectionData connAnnotation = (ConnectionData)annotation; 
				conn.setHost(connAnnotation.host()) ;
				conn.setPort(connAnnotation.port());
				conn.setPool(connAnnotation.pool());
				conn.setKeyspace(connAnnotation.keyspace()) ;
				CL = connAnnotation.consistencyLevel();
			}			
		}else{
			throw new AnnotationNotPresentException();
		}
		
		Cluster cluster = new Cluster(conn.getHost(), conn.getPort());
		Pelops.addPool(conn.getPool(), cluster, conn.getKeyspace());
		
		return conn;
		
	}



	
	
}
