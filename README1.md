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
AboutController created: 
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

