package sg.edu.nus.iss.workshop24.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.workshop24.model.Product;
import sg.edu.nus.iss.workshop24.service.PODetailsService;

@Controller
@RequestMapping(path = "/")
public class FrontController {

    @Autowired
    PODetailsService poDetailsService;

    @GetMapping
    public String getHome(HttpSession session, Model model, @ModelAttribute Product product) {
        session.invalidate();
        model.addAttribute("products", poDetailsService.getAllProducts());
        model.addAttribute("product", product);
        return "index";
    }
}
