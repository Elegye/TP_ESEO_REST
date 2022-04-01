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
        Ville ville = new Ville();

        ville.setInseeCode(villeDto.getCodeINSEE());
        ville.setName(villeDto.getName());
        ville.setLabel(villeDto.getLabel());
        ville.setLat(villeDto.getLat());
        ville.setLon(villeDto.getLon());
        ville.setPostalCode(villeDto.getPostalCode());

        villeRepository.save(ville);

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

    public void updateVille(Ville ville) throws ObjectNotFoundException{
        try{
            villeRepository.save(ville);
        }
        catch (IllegalArgumentException e){
            throw new ObjectNotFoundException(ville.getInseeCode(), "Record does not exist !");
        }
    }

    public int getDistance(String fromInseeCode, String toInseeCode) throws ObjectNotFoundException{
        Ville from;
        Ville to;

        try{
            from = this.villeRepository.findOneByInseeCode(fromInseeCode);
        }
        catch (IllegalArgumentException e){
            throw new ObjectNotFoundException(fromInseeCode, "Record does not exist !");
        }

        try{
            to = this.villeRepository.findOneByInseeCode(toInseeCode);
        }
        catch (IllegalArgumentException e){
            throw new ObjectNotFoundException(toInseeCode, "Record does not exist !");
        }

        double lon1 = Double.parseDouble(from.getLon());
        double lon2 = Double.parseDouble(to.getLon());
        double lat1 = Double.parseDouble(from.getLat());
        double lat2 = Double.parseDouble(to.getLat());

        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        double r = 6371*1000;

        return (int) Math.round(c * r);

    }
}