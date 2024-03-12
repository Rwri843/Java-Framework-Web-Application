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

F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet *each* of the following parameters:
   - The “Buy Now” button must be next to the buttons that update and delete products.
   - Display a message that indicates the success or failure of a purchase. 
   - The button should decrement the inventory of that product by **one**. It should not affect the inventory of any of the associated parts.

Updated Table and added Buy option:
```
<tr th:each="tempProduct : ${products}">
            <td th:text="${tempProduct.name}">Product Name</td>
            <td th:text="${tempProduct.price}">Product Price</td>
            <td th:text="${tempProduct.inv}">Inventory Count</td>
            <td>
                <form th:action="@{/buyProduct}" method="post" onsubmit="return confirm('By choosing Buy Now, you will purchase the item. Are you sure of your purchase?')">
                    <input type="hidden" name="productId" th:value="${tempProduct.id}" />
                    <button type="submit" class="btn btn-primary btn-sm mb-3">Buy Now</button>
                </form>
                <a th:href="@{/showProductFormForUpdate(productID=${tempProduct.id})}" class="btn btn-primary btn-sm mb-3">Update</a>
                <a th:href="@{/deleteproduct(productID=${tempProduct.id})}" class="btn btn-primary btn-sm mb-3"
                   onclick="if(!(confirm('Are you sure you want to delete this product?'))) return false;">Delete</a>
            </td>
        </tr>
```
ProductController.java file added: 

public class ProductController {
```
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
```

purchaseSuccess.html added: 
```
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Purchase Approved!</title>
</head>
<body>
<h1>Your purchase was successful!</h1>
<p>
  <a href="http://localhost:8080/">Return to Main Screen</a>
</p>
</body>
```
purchaseError.html added: 

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Purchase Denied</title>
</head>
<body>
<h1>Your purchase was not approved.</h1>
<p>
    <a href="http://localhost:8080/">Return to Main Screen</a>
</p>
</body>
</html>
```
- [ ]  G.  Modify the parts to track maximum and minimum inventory by doing the following:
- Add additional fields to the part entity for maximum and minimum inventory:
```
// Line 27-34
@Min(value = 0, message = "Price value must be positive")
    double price;
    @Min(value = 0, message = "Inventory value must be positive")
    int inv;
    @Column(name = "MAX_INV")
    int max_inv;
    @Column(name = "MIN_INV")
    int min_inv;

// Line 104 - 114
public void setMinInv(int minInv) {
        this.minInv = minInv;
    }

    public int getMaxInv() {
        return maxInv;
    }

    public void setMaxInv(int maxInv) {
        this.maxInv = maxInv;
    }
 ```   

-Modify the sample inventory to include the maximum and minimum fields.
 ```   
 //minInv and maxInv added to sample parts list also
 // Line 66-67
 part.setMinInv(minInv);
 part.setMaxInv(maxInv);
  ```
- Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
  ```
  // IhousePartForm.html
  <!-- Minimum inventory input -->
    <p><input type="text" th:field="*{minInv}" placeholder="Minimum Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('minInv')}" th:errors="*{minInv}">Minimum Inventory Error</p>

    <!-- Maximum inventory input -->
    <p><input type="text" th:field="*{maxInv}" placeholder="Maximum Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('maxInv')}" th:errors="*{maxInv}">Maximum Inventory Error</p>

  // OutsourcePartForm.html
  <!-- Minimum inventory input -->
    <p><input type="text" th:field="*{minInv}" placeholder="Minimum Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('minInv')}" th:errors="*{minInv}">Minimum Inventory Error</p>

    <!-- Maximum inventory input -->
    <p><input type="text" th:field="*{maxInv}" placeholder="Maximum Inventory" class="form-control mb-4 col-4"/></p>
    <p th:if="${#fields.hasErrors('maxInv')}" th:errors="*{maxInv}">Maximum Inventory Error</p>
  ```  

-Rename the file the persistent storage is saved to.
  ```  
  // In application.properties, changed
  // From:
  spring.datasource.url=jdbc:h2:file:~/spring-boot-h2-db102
  // To:
  spring.datasource.url=jdbc:h2:file:~/h2-db-development:
  ```  
-Modify the code to enforce that the inventory is between or at the minimum and maximum value.
 
In Part.java isInvValid that checks if a given inventory value falls between minInv and maxInv
```  
public boolean isInvValid(int inventory) {
return inventory >= minInv && inventory <= maxInv;
}
  ```  

- [ ]  H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:

-Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
 
- Inventory check for Min and Max added to AddinhousePartController:
```  
// Check if the inventory falls below the minimum
            if (part.getInv() < part.getMinInv()) {
                theModel.addAttribute("error", "Inventory cannot fall below the minimum required.");
                return "InhousePartForm";
            }

