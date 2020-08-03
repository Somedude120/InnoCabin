package Classes;

import java.util.ArrayList;
import Models.CabinFileModel;
import Models.CabinGroupModel;

public class CabinFileCalculator {

    private ArrayList<CabinFileModel> cabinList;
    private ArrayList<CabinGroupModel> cabinGroupList;
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
    private CabinGroupModel cabinGroupObj;

    public CabinFileCalculator(ArrayList<CabinFileModel> CabinList, ArrayList<CabinGroupModel> CabinGroupList) {
        cabinList = CabinList;
        cabinGroupList = CabinGroupList;
        personTravelSum = (float) 0;
        personTravelCost = (float) 0;
        totalTravelCost = 0;
    }

    public float totalCost() {
        totalTravelCost = 0;
        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).groupNumber != groupNumber) {
                if (totalTravelCost != 0) {
                    cabinList.get(i - 1).totalTravelCost = totalTravelCost;
                    cabinGroupObj = new CabinGroupModel();
                    cabinGroupObj.totalCost = totalTravelCost;
                    cabinGroupObj.groupNumber = groupNumber;
                    cabinGroupObj.rentalCost = rentalCost;
                    cabinGroupObj.travelCost = travelCost;
                    cabinGroupList.add(cabinGroupObj);
                    // System.out.println("Total Cost: " + cabinList.get(i-1).totalTravelCost);
                    // System.out.println("Group: " + cabinList.get(i-1).groupNumber);
                    totalTravelCost = 0; // Reset totaltravelcost
                    personTravelSum = 0; // Reset persontravelsum
                }
                if (groupNumber > 12) {
                    break;
                } else {
                    groupNumber = groupNumber + 1;
                    i = i - 1;
                }
            } else if (cabinList.get(i).name == null && cabinList.get(i).groupNumber == groupNumber) {
                totalTravelCost = 0;

                rentalCost = cabinList.get(i).cabinRentalCost;
                travelCost = cabinList.get(i).travelCost;
                // if(!cabinGroupList.isEmpty()){
                // cabinGroupList.get(groupNumber - 2).rentalCost =
                // cabinList.get(i).cabinRentalCost;
                // }
                // System.out.println("Rental: " + cabinList.get(i).cabinRentalCost);
                // System.out.println("Travel: " + cabinList.get(i).travelCost);

            } else if (cabinList.get(i).name != null && cabinList.get(i).groupNumber == groupNumber) {
                // totalTravelCost = 0;
                // personTravelCost = 0;
                // personTravelSum = 0;
                cabinList.get(i).perPersonRawTravelCost = cabinList.get(i).distance * travelCost;
                // personTravelCost = cabinList.get(i).distance * travelCost;
                // personTravelSum = personTravelCost + personTravelSum;
                personTravelSum = cabinList.get(i).perPersonRawTravelCost + personTravelSum;

                // System.out.println("tName: " + cabinList.get(i).name);

                // System.out.println("personDistance: " + cabinList.get(i).distance);

                totalTravelCost = personTravelSum + rentalCost;
                // System.out.println("totalTravelCostNow: " + totalTravelCost);

            }
        }
        if (!cabinGroupList.isEmpty() && cabinGroupList.lastIndexOf(cabinGroupObj) + 2 == groupNumber) {
            cabinGroupObj = new CabinGroupModel();
            cabinGroupObj.totalCost = totalTravelCost;
            cabinGroupObj.groupNumber = groupNumber;
            cabinGroupObj.rentalCost = rentalCost;
            cabinGroupObj.travelCost = travelCost;
            cabinGroupList.add(cabinGroupObj);
        }

        return totalTravelCost;
    }

    public float perPersonCost() {
        totalTravelCost = totalCost();
        travelDistanceGroupSum = travelDistancePerGroup();
        sizeOfGroup = groupSize();
        costPerPerson = 0;
        groupNumber = 0;

        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).groupNumber != groupNumber) {
                groupNumber = groupNumber + 1;
                i = i - 1;
            } else if (cabinList.get(i).groupNumber == groupNumber) {
                if (cabinList.get(i).name != null) {
                    travelDistancePerPerson = cabinList.get(i).distance;

                    if (cabinGroupList.isEmpty()) {
                        costPerPerson = rentalCost * (1 - (travelDistancePerPerson) / travelDistanceGroupSum)
                                / (sizeOfGroup - 1) + (travelDistancePerPerson * (float) travelCost);

                        addToCabinList(i, costPerPerson);
                    } else {
                        costPerPerson = cabinGroupList.get(groupNumber - 1).rentalCost
                                * (1 - (travelDistancePerPerson) / cabinGroupList.get(groupNumber - 1).groupDistance)
                                / (cabinGroupList.get(groupNumber - 1).groupSize - 1)
                                + (travelDistancePerPerson * (float) cabinGroupList.get(groupNumber - 1).travelCost);

                        // System.out.println("Name: " + cabinList.get(i).name + " CostPer: " + costPerPerson);

                        addToCabinList(i, costPerPerson);
                    }

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
        groupNumber = 0;

        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).groupNumber != groupNumber) {
                groupNumber = groupNumber + 1;
                if (!cabinGroupList.isEmpty() && travelDistanceGroupSum != 0) {
                    cabinGroupList.get(groupNumber - 2).groupDistance = travelDistanceGroupSum;
                }
                travelDistanceGroupSum = 0;
                i = i - 1;
            } else if (cabinList.get(i).name != null) {
                // System.out.println("Name: " + cabinList.get(i).name + " Distance: " +
                // cabinList.get(i).distance);
                travelDistanceGroupSum = cabinList.get(i).distance + travelDistanceGroupSum;
                // System.out.println("Traveldistancegroup: " + travelDistanceGroupSum);
            }
        }
        if (!cabinGroupList.isEmpty()) {
            cabinGroupList.get(groupNumber -1).groupDistance = travelDistanceGroupSum;
        }

        return travelDistanceGroupSum;
    }

    float groupSize() {

        groupNumber = 0;
        sizeOfGroup = 0;
        for (int i = 0; i < cabinList.size(); i++) {
            if (cabinList.get(i).groupNumber != groupNumber) {
                groupNumber = groupNumber + 1;
                if (!cabinGroupList.isEmpty() && sizeOfGroup != 0) {
                    cabinGroupList.get(groupNumber - 2).groupSize = sizeOfGroup;
                }
                i = i - 1;
                sizeOfGroup = 0;
            } else if (cabinList.get(i).name != null && cabinList.get(i).groupNumber == groupNumber) {
                sizeOfGroup = sizeOfGroup + 1;
                // System.out.println("sizeOfGroup: " + sizeOfGroup);
            }
        }
        if (!cabinGroupList.isEmpty()) {
            cabinGroupList.get(groupNumber -1).groupSize = sizeOfGroup;
        }
        return sizeOfGroup;
    }
}