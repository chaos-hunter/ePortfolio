package ePortfolio;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio();
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("Enter an option:\n"
            		+"buy\n"
            		+"sell\n"
            		+"update\n"
            		+"getGain\n"
            		+"search\n"
            		+"quit");
            String option = scn.nextLine();

            switch (option.toLowerCase()) {
	            case "buy":
	            	String type = "";
	                
	                // Keep asking for a valid investment type until the user enters "stock" or "mutualfund"
	                while (true) {
	                    System.out.println("Enter investment type (stock/mutualfund): ");
	                    type = scn.nextLine();
	                    
	                    if (type.equalsIgnoreCase("stock") || type.equalsIgnoreCase("mutualfund")) {
	                        break;  // Exit the loop if a valid option is selected
	                    } else {
	                        System.out.println("Invalid investment type. Please enter 'stock' or 'mutualfund'.");
	                    }
	                }
	                System.out.println("Enter symbol: ");
	                String symbol = scn.nextLine();
	                System.out.println("Enter name: ");
	                String name = scn.nextLine();
	                System.out.println("Enter quantity: ");
	                int quantity = Integer.parseInt(scn.nextLine());
	                System.out.println("Enter price: ");
	                double price = Double.parseDouble(scn.nextLine());
	
	                if (type.equalsIgnoreCase("stock")) {
	                    portfolio.buyStock(symbol, name, quantity, price);  // Buy stock logic
	                } else if (type.equalsIgnoreCase("mutualfund")) {
	                    portfolio.buyMutualFund(symbol, name, quantity, price);  // Buy mutual fund logic
	                } else {
	                    System.out.println("Invalid investment type.");
	                }
	                break;


	            case "sell":
	            	String sellType = "";
	                
	                // Keep asking for a valid investment type until the user enters "stock" or "mutualfund"
	                while (true) {
	                    System.out.println("Enter investment type (stock/mutualfund): ");
	                    sellType = scn.nextLine();
	                    
	                    if (sellType.equalsIgnoreCase("stock") || sellType.equalsIgnoreCase("mutualfund")) {
	                        break;  // Exit the loop if a valid option is selected
	                    } else {
	                        System.out.println("Invalid investment type. Please enter 'stock' or 'mutualfund'.");
	                    }
	                }
	                
	                System.out.println("Enter symbol: ");
	                String sellSymbol = scn.nextLine();
	                System.out.println("Enter quantity to sell: ");
	                int sellQuantity = Integer.parseInt(scn.nextLine());
	                System.out.println("Enter selling price: ");
	                double sellPrice = Double.parseDouble(scn.nextLine());

	                if (sellType.equalsIgnoreCase("stock")) {
	                    portfolio.sellStock(sellSymbol, sellQuantity, sellPrice);
	                } else if (sellType.equalsIgnoreCase("mutualfund")) {
	                    portfolio.sellMutualFund(sellSymbol, sellQuantity, sellPrice);
	                } else {
	                    System.out.println("Invalid investment type.");
	                }
	                break;


                case "update":
                    portfolio.updatePrices(scn);
                    break;

                case "getgain":
                    portfolio.getGain();  // The getGain method already handles printing
                    break;

                case "search":
                	portfolio.search(scn);
                    break;

                case "quit":
                case "q":
                    System.out.println("Program ended.");
                    return;

                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
}

