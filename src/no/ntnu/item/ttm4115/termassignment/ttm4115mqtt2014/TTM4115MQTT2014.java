package no.ntnu.item.ttm4115.termassignment.ttm4115mqtt2014;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.mqtt.MQTT.Message;

import no.ntnu.item.arctis.runtime.Block;

/**
 * Composed MQTT block that uses Reactive Blocks "instance parameters" instead of static properties.
 * This allows for using the block without writing overhead Java code.
 * The block uses hardcoded values and is intended to be used in the TTM4115 subject at NTNU.
 * http://www.item.ntnu.no/academics/courses/ttm4115/
 * 
 * Known problem: not possible to set QoS.
 * 
 * @author Y퓊n de Jong <git@yorn.priv.no>
 * @copyright 2014 Y퓊n de Jong <git@yorn.priv.no>
 */
public class TTM4115MQTT2014 extends Block {

	// Instance parameter. Edit only in overview page.
	public final int group;

	// Do not edit this constructor.
	public TTM4115MQTT2014(int group) {
	    this.group = group;
	}

	/**
	 * Generate a MQTT config object from instance variables.
	 * 
	 * @copyright 2014 Y퓊n de Jong <git@yorn.priv.no>
	 * @return Generated MQTTConfigParam instance
	 */
	public MQTTConfigParam generateConfig(String subscribeTopics) {
		String topicBase = "ntnu/item/ttm4115/2014/group-"+group;
		String clientId = MQTTConfigParam.generateUUID(); // not UUID, UID.
		MQTTConfigParam config = new MQTTConfigParam("broker.mqttdashboard.com", 1883, clientId);
		String[] topics = subscribeTopics.split(",");
		for(String topic : topics)
			if (!topic.equals(""))
				config.addSubscribeTopic(topicBase+"/"+topic);
		config.setDefaultPublishTopic(topicBase+"/default");
		return config;
	}

	/**
	 * Prepend a short topic string with a prefix
	 * 
	 * @copyright 2014 Y퓊n de Jong <git@yorn.priv.no>
	 * @return Message with prefixed topic
	 */
	public Message addTopicToMessage(Message message) {
		return new Message(message.getPayload(), "ntnu/item/ttm4115/2014/group-"+group+"/"+message.getTopic());
	}
	
	/**
	 * Remove the prefix from a topic, so that a short prefix remains
	 * 
	 * @copyright 2014 Y퓊n de Jong <git@yorn.priv.no>
	 * @return Message with short topic if prefix matches, long topic if prefix doesn't match
	 */
	public Message removeTopicFromMessage(Message message) {
		String topicBase = "ntnu/item/ttm4115/2014/group-"+group+"/";
		if (message.getTopic().startsWith(topicBase))
			return new Message(message.getPayload(), message.getTopic().substring(topicBase.length()));
		return message;
	}

}
