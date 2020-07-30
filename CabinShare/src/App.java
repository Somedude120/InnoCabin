import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        // CabinFileModel cabinObject = new CabinFileModel();
        ArrayList<CabinFileModel> cabinList = new ArrayList<CabinFileModel>();
        CabinFileReader cabinFileReader = new CabinFileReader();
        CabinFileCalculator cabinFileCalculator = new CabinFileCalculator();

        if(userInput.hasNextInt()) {
            cabinNumber = userInput.nextInt();
            userInput.close();

            cabinList = cabinFileReader.createCabinObject(cabinList, cabinNumber);
            System.out.println("Rental Cost: " + cabinList.get(0).cabinRentalCost);
            System.out.println("Travel Cost: " + cabinList.get(0).travelCost);

            for (int i = 0; i < cabinList.size(); i++) {
                System.out.println(cabinList.get(i).name + " " + cabinList.get(i).distance);
            }
            Float totalCost = cabinFileCalculator.calculator(cabinList);

            System.out.println("Total cost: " + totalCost.toString());

            // System.out.println("Cabin Rental: " + cabinList.get(0).cabinRentalCost);
            // System.out.println("Travel Cost: " + cabinList.get(0).travelCost);
            // System.out.println("Name: " + cabinList.get(0).name);
            // System.out.println("Distance: " + cabinList.get(0).distance);
        }
        // else if(userInput.hasNext("all")) {
        //     int textNum = 1;
        //     Files.walk(Paths.get("Cabin/"))
        //     .filter(Files::isRegularFile)
        //     .forEach(System.out::println);
            
        // }
        else
        userInput.close();
        
        // System.out.println("Name: " + cabinList.get(0).name);
    }
}

