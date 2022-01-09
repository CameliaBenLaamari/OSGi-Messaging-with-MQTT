package org.supcom.osgi.service.definition;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttAdapter {
    public void MqttApp(String topic, MqttMessage mqttMessage) throws MqttException;
}