package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Models.CabinFileModel;

import java.util.ArrayList;

public class CabinFileReader {

    CabinFileModel cabinObj;
    ArrayList<CabinFileModel> cabinList;
    int textNum;
    String nameAndDistance;
    String command;
    String name;

    public CabinFileReader(ArrayList<CabinFileModel> CabinList, int TextNum, String Command) {
        cabinList = CabinList;
        textNum = TextNum;
        command = Command;
    }

    public ArrayList<CabinFileModel> addCabinObjectToList() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/Cabin/cabin" + textNum + ".txt"));

        while (scanner.hasNextLine()) {
            if (scanner.hasNextFloat()) {
                cabinObj = createCabin(Float.parseFloat(scanner.nextLine()), Float.parseFloat(scanner.nextLine()),
                        textNum);
                cabinList.add(cabinObj);
            } else {
                cabinObj = createCabinPerson(scanner.nextLine(), textNum);
                cabinList.add(cabinObj);
            }
        }
        scanner.close();
        return cabinList;
    }

    CabinFileModel createCabin(float rentalCost, float travelCost, int groupNumber) {
        cabinObj = new CabinFileModel();
        cabinObj.cabinRentalCost = rentalCost;
        cabinObj.travelCost = travelCost;
        cabinObj.groupNumber = groupNumber;

        return cabinObj;
    }

    CabinFileModel createCabinPerson(String nameAndDistance, int groupNumber) {
        cabinObj = new CabinFileModel();
        String[] stringParts = nameAndDistance.split("\\s+");
        cabinObj.name = stringParts[0];
        cabinObj.distance = Float.parseFloat(stringParts[1]);
        cabinObj.groupNumber = groupNumber;
        return cabinObj;
    }

}
