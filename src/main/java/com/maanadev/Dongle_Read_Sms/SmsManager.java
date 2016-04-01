package com.maanadev.Dongle_Read_Sms;

import java.io.IOException;

import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

public class SmsManager implements SMSConfig {

	private Service service;
	private SerialModemGateway gateway;

	public SmsManager() {
	}

	public void Initialize() throws Exception {

		// Configure Dongle
		gateway = new SerialModemGateway(MODEM, PORT, FREQUENCY, "", MODEM_VERSION);
		// setting up the gateway for send and receive MSG
		gateway.setInbound(true);
		gateway.setOutbound(true);
		// configure Service
		service = Service.getInstance();
		// add gateway to service
		service.addGateway(gateway);
		// Setting HandleSMS class to handle receive SMS
		service.setInboundMessageNotification(new HandleSMS());
		service.startService();
		System.out.println("SERVER: Started!");

	}

	private void sendMessage(String msg, String number) {
		OutboundMessage message = new OutboundMessage(number, msg);
		message.setGatewayId(gateway.getGatewayId());
		try {
			service.sendMessage(message);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (GatewayException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		SmsManager app = new SmsManager();
		try {
			app.Initialize();
			app.sendMessage("Test", "xxxxxPHN NUMxxxxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}