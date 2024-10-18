package ePortfolio;

public class MutualFund {
	    private String symbol;
	    private String name;
	    private int quantity;
	    private double price;
	    private double bookValue;
	    private static final double COMMISSION = 45.00;
	 
	    // Constructor
	    public MutualFund(String symbol, String name, int quantity, double price) {
	        this.symbol = symbol;
	        this.name = name;
	        this.quantity = quantity;
	        this.price = price;
	        this.bookValue = quantity * price;
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
	  //set values for the mutualfund Portfolio
	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    public double setPrice(double price) {
	        return this.price = price;
	    }

	    public void setBookValue(double bookValue) {
	        this.bookValue = bookValue;
	    }

	    public void buy(int newQuantity, double newPrice) {
	        double newBookValue = newQuantity * newPrice;
	        this.bookValue += newBookValue;
	        this.quantity += newQuantity;
	        this.price = newPrice;
	    }

	    public double sell(int sellQuantity, double sellPrice) {
	        double payment = sellQuantity * sellPrice - COMMISSION;  // Calculate the payment after the redemption fee
	        int remainingQuantity = this.quantity - sellQuantity;  // Calculate remaining units

	        // Update the book value proportionally based on the remaining units
	        this.bookValue = this.bookValue * ((double) remainingQuantity / this.quantity);
	        this.quantity = remainingQuantity;  // Set the new quantity
	        
	        // Display remaining quantity and updated book value
	        System.out.println("Remaining quantity of " + this.symbol + ": " + this.quantity);
	        System.out.println("Updated book value of " + this.symbol + ": $" + String.format("%.2f", this.bookValue));

	        return payment;
	    }
	 // Get the gain for the current mutualfund investments
	    public double getGain() {
	        return (this.price * this.quantity) - this.bookValue;
	    }

	    }
	


