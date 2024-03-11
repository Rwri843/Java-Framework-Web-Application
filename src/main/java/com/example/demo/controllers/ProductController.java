package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.bootstrap.BootStrapData;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.repositories.ProductRepository;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.domain.Product;
import java.util.Optional; // import this

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/buyProduct")
    public String buyProduct(@RequestParam("productId") Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) { // Fix this line
            Product product = productOpt.get();

            // You need to define a getInv() method in your Product class
            if (product.getInv() > 0) { // Fix this line
                product.setInv(product.getInv() - 1);
                productRepository.save(product);
                return "redirect:/purchaseSuccess";
            }
        }

        return "redirect:/purchaseError";
    }
}