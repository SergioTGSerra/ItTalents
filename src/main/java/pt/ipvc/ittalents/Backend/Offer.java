package pt.ipvc.ittalents.Backend;

public class Offer {
    private int id;
    private String text;
    private double price;
    private boolean acepted = false;

    public Offer(int id, String text, double price) {
        this.id = id;
        this.text = text;
        this.price = price;
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
}
