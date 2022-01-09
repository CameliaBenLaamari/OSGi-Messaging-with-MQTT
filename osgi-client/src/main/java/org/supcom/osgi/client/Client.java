package org.supcom.osgi.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.osgi.framework.*;
import org.supcom.osgi.service.definition.MqttAdapter;
import org.supcom.osgi.service.implementation.MqttCallBack;

public class Client implements BundleActivator, ServiceListener {

    private BundleContext ctx;
    private ServiceReference serviceReference;
    private static MqttClient client;

    public void start(BundleContext ctx) throws MqttException {
        this.ctx = ctx;
        try {
            System.out.println("== START SUBSCRIBER ==");
            client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
        } catch (MqttException ise) {
            ise.printStackTrace();
        }
    }

    public void stop(BundleContext bundleContext) {
        if (serviceReference != null) {
            ctx.ungetService(serviceReference);
        }
        this.ctx = null;
    }

    public void serviceChanged(ServiceEvent serviceEvent) {
        int type = serviceEvent.getType();
        switch (type) {
            case (ServiceEvent.REGISTERED):
                System.out.println("Notification of service registered.");
                serviceReference = serviceEvent.getServiceReference();
                MqttAdapter service = (MqttAdapter) (ctx.getService(serviceReference));
                client.setCallback(new MqttCallBack());
                try {
                    client.connect();
                    client.subscribe("OSGi_MQTT");
                } catch (MqttException e) {
                    System.out.println(e);
                }

                break;
            case (ServiceEvent.UNREGISTERING):
                System.out.println("Notification of service unregistered.");
                ctx.ungetService(serviceEvent.getServiceReference());
                break;
            default:
                break;
        }
    }
}