package com.adeo.devmeetup.scs.demo.reactive.client.houseprices;

public class HousePrice {

private String city;
    
    private Integer pricePerSquare;

    public HousePrice() {}
    
    public HousePrice(String city, Integer pricePerSquare) {
        this.city = city;
        this.pricePerSquare = pricePerSquare;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the pricePerSquare
     */
    public Integer getPricePerSquare() {
        return pricePerSquare;
    }

    /**
     * @param pricePerSquare the pricePerSquare to set
     */
    public void setPricePerSquare(Integer pricePerSquare) {
        this.pricePerSquare = pricePerSquare;
    }
    
}
