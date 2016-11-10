prowide-integrator-sandbox
============================

Mock application to test or evaluate "Prowide Integrator" features, particularly convenient for the GUI Tools module.

IMPORTANT: This code is intended for Integrator customers or developers using an Integrator trial. Code in this repo will not compile without a running version of the Integrator library jars. In order to run the examples you should unpack your acquired jars in the lib dir and run ant here.

If you are interested on this software package you may request a trial download here: 
http://www.prowidesoftware.com/contact.jsp


1. Edit pom.xml and replace the local dependencies with jar names and location for Prowide Integrator libraries
2. mvn clean
3. mvn install
4. sh target/bin/webapp (Mac and Linux) C:/> target/bin/webapp.bat (Windows)
5. Access the webapp at http://localhost:8080/