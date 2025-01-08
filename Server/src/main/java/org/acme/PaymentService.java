package org.acme;

public class PaymentService {
    CustomerService customerService = CustomerService.getInstance();
    MerchantService merchantService = MerchantService.getInstance();
    PayListEntry[] payments = new PayListEntry[10];
    Integer count = 0;
    private static PaymentService instance;
    public static synchronized PaymentService getInstance()
    {
        if (instance == null)
            instance = new PaymentService();
        return instance;
    }
    public Boolean pay(PayPost paypost) throws UnknownPersonException {
        String customer;
        String merchant;
        try{
            customer = customerService.getName(paypost.customerId());

        } catch (Exception e) {
            throw new UnknownPersonException("customer with id "+paypost.customerId()+" is unknown");
        }
        try{
            merchant = merchantService.getName(paypost.merchantId());
        } catch (Exception e) {
            throw new UnknownPersonException("merchant with id "+paypost.customerId()+" is unknown");
        }


        payments[count] = new PayListEntry(customer, paypost.amount(), merchant);
        count++;
        return true;
    }

    public PayListEntry[] getPayments() {
        return payments;
    }
}
