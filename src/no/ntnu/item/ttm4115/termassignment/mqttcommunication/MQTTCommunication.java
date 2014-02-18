package no.ntnu.item.ttm4115.termassignment.mqttcommunication;

import com.bitreactive.library.mqtt.MQTTConfigParam;

import no.ntnu.item.arctis.runtime.Block;

/**
 * Composed MQTT block that uses Reactive Blocks "instance parameters" instead of static properties.
 * This allows for using the block without writing overhead Java code.
 * 
 * Known problem: not possible to set QoS.
 * 
 * @author Y¿rn de Jong <git@yorn.priv.no>
 * @copyright 2014 Y¿rn de Jong <git@yorn.priv.no>
 */
public class MQTTCommunication extends Block {

	// Instance parameter. Edit only in overview page.
	public final java.lang.String serverName;
	// Instance parameter. Edit only in overview page.
	public final int portNo;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String clientId;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String subscribeTopics;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String defaultPublishTopic;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String username;
	// Instance parameter. Edit only in overview page.
	public final java.lang.String password;

	/**
	 * Generate a MQTT config object from instance variables.
	 * 
	 * @copyright 2014 Y¿rn de Jong <git@yorn.priv.no>
	 * @return Generated MQTTConfigParam instance
	 */
	public MQTTConfigParam generateConfig() {
		String clientId = this.clientId.equals("") ? MQTTConfigParam.generateUUID() : this.clientId; // Not UUID, UID.
		MQTTConfigParam config = new MQTTConfigParam(serverName, portNo, clientId);
		String[] topics = subscribeTopics.split(",");
		for(String topic : topics)
			if (!topic.equals(""))
				config.addSubscribeTopic(topic);
		config.setDefaultPublishTopic(defaultPublishTopic);
		config.setUsername(username);
		config.setPassword(password);
		return config;
	}

	// Do not edit this constructor.
	public MQTTCommunication(java.lang.String serverName, int portNo, java.lang.String clientId, java.lang.String subscribeTopics, java.lang.String defaultPublishTopic, java.lang.String username, java.lang.String password) {
	    this.serverName = serverName;
	    this.portNo = portNo;
	    this.clientId = clientId;
	    this.subscribeTopics = subscribeTopics;
	    this.defaultPublishTopic = defaultPublishTopic;
	    this.username = username;
	    this.password = password;
	}

}