            // Check if the inventory goes above the maximum
            if (part.getInv() > part.getMaxInv()) {
                theModel.addAttribute("error", "Inventory cannot exceed the maximum allowed.");
                return "InhousePartForm";
            }
```
-Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.

Updated AddProductController to reflect lowering of products: 
```
@PostMapping("/showFormAddProduct")
    public String submitForm(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model theModel) {
        theModel.addAttribute("product", product);

        if(bindingResult.hasErrors()){
           
        } else {
            ProductService repo = context.getBean(ProductServiceImpl.class);
            PartService partService1 = context.getBean(PartServiceImpl.class);


            boolean inventoryIsOk = true;
            if(product.getId() != 0) {
                Product existingProduct = repo.findById((int) product.getId());
                int inventoryDifference = product.getInv() - existingProduct.getInv();
                if(inventoryDifference > 0) {
                    for (Part p : existingProduct.getParts()) {
                        if ((p.getInv() - inventoryDifference) < p.getMinInv()) { // Assuming getMinInv() exists
                            inventoryIsOk = false;
                            break;
                        }
                    }
                }
            }
```

-Display error messages when adding and updating parts if the inventory is greater than the maximum.

Inventory check for Min and Max added to AddOutsourcePartController:
```
// Check if inventory is below minimum
if (part.getInv() < part.getMinInv()) {
theModel.addAttribute("error", "Inventory cannot be less than the minimum required.");
return "OutsourcedPartForm";
}

            // Check if inventory exceeds maximum
            if (part.getInv() > part.getMaxInv()) {
                theModel.addAttribute("error", "Inventory cannot exceed the maximum allowed.");
                return "OutsourcedPartForm";
            }
```

I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package


Two test to test the max and min inventory match the expected value. 
```
// The max value reflects the maximum number of items allowed in the inventory for a specific part, which helps in maintaining adequate stock levels without exceeding storage capacity.
    @Test
    void getMaxInv() {
        int maxIn = 100;
        int maxOut = 150;
        partIn.setMaxInv(maxIn);
        partOut.setMaxInv(maxOut);
        assertEquals(maxIn, partIn.getMaxInv(), "The max for InhousePart did not match the expected value.");
        assertEquals(maxOut, partOut.getMaxInv(), "The max for OutsourcedPart did not match the expected value.");
    }
// The min value represents the minimum number of items to be kept in inventory, ensuring a buffer for continuous operation without stockouts.
    @Test
    void setMaxInv() {
        int maxIn = 100;
        int maxOut = 150;
        partIn.setMaxInv(maxIn);
        partOut.setMaxInv(maxOut);
        assertEquals(maxIn, partIn.getMaxInv(), "Failed to set max on InhousePart.");
        assertEquals(maxOut, partOut.getMaxInv(), "Failed to set max on OutsourcedPart.");
    }

    @Test
    void getMinInv() {
        int minIn = 1;
        int minOut = 1;
        partIn.setMinInv(minIn);
        partOut.setMinInv(minOut);
        assertEquals(minIn, partIn.getMinInv(), "The min for InhousePart did not match the expected value.");
        assertEquals(minOut, partOut.getMinInv(), "The min for OutsourcedPart did not match the expected value.");
    }

    @Test
    void setMinInv() {
        int minIn = 1;
        int minOut = 1;
        partIn.setMinInv(minIn);
        partOut.setMinInv(minOut);
        assertEquals(minIn, partIn.getMinInv(), "Failed to set min on InhousePart.");
        assertEquals(minOut, partOut.getMinInv(), "Failed to set min on OutsourcedPart.");
    }
```

J.  Remove the class files for *any* unused validators in order to clean your code.


```
// Deleted from ValidDeletPart.java
 String message() default "Part cannot be deleted if used in a product.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}

// Deleted from DeletePartValidator.java
public class DeletePartValidator implements ConstraintValidator<ValidDeletePart, Part> {
    @Override
    public void initialize(ValidDeletePart constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext constraintValidatorContext) {
        if(part.getProducts().isEmpty()) return true;
        else return false;
    }
}

// 

```