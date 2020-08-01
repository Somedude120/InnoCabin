import java.util.ArrayList;
import java.util.Scanner;

import Classes.CabinFileCalculator;
import Classes.CabinFileReader;
import Models.CabinFileModel;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Cabin Share!");
        System.out.println("Please enter a Cabin txt number or write \"all\" for every txt file");

        Scanner userInput = new Scanner(System.in);
        int cabinNumber = 0;
        String command = null;

        ArrayList<CabinFileModel> cabinList = new ArrayList<CabinFileModel>();
        CabinFileCalculator cabinFileCalculator = new CabinFileCalculator(cabinList);
        
        if (userInput.hasNextInt()) {
            cabinNumber = userInput.nextInt();
            userInput.close();
            
            CabinFileReader cabinFileReader = new CabinFileReader(cabinList, cabinNumber, command);
            cabinList = cabinFileReader.addCabinObjectToList();
            
            System.out.println("Rental Cost: " + cabinList.get(0).cabinRentalCost);
            System.out.println("Travel Cost: " + cabinList.get(0).travelCost);
            System.out.println("Total Cost: " + cabinFileCalculator.totalCost());
            System.out.println();

            cabinFileCalculator.perPersonCost();
            for (int i = 0; i < cabinList.size(); i++) {
                if(cabinList.get(i).name != null)
                {
                    System.out.println("Name: " + cabinList.get(i).name + " " + " Distance: " + cabinList.get(i).distance
                    + " Travel Cost: " + cabinList.get(i).costPerPerson);
                }
            }
        }
        else if(userInput.hasNext("all"))
        {
            userInput.close();
            System.out.println("Command ALL initiated");


        }
        else
            System.out.println("Not a valid number or command");
            userInput.close();
    }
}
