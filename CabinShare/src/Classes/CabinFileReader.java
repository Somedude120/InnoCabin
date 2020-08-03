package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Models.CabinFileModel;

import java.util.ArrayList;
import java.util.List;

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
        if (command == "all") {
            textNum = findAllCabinFiles();
            for (int i = 1; i < textNum + 1; i++) {
                Scanner scanner = new Scanner(new File("src/Cabin/cabin" + i + ".txt"));
                while (scanner.hasNextLine()) {
                    if (scanner.hasNextFloat()) {
                        cabinObj = createCabin(Float.parseFloat(scanner.nextLine()),
                                Float.parseFloat(scanner.nextLine()), i);
                        cabinList.add(cabinObj);
                    } else {
                        cabinObj = createCabinPerson(scanner.nextLine(), i);
                        cabinList.add(cabinObj);
                    }
                }
                scanner.close();
            }
            return cabinList;

        } else {
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

    int findAllCabinFiles() {
        try (Stream<Path> walk = Files.walk(Paths.get("src/Cabin/"))) {

            List<String> result = walk.map(x -> x.toString()).filter(f -> f.endsWith("txt"))
                    .collect(Collectors.toList());
            return result.size();

        } catch (IOException e) {
            System.out.println("An error has occured.");
        }
        return 0;
    }

}
