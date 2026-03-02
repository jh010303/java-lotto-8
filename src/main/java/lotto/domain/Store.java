package lotto.domain;

public class Store {
    private long profit;
    private double rate;

    private static Store instance;

    private Store() {

    }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }


    public void setProfit(long profit, int lottoPurchasePrice) {
        this.profit = profit;
        setRate(lottoPurchasePrice);
    }

    public double getRate() {
        return rate*100;
    }

    private void setRate(int lottoPurchasePrice) {
        this.rate = (double) profit / lottoPurchasePrice;
    }
}