package com.team41.cardic.homelessshelterapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shott on 3/6/18.
 */

public class Filter {
    private final Model model = Model.getInstance();
    private List<Shelter> modelShelters = model.getShelters();
    private int modelSheltersSize = modelShelters.size();

    private final List<Shelter> filteredShelters = new ArrayList<>();

    /**
     * method that searches through all the Shelters of the Model for a specific name
     * and adds that shelter to the filteredShelters list
     * @param name String of the name of the Shelter that you want to search for
     */
    public void getByName(String name) {
        modelShelters = model.getShelters();
        modelSheltersSize = modelShelters.size();
        for(int i = 0; i < modelSheltersSize; i++) {
            Shelter currentShelter = modelShelters.get(i);
            String currentShelterName = currentShelter.getName();
            if(currentShelterName.equals(name)) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    /**
     * method that searches through all the Shelters of the Model that are female gender restricted
     * and adds those shelters to the filteredShelters list
     */
    public void getFemaleOnly() {
        modelShelters = model.getShelters();
        modelSheltersSize = modelShelters.size();
        for(int i = 0; i < modelSheltersSize; i++) {
            Shelter currentShelter = modelShelters.get(i);
            String currentShelterGender = currentShelter.getGender();
            if(currentShelterGender.contains("Women") ||
                    currentShelterGender.contains("anyGender")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    /**
     * method that searches through all the Shelters of the Model that are male gender restricted
     * and adds those shelters to the filteredShelters list
     */
    public void getMaleOnly() {
        modelShelters = model.getShelters();
        modelSheltersSize = modelShelters.size();
        for(int i = 0; i < modelSheltersSize; i++) {
            Shelter currentShelter = modelShelters.get(i);
            String currentShelterGender = currentShelter.getGender();
            if(currentShelterGender.contains("Men") ||
                    currentShelterGender.contains("anyGender")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    /**
     * method that searches through all the Shelters of the Model that allow Newborns
     * and adds those shelters to the filteredShelters list
     */
    public void getNewborns() {
        modelShelters = model.getShelters();
        modelSheltersSize = modelShelters.size();
        for(int i = 0; i < modelSheltersSize; i++) {
            Shelter currentShelter = modelShelters.get(i);
            String currentShelterGender = currentShelter.getGender();
            if(currentShelterGender.contains("Newborns") ||
                    currentShelterGender.contains("anyAge") ||
                    currentShelterGender.contains("Anyone")){
                filteredShelters.add(currentShelter);
            }
        }
    }

    /**
     * method that searches through all the Shelters of the Model that allow families
     * and adds those shelters to the filteredShelters list
     */
    public void getFamilies() {
        modelShelters = model.getShelters();
        modelSheltersSize = modelShelters.size();
        for(int i = 0; i < modelSheltersSize; i++) {
            Shelter currentShelter = modelShelters.get(i);
            String currentShelterFamilies = currentShelter.getGender();
            if(currentShelterFamilies.contains("Children") ||
                    currentShelterFamilies.contains("anyAge") ||
                    currentShelterFamilies.contains("Anyone")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    /**
     * method that searches through all the Shelters of the Model that allow Young Adults
     * and adds those shelters to the filteredShelters list
     */
    public void getYoungAdults() {
        modelShelters = model.getShelters();
        modelSheltersSize = modelShelters.size();
        for(int i = 0; i < modelSheltersSize; i++) {
            Shelter currentShelter = modelShelters.get(i);
            String currentShelterYA = currentShelter.getGender();
            if(currentShelterYA.contains("Young adults") ||
                    currentShelterYA.contains("anyAge") ||
                    currentShelterYA.contains("Anyone")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    /**
     * method that searches through all the Shelters of the Model that allow anyone
     * and adds those shelters to the filteredShelters list
     */
    public void getAnyone() {
        modelShelters = model.getShelters();
        modelSheltersSize = modelShelters.size();
        for(int i = 0; i < modelSheltersSize; i++) {
            Shelter currentShelter = modelShelters.get(i);
            String currentShelterGender = currentShelter.getGender();
            if(currentShelterGender.contains("Anyone") ||
                    currentShelterGender.contains("anyAge")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    /**
     * getter method for the FilteredShelters of the Filter class
     * @return returns list of Shelters that are filtered
     */
    public List<Shelter> getFilteredShelters() {
        return filteredShelters;
    }

    /**
     * method that clears the list of Filtered Shelters of this Filter class
     */
    public void clearShelterList() {
        filteredShelters.clear();
    }

}

