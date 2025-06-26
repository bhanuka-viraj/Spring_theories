package org.bhanuka;

import org.bhanuka.config.WebAppConfig;
import org.bhanuka.config.WebRootConfig;
import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?> @Nullable [] getRootConfigClasses() {
        return new Class[]{WebRootConfig.class};
    }

    @Override
    protected Class<?> @Nullable [] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    // install tomcat,
    // in intellij > go to project structure > create web facet for the module > create artifact and add the libraries
    // add the configuration tomcat local

    //** add servlet api dependency
}