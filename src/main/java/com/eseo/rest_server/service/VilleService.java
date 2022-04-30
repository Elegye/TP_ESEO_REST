package com.eseo.rest_server.service;

import com.eseo.rest_server.entity.Ville;
import com.eseo.rest_server.dto.VilleDto;
import com.eseo.rest_server.repository.VilleRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    public Iterable<Ville> getVilles(
            String postalCode,
            String name,
            String inseeCode
    ) {
        if(postalCode != null){
            return villeRepository.findByPostalCode(postalCode);
        }
        if(name != null){
            return villeRepository.findByName(name);
        }
        if(inseeCode != null){
            return villeRepository.findByInseeCode(inseeCode);
        }

        return villeRepository.findAll();
    }

    public Ville addVille(VilleDto villeDto){

        Ville ville = villeRepository.save(this.hydrate(villeDto));

        return ville;
    }

    public void deleteVille(String inseeCode) throws ObjectNotFoundException{
        try{
            villeRepository.delete(villeRepository.findOneByInseeCode(inseeCode));
        }
        catch (IllegalArgumentException e){
            throw new ObjectNotFoundException(inseeCode, "Record does not exist !");
        }
    }

    public void updateVille(VilleDto villeDto) throws ObjectNotFoundException{
        try{
            villeRepository.save(this.hydrate(villeDto));
        }
        catch (IllegalArgumentException e){
            throw new ObjectNotFoundException(villeDto.getInseeCode(), "Record does not exist !");
        }
    }

    public Ville hydrate(VilleDto villeDto){
        Ville ville = new Ville();

        ville.setInseeCode(villeDto.getInseeCode());
        ville.setName(villeDto.getName());
        ville.setLabel(villeDto.getLabel());
        ville.setLat(villeDto.getLat());
        ville.setLon(villeDto.getLon());
        ville.setPostalCode(villeDto.getPostalCode());
        ville.setLigne5(villeDto.getLine5());

        return ville;
    }
}