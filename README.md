# OSGi

## Requirements
1. Apache Karaf :
    - Download the latest version of Karaf from https://karaf.apache.org/download
    - Extract all the files
    - Create a new environment variable `KARAF_HOME` that refers to the download folder path
2. Apache Maven :
    - Download the latest version of Maven from https://maven.apache.org/download.cgi
    - Extract all the files
    - Add the `/bin` folder path of Maven to the `PATH` environment variable


## Steps
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
Hello John
```
6. Run `stop <Bundle ID> <Service Bundle ID> <Client Bundle ID>` to stop the bundles:
```
Unregistering service.
```


## Authors
- Ahmed Kallel
- Camelia Ben Laamari
<br />
<br />
© INDP3 SNI 2021-2022. SUP'COM.
