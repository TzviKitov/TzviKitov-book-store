package com.example.ex4template.listeners;



import com.example.ex4template.beans.ShoppingBasket;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * a @WebListener class for session count
 * the @Component is needed only if we INJECT beans
 */
@Component
@WebListener
public class SessionListenerCounter implements HttpSessionListener {

    private final AtomicInteger activeSessions;

    @Resource(name = "ShoppingBasket")
    private ShoppingBasket shoppingBasket;


    public SessionListenerCounter() {
        super();
        activeSessions = new AtomicInteger();
    }

    public int getTotalActiveSession() {
        return activeSessions.get();
    }


    public void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
        System.out.println("******************************SessionListenerCounter +++ Total active session are " + activeSessions.get());

        // example of application bean accessed from session listener


    }
@Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
        System.out.println("*********************8SessionListenerCounter --- Total active session are " + activeSessions.get());
    }
}
