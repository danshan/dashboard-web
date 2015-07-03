package com.dianping.wed.monitor.config.mongo;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.List;

public class MongoDAOClient {
	private Mongo mongo;

	public MongoDAOClient(String mongoUri){
		List<ServerAddress> servers = parseUri(mongoUri);
		mongo = new Mongo(servers,defaultOption());
	}
	
	public Morphia getMorphiaInstance(List<String> mapClasses){
		Morphia morphia = new Morphia();
		for(String clazz : mapClasses){
			try {
				morphia = morphia.map(Class.forName(clazz));
			} catch (ClassNotFoundException e) {
				continue;
			}
		}
		return morphia;
	}
	
	
	public Mongo getMongoInstance(){
		return mongo;
	}
	
	
	public MongoOptions defaultOption(){
		MongoOptions options = new MongoOptions();
		options.socketKeepAlive = true;
		options.socketTimeout = 5000;
		options.connectionsPerHost = 30;
		options.threadsAllowedToBlockForConnectionMultiplier = 50;
		options.w = 0;
		options.wtimeout = 5000;
		options.fsync = false;
		options.connectTimeout = 5000;
		options.maxWaitTime = 1000 * 60 * 2;
		options.autoConnectRetry = false;
		options.safe = false;
		return options;
	}
	private List<ServerAddress> parseUri(String uri) {
		String[] hostPortArr = uri.split(",");
		List<ServerAddress> result = new ArrayList<ServerAddress>();
		for (int i = 0; i < hostPortArr.length; i++) {
			String[] pair = hostPortArr[i].split(":");
			try {
				result.add(new ServerAddress(pair[0].trim(), Integer.parseInt(pair[1].trim())));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}
}
