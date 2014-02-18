package no.ntnu.item.ttm4115.termassignment.messagecreator;

import com.bitreactive.library.mqtt.mqtt.MQTT.Message;

import no.ntnu.item.arctis.runtime.Block;

/**
 * Generate a Message config object from a payload and a topic
 * 
 * @copyright 2014 Y¿rn de Jong <git@yorn.priv.no>
 */
public class MessageCreator extends Block {

	/**
	 * Generate a Message config object from a payload and a topic
	 * 
	 * @copyright 2014 Y¿rn de Jong <git@yorn.priv.no>
	 * @return Message with the specified payload and topic
	 */
	public Message createMessage(byte[] payload, String topic) {
		return new Message(payload, topic);
	}

}
