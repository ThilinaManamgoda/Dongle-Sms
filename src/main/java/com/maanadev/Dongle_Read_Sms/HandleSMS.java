package com.maanadev.Dongle_Read_Sms;

import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;

public class HandleSMS implements IInboundMessageNotification {

	public HandleSMS() {

	}

	// This method is invoked when a message arrive
	public void process(AGateway gateway, MessageTypes messageType, InboundMessage message) {
		System.out.println("Gateway :" + message.getGatewayId());
		System.out.println("Sender :" + message.getOriginator());
		System.out.println("Text :" + message.getText());

	}

}
