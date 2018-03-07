package com.team41.cardic.homelessshelterapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shott on 3/6/18.
 */

public class Filter {
    Model model = Model.getInstance();
    List<Shelter> filteredShelters = new ArrayList<>();

    public void getByName(String name) {
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
            if(currentShelter.getName().equals(name)) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    public void getFemaleOnly() {
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
            if(currentShelter.getGender().contains("Women") || currentShelter.getGender().contains("anyGender")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    public void getMaleOnly() {
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
            if(currentShelter.getGender().contains("Men") || currentShelter.getGender().contains("anyGender")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    public void getNewborns() {
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
            if(currentShelter.getGender().contains("Newborns") || currentShelter.getGender().contains("anyAge") || currentShelter.getGender().contains("Anyone")){
                filteredShelters.add(currentShelter);
            }
        }
    }

    public void getFamilies() {
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
            if(currentShelter.getGender().contains("Children") || currentShelter.getGender().contains("anyAge") || currentShelter.getGender().contains("Anyone")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    public void getYoungAdults() {
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
            if(currentShelter.getGender().contains("Young adults") || currentShelter.getGender().contains("anyAge") || currentShelter.getGender().contains("Anyone")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    public void getAnyone() {
        for(int i = 0; i < model.getShelters().size(); i++) {
            Shelter currentShelter = model.getShelters().get(i);
            if(currentShelter.getGender().contains("Anyone") || currentShelter.getGender().contains("anyAge")) {
                filteredShelters.add(currentShelter);
            }
        }
    }

    public List<Shelter> getFilteredShelters() {
        return filteredShelters;
    }

    public void clearShelterList() {
        filteredShelters.clear();
    }

}

