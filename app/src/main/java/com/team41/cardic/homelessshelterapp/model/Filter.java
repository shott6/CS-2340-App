package com.team41.cardic.homelessshelterapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shott on 3/6/18.
 */

public class Filter {
    private final Model model = Model.getInstance();
    private final List<Shelter> filteredShelters = new ArrayList<>();

    /**
     * method that searches through all the Shelters of the Model for a specific name
     * and adds that shelter to the filteredShelters list
     * @param name String of the name of the Shelter that you want to search for
     */
    public void getByName(String name) {
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
            if(currentShelter.getName().equals(name)) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    /**
     * method that searches through all the Shelters of the Model that are female gender restricted
     * and adds those shelters to the filteredShelters list
     */
    public void getFemaleOnly() {
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
            if(currentShelter.getGender().contains("Women") ||
                    currentShelter.getGender().contains("anyGender")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    /**
     * method that searches through all the Shelters of the Model that are male gender restricted
     * and adds those shelters to the filteredShelters list
     */
    public void getMaleOnly() {
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
            if(currentShelter.getGender().contains("Men") ||
                    currentShelter.getGender().contains("anyGender")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    /**
     * method that searches through all the Shelters of the Model that allow Newborns
     * and adds those shelters to the filteredShelters list
     */
    public void getNewborns() {
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
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
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
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
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
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
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
            if(currentShelter.getGender().contains("Anyone") ||
                    currentShelter.getGender().contains("anyAge")) {
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

