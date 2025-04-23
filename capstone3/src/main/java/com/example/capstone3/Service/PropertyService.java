package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Admin;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Model.Property;
import com.example.capstone3.Repository.AdminRepository;
import com.example.capstone3.Repository.OwnerRepository;
import com.example.capstone3.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final AdminRepository adminRepository;


    //GET
    public List<Property> getAllProperty(){
        return propertyRepository.findAll();
    }
    //ADD
    public void addProperty(Property property,Integer owner_id){
        //we can after check the ids
        Owner owner = ownerRepository.findOwnerById(owner_id);

        if(owner == null){
            throw new ApiException("Owner not found");
        }

        property.setOwner(owner);

        propertyRepository.save(property);
    }

    //Update
    public void updateProperty(Integer id,Property property){
        Property oldProperty = propertyRepository.findPropertyById(id);
        if(oldProperty == null){
            throw new ApiException("Property not found");
        }
        oldProperty.setDescription(property.getDescription());
        oldProperty.setTitle(property.getTitle());
        //oldProperty.setIsApproved();
        propertyRepository.save(oldProperty);
    }

    //DELETE
    public void deleteProperty(Integer id){
        Property delProperty = propertyRepository.findPropertyById(id);
        if(delProperty == null){
            throw new ApiException("Property not found");
        }
        propertyRepository.delete(delProperty);
    }

    //4.endpoint //Abdullah //range
    public List<Property> getPropertyByPriceRange(Double min,Double max){
        List<Property> allProperty = propertyRepository.findAll();
        List<Property> filtered = new ArrayList<>();
        for (Property property : allProperty){
            if (property.getPrice() >= min && property.getPrice() <= max){
                filtered.add(property);
            }
        }
        return filtered;
    }

    //6.endpoint //Abdullah //By Location
    public List<Property> getPropertyByLocation(String loc){
        List<Property> properties = propertyRepository.findPropertyByLocation(loc);
        List<Property> byLoc = new ArrayList<>();
        if (loc == null){
            throw new ApiException("Location not found");
        }
        for (Property property : properties){
            if (property.getLocation().equalsIgnoreCase(loc)){
                byLoc.add(property);
            }
        }
        return byLoc;

    }

    //Add with

    //Assign



}