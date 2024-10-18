package ePortfolio;

public class Stock {
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    private static final double COMMISSION = 9.99;

    // Constructor
    public Stock(String symbol, String name, int quantity, double price) {
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = quantity * price + COMMISSION; // Initialize the bookValue correctly
    }

    
    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getBookValue() {
        return bookValue;
    }

   //set values for the stock portfolio
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }

    // Buy method to increase the quantity and update bookValue
    public void buy(int newQuantity, double newPrice) {
        double newBookValue = newQuantity * newPrice + COMMISSION;
        this.bookValue += newBookValue;
        this.quantity += newQuantity;
        this.price = newPrice;  
    }

    // sell stock
    public double sell(int sellQuantity, double sellPrice) {
    	double payment = sellQuantity * sellPrice - COMMISSION;  
    	int remainingQuantity = this.quantity - sellQuantity;  

        // Update the book value proportionally based on the remaining shares
        this.bookValue = this.bookValue * ((double) remainingQuantity / this.quantity);
        this.quantity = remainingQuantity;  // Set the new quantity
       
        // Display remaining quantity and updated book value
        System.out.println("Remaining quantity of " + this.symbol + ": " + this.quantity);
        System.out.println("Updated book value of " + this.symbol + ": $" + String.format("%.2f", this.bookValue));

        return payment;
    }


    // Get the gain for the current stock investment
    public double getGain() {
        return (this.price * this.quantity) - this.bookValue;
    }

    }

