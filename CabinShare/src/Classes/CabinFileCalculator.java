package Classes;

import java.util.ArrayList;

import Models.CabinFileModel;

public class CabinFileCalculator {
    public CabinFileCalculator(){

    }
    public float calculator(ArrayList<CabinFileModel> cabinList){
        Float rentalCost = cabinList.get(0).cabinRentalCost;
        Float travelCost = cabinList.get(0).travelCost;
        Float personTravel = (float)0;
        Float personTravelSum = (float)0;
        for (int i = 0; i < cabinList.size(); i++) {
            if(cabinList.get(i).name != null)
            {
                personTravel = cabinList.get(i).distance * travelCost;
                personTravelSum = personTravel+personTravelSum;    
            }
        }

        return personTravelSum + rentalCost;
    }
}