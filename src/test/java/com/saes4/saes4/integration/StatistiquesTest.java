package com.saes4.saes4.integration;

import com.saes4.saes4.TestUtil;
import com.saes4.saes4.integration.mock.SondageMock;
import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.model.dto.statistiques.StatistiquesDTO;
import com.saes4.saes4.model.entities.Sondage;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StatistiquesTest {

    @Autowired
    private MockMvc restMockMvc;

    @Autowired
    private SondageMock sondageMock;

    @Test
    @Transactional
    public void testGetStatistiques() throws Exception {
        // La categories la plus choisie devrait être la 1
        // L'aliment le plus choisi devrait être le 25628
        final Long ID_CATEGORIE_PLUS_CHOISIE = (long) 1;
        final Long ID_ALIMENT_PLUS_CHOISI = (long) 25628;
        final Long NOMBRE_REPONSES = (long) 3;

        List<Sondage> sondages = sondageMock.createAndGet3SondagesStatistiques();

        MvcResult result = this.restMockMvc.perform(get("/api/statistiques/"))
                .andExpect(status().isOk())
                .andReturn();

        StatistiquesDTO statistiques = TestUtil.parseJsonResponse(result, StatistiquesDTO.class);

        assertEquals(statistiques.getStatistiques_generales().getNombre_reponses(), NOMBRE_REPONSES);
        long test = statistiques.getStatistiques_generales().getAliment_plus_choisi().getId_aliment();
        long tes2 = statistiques.getStatistiques_generales().getAliment_plus_choisi().getId_aliment();
        assertEquals(statistiques.getStatistiques_generales().getAliment_plus_choisi().getId_aliment(), ID_ALIMENT_PLUS_CHOISI);
        assertEquals(statistiques.getStatistiques_generales().getCategorie_plus_choisi().getId_categorie(), ID_CATEGORIE_PLUS_CHOISIE);
    }
}
