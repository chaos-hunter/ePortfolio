package ePortfolio;
import java.util.ArrayList;
import java.util.Scanner;

public class Portfolio {
	
	Scanner scn = new Scanner(System.in);   
	private ArrayList<Stock> stocks = new ArrayList<>();//where the stocks investments are stored
	private ArrayList<MutualFund> mutualFunds = new ArrayList<>();//where the mutualfund investments are stored

	    public void buyStock(String symbol, String name, int quantity, double price) {
	    	// To track if stock already exists in the portfolio
	    	boolean stockFound = false;  

	        // Search for the stock in the portfolio
	        for (Stock stock : stocks) {
	            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
	                stockFound = true;
	             // Add additional shares to the existing stock
	                stock.buy(quantity, price);  
	                System.out.println("Additional " + quantity + " shares of " + symbol + " purchased. Total shares: " + stock.getQuantity() + ".");
	                System.out.println("Updated book value: $" + String.format("%.2f", stock.getBookValue()));
	                return;
	            }
	        }

	        // If stock was not found, add it as a new stock
	        if (!stockFound) {
	            Stock newStock = new Stock(symbol, name, quantity, price);
	            stocks.add(newStock);
	            System.out.println("Stock " + symbol + " added to portfolio with " + quantity + " shares at $" + String.format("%.2f", price) + ".");
	        }
	    }
	    //same condition as buystock
	    public void buyMutualFund(String symbol, String name, int quantity, double price) {
	    	boolean fundFound = false;  
	        for (MutualFund fund : mutualFunds) {
	            if (fund.getSymbol().equalsIgnoreCase(symbol)) {
	                fundFound = true;
	                fund.buy(quantity, price);  
	                System.out.println("Additional " + quantity + " units of " + symbol + " purchased. Total units: " + fund.getQuantity() + ".");
	                System.out.println("Updated book value: $" + String.format("%.2f", fund.getBookValue()));
	                return;
	            }
	        }

	        // If mutual fund was not found, add it as a new mutual fund
	        if (!fundFound) {
	            MutualFund newFund = new MutualFund(symbol, name, quantity, price);
	            mutualFunds.add(newFund);
	           	System.out.println("Mutual fund " + symbol + " added to portfolio with " + quantity + " units at $" + String.format("%.2f", price) + ".");
	        }
	    }
    
	    
	    public void sellStock(String symbol, int quantity, double price) {
	    	boolean stockFound = false;  

	        // Search for the stock in the portfolio
	        for (Stock stock : stocks) {
	            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
	                stockFound = true;
	             // Condition to check if enough shares are available
	                if (stock.getQuantity() >= quantity) {  
	                	// Sell units and get payment
	                	double payment = stock.sell(quantity, price); 
	                    System.out.println("Payment received for selling " + quantity + " shares of " + symbol + ": $" + String.format("%.2f", payment));

	                    // Condition to remove stock from portfolio if all shares are sold
	                    if (stock.getQuantity() == 0) {
	                        stocks.remove(stock);
	                        System.out.println("All shares of " + symbol + " have been sold. Stock removed from portfolio.");
	                    }
	                } else {
	                    System.out.println("Not enough shares to sell. Available: " + stock.getQuantity());
	                }
	                return;
	            }
	        }

	        // If stock with the given symbol was not found
	        if (!stockFound) {
	            System.out.println("Stock with symbol " + symbol + " not found.");
	        }
	    }


	    //same conditions for sellstocks
	    public void sellMutualFund(String symbol, int quantity, double price) {
	        boolean fundFound = false;  
	        for (MutualFund fund : mutualFunds) {
	            if (fund.getSymbol().equalsIgnoreCase(symbol)) {
	                fundFound = true;
	                if (fund.getQuantity() >= quantity) {  
	                    double payment = fund.sell(quantity, price);  
	                    System.out.println("Payment received for selling " + quantity + " units of " + symbol + ": $" + String.format("%.2f", payment));
	                    if (fund.getQuantity() == 0) {
	                        mutualFunds.remove(fund);
	                        System.out.println("All units of " + symbol + " have been sold. Mutual fund removed from portfolio.");
	                    }
	                } else {
	                    System.out.println("Not enough units to sell. Available: " + fund.getQuantity());
	                }
	                return;
	            }
	        }

	        // If mutual fund with the given symbol was not found
	        if (!fundFound) {
	            System.out.println("Mutual fund with symbol " + symbol + " not found.");
	        }
	    }
	    //update prices for stocks or mutualfunds
	    public void updatePrices(Scanner scn) {    	    
	    	    String updateType = "";

	    	    // Ask the user to choose between updating a stock or mutual fund
	    	    while (true) {
	    	        System.out.println("Do you want to update a stock or a mutual fund:");
	    	        updateType = scn.nextLine();
	    	        if (updateType.equalsIgnoreCase("stock") || updateType.equalsIgnoreCase("mutualfund")) {
	    	            break; 
	    	        } else {
	    	            System.out.println("Invalid input. Please enter 'stock' or 'mutualfund'.");
	    	        }
	    	    }

	    	    // Ask for the symbol of the investment to update
	    	    System.out.println("Enter the symbol of the " + updateType + " you want to update:");
	    	    String symbol = scn.nextLine();
	    	    //update price for stocks
	    	    if (updateType.equalsIgnoreCase("stock")) {
	    	        boolean found = false;
	    	        for (Stock stock : stocks) {
	    	            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
	    	                found = true;
	    	                System.out.println("Enter the new price for " + stock.getSymbol() + " (" + stock.getName() + "): ");
	    	                double newPrice = scn.nextDouble();
	    	                stock.setPrice(newPrice);  
	    	                System.out.println("Price of stock " + stock.getSymbol() + " updated to $" + String.format("%.2f", newPrice));
	    	                break;
	    	            }
	    	        }
	    	        if (!found) {
	    	            System.out.println("Stock with symbol " + symbol + " not found.");
	    	        }
	    	    //update price for mutualfunds
	    	    } else if (updateType.equalsIgnoreCase("mutualfund")) {
	    	        boolean found = false;
	    	        for (MutualFund fund : mutualFunds) {
	    	            if (fund.getSymbol().equalsIgnoreCase(symbol)) {
	    	                found = true;
	    	                System.out.println("Enter the new price for " + fund.getSymbol() + " (" + fund.getName() + "): ");
	    	                double newPrice = scn.nextDouble();
	    	                fund.setPrice(newPrice);  
	    	                System.out.println("Price of mutual fund " + fund.getSymbol() + " updated to $" + String.format("%.2f", newPrice));
	    	                break;
	    	            }
	    	        }
	    	        if (!found) {
	    	            System.out.println("Mutual fund with symbol " + symbol + " not found.");
	    	        }
	    	    }
	    	}


	    //calculate total Gain in the Portfolio from all investments
	    public double getGain() {
	        //Initialized to zero
	    	double totalGain = 0.0;

	        // Calculate gain for all stocks
	        for (Stock stock : stocks) {
	            double stockGain = stock.getGain();
	            totalGain += stockGain;
	            System.out.println("Gain for stock " + stock.getSymbol() + ": $" + String.format("%.2f", stockGain));
	        }

	        // Calculate gain for all mutual funds
	        for (MutualFund fund : mutualFunds) {
	            double fundGain = fund.getGain();
	            totalGain += fundGain;
	            System.out.println("Gain for mutual fund " + fund.getSymbol() + ": $" + String.format("%.2f", fundGain));
	        }

	        System.out.println("Total gain for the portfolio: $" + String.format("%.2f", totalGain));
	        return totalGain;
	    }



	    //search function for all the investments based on price, symbol and keywords
	    public void search(Scanner scn) {
	        // Get search criteria
	        System.out.println("Enter symbol (leave empty to match any): ");
	        String symbol = scn.nextLine().trim();
	        System.out.println("Enter keywords (separated by spaces, leave empty to match any): ");
	        String keywords = scn.nextLine().trim();
	        System.out.println("Enter price range (e.g., '10-100' , '-100', or leave empty to match any): ");
	        String priceRange = scn.nextLine().trim();
	        // Parse the price range input
	        Double lowerPrice = null;
	        Double upperPrice = null;
	        
	        //conditon if it is in a range
	        if (!priceRange.isEmpty()) {
	            String[] rangeParts = priceRange.split("-");
	            if (rangeParts.length == 2) {
	                if (!rangeParts[0].isEmpty()) lowerPrice = Double.parseDouble(rangeParts[0]);
	                if (!rangeParts[1].isEmpty()) upperPrice = Double.parseDouble(rangeParts[1]);
	            }
	            //condtion for if the price is exact 
	            else if (rangeParts.length == 1) {
	                lowerPrice = Double.parseDouble(rangeParts[0]);
	                upperPrice = lowerPrice;  
	            }
	        }

	        // Split keywords into a list
	        String[] keywordArray = keywords.isEmpty() ? new String[0] : keywords.toLowerCase().split(" ");

	        // List to store matching investments
	        ArrayList<Object> matches = new ArrayList<>();  

	        // Search for stocks
	        for (Stock stock : stocks) {
	        	//Condition for if symbol does not match
	        	if (!symbol.isEmpty() && !stock.getSymbol().equalsIgnoreCase(symbol)) {
	                continue;  	            }

	            // Match keywords in the name if provided
	            String stockName = stock.getName().toLowerCase();
	            boolean keywordsMatch = true;
	            for (String keyword : keywordArray) {
	                if (!stockName.contains(keyword)) {
	                    keywordsMatch = false; 
	                    break;
	                }
	            }
	          //Condition for if keywords match
	            if (!keywordsMatch) {
	                continue;  
	            }
	            // Match price range if provided
	            double currentPrice = stock.getPrice();
	            //Condition for if price is outside the range
	            if ((lowerPrice != null && currentPrice < lowerPrice) || (upperPrice != null && currentPrice > upperPrice)) {
	                continue; 
	            }
	         // If all criteria matches, add to result
	            matches.add(stock);
	        }

	        // Search for mutual funds(same conditions for stocks)
	        for (MutualFund fund : mutualFunds) {
	            if (!symbol.isEmpty() && !fund.getSymbol().equalsIgnoreCase(symbol)) {
	                continue;  
	            }
	            String fundName = fund.getName().toLowerCase();
	            boolean keywordsMatch = true;
	            for (String keyword : keywordArray) {
	                if (!fundName.contains(keyword)) {
	                    keywordsMatch = false;
	                    break;
	                }
	            }
	            if (!keywordsMatch) {
	                continue;  
	            }
	            double currentPrice = fund.getPrice();
	            if ((lowerPrice != null && currentPrice < lowerPrice) || (upperPrice != null && currentPrice > upperPrice)) {
	                continue;  
	            }
	            matches.add(fund);
	        }

	        // Display matching investments
	        if (matches.isEmpty()) {
	            System.out.println("No matching investments found.");
	        } else {
	            for (Object investment : matches) {
	                if (investment instanceof Stock) {
	                    Stock stock = (Stock) investment;
	                    System.out.println("Stock: " + stock.getSymbol() + " - " + stock.getName() + " @ $" + stock.getPrice());
	                } else if (investment instanceof MutualFund) {
	                    MutualFund fund = (MutualFund) investment;
	                    System.out.println("Mutual Fund: " + fund.getSymbol() + " - " + fund.getName() + " @ $" + fund.getPrice());
	                }
	            }
	        }
	    }

	}