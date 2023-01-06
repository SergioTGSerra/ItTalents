package pt.ipvc.ittalents.Backend;

import pt.ipvc.ittalents.Models.Offers;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Offer implements Serializable {
    private int id;
    private String text;
    private double price;
    private int requiredHours;
    private LocalDate dateAccepted;
    private boolean acepted = false;

    public Offer(String text, double price, int requiredHours) {
        this.id = ++Offers.index;;
        this.text = text;
        this.price = price;
        this.requiredHours = requiredHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAcepted() {
        return acepted;
    }

    public void setAcepted(boolean acepted) {
        this.acepted = acepted;
    }

    public LocalDate getDateAccepted() {
        return dateAccepted;
    }

    public void setDateAccepted(LocalDate dateAccepted) {
        this.dateAccepted = dateAccepted;
    }

    public int getRequiredHours() {
        return requiredHours;
    }
}
