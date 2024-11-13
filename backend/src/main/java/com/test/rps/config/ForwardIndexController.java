package com.test.rps.config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Simple controller to redirect any path to the index.html page for angular routing to work.
 */
@Controller
public class ForwardIndexController {

    @RequestMapping(value = "{path:[^\\.]*}")
    public String redirect() {
        // Forward to the index page
        return "forward:/index.html";
    }
}