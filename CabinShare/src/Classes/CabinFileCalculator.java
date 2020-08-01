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
    private float travelDistancePerPerson;
    private float travelDistanceGroupSum;
    private float sizeOfGroup;
    private int groupNumber;

    public CabinFileCalculator(ArrayList<CabinFileModel> CabinList) {
        cabinList = CabinList;
        personTravelSum = (float) 0;
        personTravelCost = (float) 0;
        totalTravelCost = 0;
    }

    public float totalCost() {
        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).groupNumber != groupNumber) {
                groupNumber = groupNumber + 1;
                i = i - 1;
            } else if (cabinList.get(i).name == null) {
                rentalCost = cabinList.get(i).cabinRentalCost;
                travelCost = cabinList.get(i).travelCost;
            } else if (cabinList.get(i).name != null) {
                personTravelCost = cabinList.get(i).distance * travelCost;
                personTravelSum = personTravelCost + personTravelSum;

                totalTravelCost = personTravelSum + rentalCost;
            }
        }

        return totalTravelCost;
    }

    public float perPersonCost() {
        costPerPerson = 0;
        totalTravelCost = totalCost();
        travelDistanceGroupSum = travelDistancePerGroup();
        sizeOfGroup = groupSize();

        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).groupNumber != groupNumber) {
                groupNumber = groupNumber + 1;
                i = i - 1;
            } else if (cabinList.get(i).groupNumber == groupNumber) {
                if (cabinList.get(i).name != null) {
                    travelDistancePerPerson = cabinList.get(i).distance;

                    costPerPerson = rentalCost * (1 - (travelDistancePerPerson) / travelDistanceGroupSum)
                            / (sizeOfGroup - 1) + (travelDistancePerPerson * (float) travelCost);

                    addToCabinList(i, costPerPerson);
                }
            }
        }
        return costPerPerson;
    }

    void addToCabinList(int count, float cost) {
        CabinFileModel cabinObject = new CabinFileModel();
        cabinObject = cabinList.get(count);
        cabinObject.costPerPerson = cost;
        cabinList.set(count, cabinObject);
    }

    float travelDistancePerGroup() {
        travelDistanceGroupSum = 0;

        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).groupNumber != groupNumber) {
                groupNumber = groupNumber + 1;
                i = i - 1;
            } else if (cabinList.get(i).name != null) {
                travelDistanceGroupSum = cabinList.get(i).distance + travelDistanceGroupSum;
            }
        }

        return travelDistanceGroupSum;
    }

    float groupSize() {

        sizeOfGroup = 0;

        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).groupNumber != groupNumber) {
                groupNumber = groupNumber + 1;
                i = i - 1;
            } else if (cabinList.get(i).name != null) {
                sizeOfGroup = sizeOfGroup + 1;
            }
        }

        return sizeOfGroup;
    }
}