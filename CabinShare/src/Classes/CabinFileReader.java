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

    CabinFileModel cabinObj;
    ArrayList<CabinFileModel> cabinList;
    int textNum;

    public CabinFileReader(ArrayList<CabinFileModel> CabinList, int TextNum) {
        cabinList = CabinList;
        textNum = TextNum;
    }

    public ArrayList<CabinFileModel> createCabinObject() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/Cabin/cabin" + textNum + ".txt"));
        String nameAndDistance;

        while (scanner.hasNextLine()) {
            if (scanner.hasNextFloat()) {
                cabinObj = new CabinFileModel();
                cabinObj.cabinRentalCost = Float.parseFloat(scanner.nextLine());
                cabinObj.travelCost = Float.parseFloat(scanner.nextLine());
                cabinList.add(cabinObj);
            } else {
                cabinObj = new CabinFileModel();
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
}
