package sg.edu.nus.iss.workshop24.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.workshop24.model.PO;
import sg.edu.nus.iss.workshop24.model.Product;
import sg.edu.nus.iss.workshop24.service.PODetailsService;
import sg.edu.nus.iss.workshop24.service.POService;

@Controller
public class POController {

    @Autowired
    PODetailsService poDetailsService;

    @Autowired
    POService poService;

    @PostMapping(path = "/order", consumes = "application/x-www-form-urlencoded")
    public String addToCart(Model model, HttpSession session, @ModelAttribute Product product) {

        List<Product> items = (List<Product>) session.getAttribute("items");

        if (items == null) {
            items = new ArrayList<>();
            session.setAttribute("items", items);
        }
        // items.add(product);
        items = poDetailsService.addToList(items, product);

        model.addAttribute("items", items);
        model.addAttribute("products", poDetailsService.getAllProducts());
        model.addAttribute("product", product);
        return "cart";
    }

    @PostMapping(path = "/checkout")
    public String checkout(Model model, HttpSession session, HttpServletRequest request) {

        List<Product> items = (List<Product>) session.getAttribute("items");
        PO po = PO.createFromRequest(request);
        int primaryKey = poService.insertIntoPurchaseOrder(po);
        poDetailsService.insertPurchaseOrderDetails(items, primaryKey);

        model.addAttribute("total", items.size());
        model.addAttribute("orderId", primaryKey);
        model.addAttribute("items", items);
        return "checkout";
    }
}
