/**
 * 
 */
package com.ranasoftcraft.org.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sandeep.rana
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/ng-ems/**")
          .addResourceLocations("classpath:/ui/ng-ems/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addRedirectViewController("/ng-ems", "/ng-ems/");
        registry.addViewController("/ng-ems/").setViewName("forward:/ng-ems/index.html");
    }
}
