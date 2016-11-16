package launch;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import com.prowidesoftware.swift.guitools.servlet.ResourceServlet;

/**
 * Embedded Tomcat launcher
 */
public class Main {

    public static void main(String[] args) throws Exception {

        final String webappDirLocation = "src/main/webapp/";
        final Tomcat tomcat = new Tomcat();
        final String webPort = "8080";

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File(webappDirLocation).getAbsolutePath());

        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);
        
        /*
         * Programmatically add the resource servlet mapping.
         * This is analogous to defining the following in the application web.xml
         * 	<servlet>
         * 		<servlet-name>ResourceServlet</servlet-name>
         * 		<servlet-class>com.prowidesoftware.swift.guitools.servlet.ResourceServlet</servlet-class>
         * 	</servlet>
         * 	<servlet-mapping>
         * 		<servlet-name>ResourceServlet</servlet-name>
         * 		<url-pattern>/getResource</url-pattern>
         * 	</servlet-mapping> 
         */
        final String servletName = "ResourceServlet"; 
        Tomcat.addServlet(ctx, servletName, ResourceServlet.class.getName());
        ctx.addServletMapping( "/getResource", servletName);

        tomcat.start();
        tomcat.getServer().await();
    }
}
