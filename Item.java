public class Item {
    String name;
    int quantity;
    double price;
    String manufactureDate;
    String expiryDate;

    public Item(String name, int quantity, double price, String manufactureDate, String expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return name + " (₹" + price + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item)) return false;
        Item other = (Item) obj;
        return this.name.equals(other.name) && this.price == other.price;
    }
}
