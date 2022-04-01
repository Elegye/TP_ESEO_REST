package com.eseo.rest_server.controller;
import com.eseo.rest_server.entity.Ville;
import com.eseo.rest_server.dto.VilleDto;
import com.eseo.rest_server.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/ville")
public class VilleController {

    @Autowired
    private VilleService villeService;

    @GetMapping()
    public @ResponseBody Iterable<Ville> get(
            @RequestParam(required=false, value="postalCode") String postalCode,
            @RequestParam(required=false, value="name") String name,
            @RequestParam(required=false, value="inseeCode") String inseeCode
    ) {
        return this.villeService.getVilles(postalCode, name, inseeCode);
    }

    @PostMapping(value="/create")
    public void post(@RequestBody VilleDto ville) {
        this.villeService.addVille(ville);
    }

    @PutMapping(value="/edit")
    public void put(@RequestBody Ville ville){
        this.villeService.updateVille(ville);
    }

    @DeleteMapping(value="/delete/{id}")
    public void delete(@PathVariable(value="id") String inseeCode){
        this.villeService.deleteVille(inseeCode);
    }

}