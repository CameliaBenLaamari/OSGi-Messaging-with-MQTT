# OSGi Messaging with MQTT

## Setup
1. Apache Karaf:
    - Download the latest version of Karaf from https://karaf.apache.org/download
    - Extract all the files
    - Create a new environment variable `KARAF_HOME` that refers to the download folder path
2. Apache Maven:
    - Download the latest version of Maven from https://maven.apache.org/download.cgi
    - Extract all the files
    - Add the `/bin` folder path of Maven to the `PATH` environment variable
3. Eclipse Mosquitto:
    - Download the latest version of Mosquitto from https://mosquitto.org/download
    - Follow the installer instructions


## Steps
### Start the Mosquitto MQTT broker
1. In the terminal, navigate to Mosquitto installation directory. By default, it should be:
```
C:\Users\Ahmed> cd /Program Files/mosquitto
```
2. Start the broker by running:
```
C:\Program Files\mosquitto> mosquitto
```
3. Open a new terminal window in Mosquitto's installation directory and use it to publish messages:
```
C:\Program Files\mosquitto> mosquitto_pub -h localhost -t OSGi_MQTT -m "Hello World!"
```

### Start the OSGi bundles
1. Run `mvn install` in the local directory to install the dependencies:
```
[INFO] ------------------------------------------------------------
[INFO] Reactor Summary for osgi 1.0:
[INFO]
[INFO] osgi ............................................... SUCCESS
[INFO] osgi-bundle ........................................ SUCCESS
[INFO] osgi-service ....................................... SUCCESS
[INFO] osgi-client ........................................ SUCCESS
[INFO] ------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------
```
2. Run `%KARAF_HOME%/bin/karaf.bat` to launch Karaf
3. Run
```
karaf@root()> bundle:install mvn:org.supcom/osgi-bundle/1.0
Bundle ID: <Bundle ID>
karaf@root()> install mvn:org.supcom/osgi-service/1.0
Bundle ID: <Service Bundle ID>
karaf@root()> install mvn:org.supcom/osgi-client/1.0
Bundle ID: <Client Bundle ID>
```
4. Run `start <Service Bundle ID>` to register the service to the platform when the BundleActivator starts
5. Run `start <Client Bundle ID>` to invoke the implementation delivered through the service bundle. The command should return:
```
Registering service.
Notification of service registered.
== START SUBSCRIBER ==
```
The client bundle is listening to `tcp://localhost:1883` by default and is subscribed to the topic OSGi_MQTT.

6. Each time a user publishes a new message in the topic OSGi_MQTT, the client bundle returns:
```
Message received:   Hello World!
```
7. Run `stop <Bundle ID> <Service Bundle ID> <Client Bundle ID>` to stop the bundles:
```
Unregistering service.
```


## Authors
- Ahmed Kallel
- Camelia Ben Laamari
<br />
<br />
© INDP3 SNI 2021-2022. SUP'COM.
