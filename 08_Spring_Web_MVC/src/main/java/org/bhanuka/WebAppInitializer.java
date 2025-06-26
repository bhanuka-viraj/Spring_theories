package org.bhanuka;

import org.bhanuka.config.WebAppConfig;
import org.bhanuka.config.WebRootConfig;
import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //adding the webRootConfig to the root context
    @Override
    protected Class<?> @Nullable [] getRootConfigClasses() {
        return new Class[]{WebRootConfig.class};
    }

    // adding the webAppConfig to the servlet context
    @Override
    protected Class<?> @Nullable [] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    // 1. install tomcat,
    // 2. in intellij > go to project structure > create web facet for the module > create artifact and add the libraries
    // 3. add the configuration tomcat local

    //** add servlet api dependency



    /*
           ---------- contexts -----------
    * servlet web app context  = controllers , View controllers, Handler Mappings (also known as child context)
    * root web context = services and repositories (also known as parent context)
    */


    /*
            ---------- DispatcherServlet -----------

        DispatcherServlet acts as a front controller, handling all incoming HTTP requests and routing them to
        the appropriate Spring MVC controllers. It's the central component for managing the request-response
        lifecycle, essentially directing traffic within the application
     */

}