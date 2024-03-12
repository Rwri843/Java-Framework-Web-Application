package com.example.demo.controllers;
import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/buyProduct")
    public String buyProduct(@RequestParam("productId") Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            if (product.getInv() > 0) {
                product.setInv(product.getInv() - 1);
                productRepository.save(product);
                return "redirect:/purchaseSuccess";
            }
        }

        return "redirect:/purchaseError";
    }



    @GetMapping("/purchaseSuccess")
    public String showPurchaseSuccess() {
        // Return the view for a successful purchase message
        return "purchaseSuccess"; // Assumes you have a purchaseSuccess.html template
    }

    @GetMapping("/purchaseError")
    public String showPurchaseError() {
        // Return the view for an error message on purchase
        return "purchaseError"; // Assumes you have a purchaseError.html template
    }

    // Add any other mappings for your controller here
}
