package org.acme;

public class MerchantService {
    private static MerchantService instance;
    public static synchronized MerchantService getInstance()
    {
        if (instance == null)
            instance = new MerchantService();
        return instance;
    }
    Merchant[] merchants = new Merchant[10];
    Integer count = 0;
    public String setMerchant(Merchant merchant){
        this.merchants[count] = merchant;
        this.count++;
        return Integer.toString(count-1);
    }

    public String getName(String merchantId){
       return merchants[Integer.parseInt(merchantId)].name();
    }
}
