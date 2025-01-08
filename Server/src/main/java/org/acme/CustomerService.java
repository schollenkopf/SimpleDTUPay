package org.acme;

public class CustomerService {
    private static CustomerService instance;
    public static synchronized CustomerService getInstance()
    {
        if (instance == null)
            instance = new CustomerService();
        return instance;
    }
    Customer[] customers = new Customer[10];
    Integer count = 0;
    public String setCustomer(Customer customer){
        this.customers[count] = customer;
        this.count++;
        return Integer.toString(count-1);
    }

    public String getName(String customerId){
        return customers[Integer.parseInt(customerId)].name();
    }
}
