package dtu.example;
import io.cucumber.java.en.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SimpleDTUPaySteps {
    private Customer customer;
    private Merchant merchant;
    private String customerId, merchantId;
    private SimpleDtuPayService dtupay = new SimpleDtuPayService();
    PayListEntry[] payList = {};
    UnknownPersonException exception;
    @Given("a customer with name {string}")
    public void aCustomerWithName(String name) {
        customer = new Customer(name);
    }
    @Given("the customer is registered with Simple DTU Pay")
    public void theCustomerIsRegisteredWithSimpleDTUPay() {
        customerId = dtupay.register(customer);
    }
    @Given("a merchant with name {string}")
    public void aMerchantWithName(String name) {
        merchant = new Merchant(name);
    }
    @Given("the merchant is registered with Simple DTU Pay")
    public void theMerchantIsRegisteredWithSimpleDTUPay() {
        merchantId = dtupay.register(merchant);
    }
    @When("the merchant initiates a payment for {int} kr by the customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(Integer amount) {
        try{
            dtupay.pay(amount,customerId,merchantId);
        }catch (UnknownPersonException e) {
            exception = e;
        }
    }
    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
        assertNull(exception);
    }

    @Given("a customer with name {string}, who is registered with Simple DTU Pay")
    public void aCustomerWithNameWhoIsRegisteredWithSimpleDTUPay(String name) {
        customer = new Customer(name);
        customerId = dtupay.register(customer);
    }
    @Given("a merchant with name {string}, who is registered with Simple DTU Pay")
    public void aMerchantWithNameWhoIsRegisteredWithSimpleDTUPay(String name) {
        merchant = new Merchant(name);
        merchantId = dtupay.register(merchant);
    }
    @Given("a successful payment of {int} kr from the customer to the merchant")
    public void aSuccessfulPaymentOfKrFromTheCustomerToTheMerchant(Integer amount) {
        try{
            dtupay.pay(amount,customerId,merchantId);
        }catch (UnknownPersonException e) {
            exception = e;
        }
        assertNull(exception);
    }
    @When("the manager asks for a list of payments")
    public void theManagerAsksForAListOfPayments() {
        payList = dtupay.getPayPosts();
    }
    @Then("the list contains a payments where customer {string} paid {int} kr to merchant {string}")
    public void theListContainsAPaymentsWhereCustomerPaidKrToMerchant(String customer, Integer amount, String merchant) {
        assertTrue(Arrays.asList(payList).contains(new PayListEntry(customer,amount,merchant)));
    }

    @When("the merchant initiates a payment for {int} kr using customer id {string}")
    public void theMerchantInitiatesAPaymentForKrUsingCustomerId(Integer amount, String customer) {
        try{
            dtupay.pay(amount,customer,merchantId);
        }catch (UnknownPersonException e) {
            exception = e;
        }
        ;
    }
    @Then("the payment is not successful")
    public void thePaymentIsNotSuccessful() {
        assertNotNull(exception);
    }
    @Then("an error message is returned saying {string}")
    public void anErrorMessageIsReturnedSaying(String string) {
        assertEquals(string,exception.getMessage());
    }



}