package gr.di.uoa.chicagoincidents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/service_requests")
@CrossOrigin(origins = "*")
public class ServiceRequestController {

    @RequestMapping(value = { "/home" })
    public ModelAndView lista(HttpServletRequest request) {
        String test = "Alohaaaa my friends";
        ModelAndView modelAndView = new ModelAndView("home");

        modelAndView.addObject("test", test);
        return modelAndView;
    }

    @RequestMapping(value = { "/search" })
    public ModelAndView lista2(HttpServletRequest request) {
        String test = "Aloheirosss my friends";
        ModelAndView modelAndView = new ModelAndView("search");

        modelAndView.addObject("test", test);
        return modelAndView;
    }

}
