package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import Models.CabinFileModel;

/*Structure of file:
Cabin Rental
Travel Cost

Name + Distance
*/
public class CabinFileReader {

    public CabinFileReader(){
        
    }

    public ArrayList<CabinFileModel> createCabinObject(ArrayList<CabinFileModel> cabinList, int textNum) throws FileNotFoundException {
        
        //Scan the cabin txt file in
        if(true){
            Scanner scanner = new Scanner(new File("src/Cabin/cabin" + textNum + ".txt"));
            String nameAndDistance;

            while (scanner.hasNextLine()) {
                if(scanner.hasNextFloat()){
                    CabinFileModel cabinObj = new CabinFileModel();
                    cabinObj.cabinRentalCost = Float.parseFloat(scanner.nextLine());
                    cabinObj.travelCost = Float.parseFloat(scanner.nextLine());
                    cabinList.add(cabinObj);
                }
                else {
                    CabinFileModel cabinObj = new CabinFileModel();
                    nameAndDistance = scanner.nextLine();
                    String[] stringParts = nameAndDistance.split("\\s+");
                    cabinObj.name = stringParts[0];
                    cabinObj.distance = Float.parseFloat(stringParts[1]);
                    cabinList.add(cabinObj);
                }
            }
            scanner.close();
            return cabinList;
        }
        else
        System.out.println(textNum + " is not a number, please enter a valid number");
        return null;
    }
}
