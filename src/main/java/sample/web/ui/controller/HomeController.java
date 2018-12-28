package sample.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    private static final String HOME = "home";
    private static final String LOGIN = "login";
    private static final String HELLO = "hello";
    private static final String FORBIDDEN = "forbidden";


    @GetMapping(path = {"/", "/home"})
    public ModelAndView home() {
        return new ModelAndView(HOME);
    }

    @GetMapping(path = "/login")
    public ModelAndView login() {
        return new ModelAndView(LOGIN);
    }

    @GetMapping(path = "/hello")
    public ModelAndView hello() {
        return new ModelAndView(HELLO);
    }

    @GetMapping(path = "/403")
    public ModelAndView forbidden() {
        return new ModelAndView(FORBIDDEN);
    }
}
