package com.example.ex4template;

import com.example.ex4template.beans.Label;
import com.example.ex4template.beans.Label;
import com.example.ex4template.beans.ShoppingBasket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * create some beans witn various scopes using QUALIFIERS (method names)
 */
@Configuration
public class BeanConfiguration {

    /* singleton scope */
    @Bean
    @Scope("singleton")
    public Label autowiredFieldSingletonScope() {
        Label l =  new Label();
        l.setLabel("I'm SINGLETON Label bean");
        return l;
    }

    /* application scope */
//    @Bean
//    @ApplicationScope
//    public Label autowiredFieldApplicationScope() {
//        Label l =  new Label();
//        l.setLabel("I'm APPLICATION Label bean");
//        return l;
//    }

//    /* request scope */
//    @Bean
//    @RequestScope
//    public Label autowiredFieldRequestScope() {
//        Label l =  new Label();
//        l.setLabel("I'm REQUEST Label bean");
//        return l;
//    }
//
    /* session scope
    request.getSession().addAttribute ("sessionScopeBeanExample",new Label())
    */

    //@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

    @Bean
    @SessionScope
    public ShoppingBasket ShoppingBasket () {
        return new ShoppingBasket();
    }

}
