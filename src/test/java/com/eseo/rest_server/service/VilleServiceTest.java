package com.eseo.rest_server.service;

import com.eseo.rest_server.entity.Ville;
import com.eseo.rest_server.repository.VilleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VilleServiceTest {

    @MockBean
    VilleRepository villeRepository;

    @MockBean
    VilleService villeService;

    private List<Ville> records = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        Ville VILLE_1 = new Ville("01001", "Ville 1", "01400", "Ville 1", "", "46.2", "4.99");
        Ville VILLE_2 = new Ville("01002", "Ville 2", "01400", "Ville 2", "", "46.0", "5.12");
        Ville VILLE_3 = new Ville("01003", "Ville 3", "01400", "Ville 3", "", "47.0", "5.08");

        this.records.addAll(Arrays.asList(VILLE_1, VILLE_2, VILLE_3));
    }

    @Test
    void getVilles() throws Exception {
        assertTrue(true);
    }

    @Test
    void addVille() {
    }

    @Test
    void deleteVille() {
    }

    @Test
    void updateVille() {
    }

    @Test
    void getDistance() {
    }
}