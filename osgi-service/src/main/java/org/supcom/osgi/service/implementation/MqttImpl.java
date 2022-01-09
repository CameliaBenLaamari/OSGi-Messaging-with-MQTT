package org.supcom.osgi.service.implementation;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.supcom.osgi.service.definition.MqttAdapter;

import java.util.Hashtable;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttImpl implements MqttAdapter, BundleActivator {

    private ServiceReference<MqttAdapter> reference;
    private ServiceRegistration<MqttAdapter> registration;

    @Override
    public void MqttApp(String topic, MqttMessage mqttMessage) throws MqttException {
        MqttCallBack mqttCallback = new MqttCallBack();
        try {
            mqttCallback.messageArrived(topic, mqttMessage);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Registering service.");
        registration = context.registerService(
                MqttAdapter.class,
                new MqttImpl(),
                new Hashtable<String, String>());
        reference = registration.getReference();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Unregistering service.");
        registration.unregister();
    }
}
