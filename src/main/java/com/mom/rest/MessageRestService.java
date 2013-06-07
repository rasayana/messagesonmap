package com.mom.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.mom.mongo.Message;
import com.mom.pusher.Pusher;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@Path("/messages")
public class MessageRestService {

	@Inject
	private DB db;

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public void submit(Message message) throws Exception {
		DBCollection messages = db.getCollection("messages");
		BasicDBObject object = new BasicDBObject();
		object.put("message", message.getMessage());
		object.put("location", message.getLocation());
		messages.insert(object);
		
		Pusher.triggerPush("messages", "new_msg", toJson(message));
		
		long count = messages.count();
		Pusher.triggerPush("counter-channel", "count-event", toJson(new Counter(count)));
	}

	
	private static <T> String  toJson(T obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e){
			return "Unable to generate json";
		}
	}
}
