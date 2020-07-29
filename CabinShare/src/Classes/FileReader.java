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
class FileReader {
    ArrayList<CabinFileModel> createCabinObject(ArrayList<CabinFileModel> cabinList) throws FileNotFoundException {
        
        Scanner scanner = new Scanner(new File("filename"));
        CabinFileModel cabinObj = new CabinFileModel();
        String nameAndDistance;

        while (scanner.hasNextLine()) {
            if(scanner.hasNextFloat() || scanner.hasNextInt()){
                cabinObj.cabinRentalCost = Float.parseFloat(scanner.nextLine());
                cabinObj.travelCost = Float.parseFloat(scanner.nextLine());
            }
            else {
                nameAndDistance = scanner.nextLine();
                String[] stringParts = nameAndDistance.split("\\s+");
                cabinObj.name = stringParts[0];
                cabinObj.distance = stringParts[1];
            }
            cabinList.add(cabinObj);
        }
        return cabinList;
    }
}
