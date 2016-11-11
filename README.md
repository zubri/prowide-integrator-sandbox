prowide-integrator-sandbox
============================

Mock application to test or evaluate "Prowide Integrator" features, particularly convenient for the GUI Tools module.

**IMPORTANT**: This code is intended for Integrator customers or developers using an Integrator trial. Code in this repo will not compile without a running version of the Integrator library jars. In order to run the examples you should unpack your acquired jars in the lib dir and run ant here.

If you are interested on this software package you may request a trial download here: 
http://www.prowidesoftware.com/contact.jsp

----

**Requirements**

>Basic Java knowledge, including an installed version of the JVM and Maven.

**Instructions**

>1. Copy pw-integrator-*.jar and pw-xsd-gui-*.jar files from your Prowide integrator distribution/trial into prowide-integrator-sandbox/src/main/webapp/WEB-INF/lib within your checkout
>2. Edit pom.xml and replace the filenames indicated in the pw-integrator and pw-xsd-gui install section to match the filenames you have just copied in 1.
>3. mvn clean
>4. mvn install
>5. mvn package
>6. sh target/bin/webapp (Mac and Linux) C:/> target/bin/webapp.bat (Windows)
>7. Access the webapp at http://localhost:8080/ and follow screen instructions to use the demo app
