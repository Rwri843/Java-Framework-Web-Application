Includes notes describing where in the code to find the changes you made for each of parts C to J. 
 
Each note should include the prompt, file name, line number, and change.

<h1>D287 – JAVA FRAMEWORKS</h1>

C:  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.

CSS code added to mainscreen.html and Title and header changed:
```

<title>Rob's Toy Store</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }
        .container {
            text-align: center;
            padding: 20px;
            background-color: deepskyblue;
        }
        h1 {
            color: firebrick;
            font-size: 40px;

        }
        hr{
            border: 0;
            height: 2px;
            background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(68,68,68,0.75), rgba(0,0,0,0));
            margin-top: 10px; 
```
D:  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.

--About.html created Lines 1-97, AboutController.java created, and  
About link/button added to mainscreen.html:
```
<a th:href="@{/about}" class="btn btn-primary btn-sm mb-3">About Us</a>
```
AboutController.java created: 
```
@Controller
@RequestMapping("/about")
public class AboutController {

    //creates endpoint to display About page
    @GetMapping
    public String showAbout() {
        return "about";
    }
}
```
E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.

Five Products & Parts added to BootStrapData.java file:
```
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
```

