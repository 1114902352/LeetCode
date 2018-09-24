package com.restructure.movie.rental.optimization;

import java.util.Iterator;
import java.util.Vector;

public class Customer {
    private String name;
    private Vector<Rental> rentals = new Vector<>();

    public Customer(String name){
        this.name = name;
    }

    public void addRental(Rental arg){
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement(){
        int frequentRenterPoints = 0;
        Iterator<Rental> it = rentals.iterator();
        String result = "Rental Record for " + getName() + "\n";
        while(it.hasNext()) {
            Rental each = it.next();
            frequentRenterPoints += each.getFrequentRenterPoints();
            // show figures for this rental
            result += "\t"+each.getMovie().getTitle()+"\t"+each.getCharge()+"\n";
        }
        // add footer lines
        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() + "frequent renter points";
        return result;
    }

    private double getTotalCharge(){
        double result = 0;
        Iterator<Rental> it = rentals.iterator();
        while(it.hasNext()){
            Rental each = it.next();
            result += each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints(){
        int result = 0;
        Iterator<Rental> it = rentals.iterator();
        while(it.hasNext()){
            Rental each = it.next();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }
}
