package Classes;

import java.util.ArrayList;

import Models.CabinFileModel;

public class CabinFileCalculator {

    private ArrayList<CabinFileModel> cabinList;
    private float rentalCost;
    private float travelCost;
    private float personTravelCost;
    private float personTravelSum;
    private float costPerPerson;
    private float totalTravelCost;
    private float travelTimePerPerson;
    private float travelTimeGroupSum;
    private float sizeOfGroup;
    // private double travelSpeed = 1.5; //This one is missing
    private double travelSpeed = (Math.random() * ((130 - 30) + 1)) + 30; // This wasn't part of the assignment, and
                                                                          // cannot calculate time. (Equation for time
                                                                          // is Time = Distance / Speed)

    public CabinFileCalculator(ArrayList<CabinFileModel> CabinList) {
        cabinList = CabinList;
        personTravelSum = (float) 0;
        personTravelCost = (float) 0;
        totalTravelCost = 0;
    }

    public float totalCost() {
        rentalCost = cabinList.get(0).cabinRentalCost;
        travelCost = cabinList.get(0).travelCost;
        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).name != null) {
                personTravelCost = cabinList.get(i).distance * travelCost;
                personTravelSum = personTravelCost + personTravelSum;
            }
        }
        totalTravelCost = personTravelSum + rentalCost;

        return totalTravelCost;
    }

    public float perPersonCost() {
        costPerPerson = 0;
        totalTravelCost = totalCost();
        travelTimeGroupSum = travelTimePerGroup();
        sizeOfGroup = groupSize();

        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).name != null) {
                travelTimePerPerson = cabinList.get(i).distance / (float) travelSpeed;

                costPerPerson = ((totalTravelCost) * (1 - (travelTimePerPerson / travelTimeGroupSum))
                        / (sizeOfGroup - 1)) + travelTimePerPerson * (float) travelCost;

                CabinFileModel cabinObject = new CabinFileModel();
                cabinObject = cabinList.get(i);
                cabinObject.costPerPerson = costPerPerson;
                cabinList.set(i, cabinObject);
            }
        }
        return costPerPerson;
    }

    float travelTimePerGroup() {
        float travelTimeGroup = 0;
        travelTimeGroupSum = 0;

        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).name != null) {
                travelTimeGroup = travelTimeGroup + cabinList.get(i).distance / (float) travelSpeed;
                travelTimeGroupSum = travelTimeGroup + travelTimeGroupSum;
            }

        }
        return travelTimeGroupSum;
    }

    float groupSize() {

        sizeOfGroup = 0;
        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).name != null) {
                sizeOfGroup = sizeOfGroup + 1;
            }
        }
        return sizeOfGroup;
    }
}