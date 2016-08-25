package com.jrw;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Originally copied from Spring Guides (https://spring.io/guides/gs/rest-service/).

/*
 * With @RestController annotation, every method returns a domain object instead of a view.
 */
@RestController
public class GreetingController {

    private static final String template1 = "Hello, %s!";
    private static final String template2 = " Otherwise known as %s!";
    private final AtomicLong counter = new AtomicLong();

    /*
     * Using @RequestMapping annotation ensures that HTTP requests to /greeting are mapped to the greeting() method.
     * By default, @RequestMapping maps all HTTP operations. Add ...(method=GET), etc., to limit this.
     * @RequestParam binds the value of the query string parameter name into the name parameter of greeting().
     * No view technology (e.g., velocity, thymeleaf) is needed for a RESTful controller, as the data does not need
     * 	to be rendered to HTML. It does need to convert to JSON, which is enabled by having Jackson 2 on the classpath.
     * The name parameter has been given a default value of "World", but can be overridden through the URL query string.
     */
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name,
    		@RequestParam(value="userid", defaultValue="0000") String userid) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template1, name),String.format(template2, userid));
    }
}