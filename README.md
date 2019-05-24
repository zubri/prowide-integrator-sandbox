## prowide-integrator-sandbox

Mock application to test or evaluate "Prowide Integrator" features, particularly convenient for the GUI Tools module.

**IMPORTANT**: This code is intended for Integrator customers or developers using an Integrator trial. Code in this repo will not compile without a running version of the Integrator library jars. In order to run the examples you should unpack your acquired jars in the lib dir and patch the jar version in build.gradle accordingly.

If you are interested on this software package you may request a trial download here: 
https://www.prowidesoftware.com/contact-us

### Compile & Run

**Requirements**
>Basic Java knowledge, including an installed version of Java 8+.

**Instructions**

>1. Copy pw-integrator-*.jar and pw-swift-guitools-*.jar files from your Prowide Integrator distribution/trial into prowide-integrator-sandbox/lib within your checkout
>2. Edit build.gradle and replace the pw-integrator and pw-swift-guitools dependencies with the files names you have just copied in 1.
>3. ./gradle bootRun
>4. Access the app at http://localhost:8080/


PD: to load this in Eclipse, run "./gradlew eclipse" first