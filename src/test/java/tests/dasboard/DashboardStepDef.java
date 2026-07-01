package tests.dasboard;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.ProductsPage;
import tests.base.BaseTest;
import tests.reuse.SwagLabsObjects;

public class DashboardStepDef extends BaseTest {

    @When("choosing product")
    public void choosingProduct(){
        SwagLabsObjects.products.clickOnProductName();
    }

}
