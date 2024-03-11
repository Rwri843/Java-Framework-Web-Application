package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        /*
        Product bicycle= new Product("bicycle",100.0,15);
        Product unicycle= new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */

        // Clearing repositories for multiple test runs
        // partRepository.deleteAll();
        // productRepository.deleteAll();
        // outsourcedPartRepository.deleteAll();



        // Toy Parts
        if()outsourcedPartRepository.count() == 0){
            OutsourcedPart wheels = new OutsourcedPart();
            wheels.setCompanyName("Hot Wheels");
            wheels.setName("RC Car Wheels");
            wheels.setPrice(30.00);
            wheels.setInv(50);

            OutsourcedPart legoBricks = new OutsourcedPart();
            legoBricks.setCompanyName("Legos");
            legoBricks.setName("Lego Bricks Pack");
            legoBricks.setPrice(20.00);
            legoBricks.setInv(40);

            OutsourcedPart dollFurniture = new OutsourcedPart();
            dollFurniture.setCompanyName("Barbie");
            dollFurniture.setName("Doll House Furniture");
            dollFurniture.setPrice(50.00);
            dollFurniture.setInv(25);

            OutsourcedPart actionFigureStand = new OutsourcedPart();
            actionFigureStand.setCompanyName("Marvel");
            actionFigureStand.setName("Action Figure Stand");
            actionFigureStand.setPrice(10.00);
            actionFigureStand.setInv(60);

            OutsourcedPart modelPaintBrush = new OutsourcedPart();
            modelPaintBrush.setCompanyName("Crayola");
            modelPaintBrush.setName("Model Paint Brushes");
            modelPaintBrush.setPrice(15.00);
            modelPaintBrush.setInv(30);

            outsourcedPartRepository.save(wheels);
            outsourcedPartRepository.save(legoBricks);
            outsourcedPartRepository.save(dollFurniture);
            outsourcedPartRepository.save(actionFigureStand);
            outsourcedPartRepository.save(modelPaintBrush); }

        if(productRepository.count() == 0){

        Product RcCar = new Product("Hot Wheels Rc Car", 50.00, 15);
        Product LegosSet = new Product("Lego Set", 40.00, 20);
        Product BarbieDollhouse = new Product("Barbie Dollhouse", 35.00, 19);
        Product ActionFigure = new Product("Marvel Action Figure Set", 25.00, 60);
        Product ModelPaintKit = new Product("Crayola Model Paint Kit", 32.00, 100);


        productRepository.save(RcCar);
        productRepository.save(LegosSet);
        productRepository.save(BarbieDollhouse);
        productRepository.save(ActionFigure);
        productRepository.save(ModelPaintKit);

    }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
