package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;







// (imports omitted for brevity)

@Component
public class BootStrapData implements CommandLineRunner {
    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Toy Parts
        if (outsourcedPartRepository.count() == 0) {
            createAndSaveOutsourcedPart("Hot Wheels", "RC Car Wheels", 30.00, 50, 10);
            createAndSaveOutsourcedPart("Legos", "Lego Bricks Pack", 20.00, 40, 11);
            createAndSaveOutsourcedPart("Barbie", "Doll House Furniture", 50.00, 25, 12);
            createAndSaveOutsourcedPart("Marvel", "Action Figure Stand", 10.00, 60, 13);
            createAndSaveOutsourcedPart("Crayola", "Model Paint Brushes", 15.00, 30, 14);
        }
        if (productRepository.count() == 0) {
            createAndSaveProduct("Hot Wheels Rc Car", 50.00, 15);
            createAndSaveProduct("Lego Set", 40.00, 20);
            createAndSaveProduct("Barbie Dollhouse", 35.00, 19);
            createAndSaveProduct("Marvel Action Figure Set", 25.00, 60);
            createAndSaveProduct("Crayola Model Paint Kit", 32.00, 100);
        }
        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products" + productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts" + partRepository.count());
        System.out.println(partRepository.findAll());
    }

    private void createAndSaveOutsourcedPart(String companyName, String name, double price, int inv, int id) {
        OutsourcedPart part = new OutsourcedPart();
        part.setCompanyName(companyName);
        part.setName(name);
        part.setPrice(price);
        part.setInv(inv);
        part.setId(id);
        outsourcedPartRepository.save(part);
    }

    private void createAndSaveProduct(String name, double price, int inv) {
        Product product = new Product(name, price, inv);
        productRepository.save(product);
    }
}