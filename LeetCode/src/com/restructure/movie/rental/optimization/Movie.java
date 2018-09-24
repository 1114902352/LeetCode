package com.restructure.movie.rental.optimization;

public class Movie {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDRENS = 2;

    private String title;

    private Price price;

    public Movie(String title,int princeCode){
        this.title = title;
//        this.priceCode = princeCode;
    }

    public String getTitle() {
        return title;
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        switch (arg) {
            case Movie.REGULAR:
                price = new RegularPrice();
                break;
            case Movie.NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            case Movie.CHILDRENS:
                price = new ChildrensPrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    /**
     * 计算费用需要两项数据:租期长度和影片类型
     * 此处，没有选择将影片类型传给Rental对象，而是将租期长度传给Movie对象，是因为
     * 本系统可能发生的变化是加入新影片的类型，这种变化带有不稳定倾向。如果影片类型有所变化，
     * 我们希望尽量控制它造成的影响，所以选择在Movie对象内计算费用
     */
    public double getCharge(int daysRented){
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented){
        return price.getFrequentRenterPoints(daysRented);
    }
}
