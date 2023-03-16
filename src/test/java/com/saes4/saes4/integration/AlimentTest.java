package com.saes4.saes4.integration;


import com.saes4.saes4.TestUtil;
import com.saes4.saes4.integration.mock.SondageMock;
import com.saes4.saes4.model.dto.AlimentDTO;
import com.saes4.saes4.model.dto.SondageDTO;
import com.saes4.saes4.model.dto.statistiques.AlimentCountDTO;
import com.saes4.saes4.model.entities.Sondage;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AlimentTest {

    @Autowired
    private MockMvc restMockMvc;
    @Autowired
    private SondageMock sondageMock;

    @Test
    @Transactional
    public void testGetAllAliments() throws Exception {
        MvcResult result = this.restMockMvc.perform(get("/api/aliment/all"))
                .andExpect(status().isOk())
                .andReturn();

        List<AlimentDTO> alimentDTOList = TestUtil.parseJsonArrayResponse(result, AlimentDTO.class);

        // On verifie que la liste d'aliments n'est pas nulle ni vide
        assertNotNull(alimentDTOList);
        assertTrue(!alimentDTOList.isEmpty());
    }

    @Test
    @Transactional
    public void testGetAlimentsBySousSousCategorieNoValeursNutritives() throws Exception {
        final Long SOUSSOUSCATEGORIE_ID = (long) 101000;

        List<AlimentDTO> alimentDTOList = this.getAlimentBySousSousCategorie(SOUSSOUSCATEGORIE_ID, false);

        // On verifie que la liste d'aliments n'est pas nulle
        assertNotNull(alimentDTOList);

        // On verifie pour chaque aliment que :
        //  * Sa sous categorie soit bien SOUSSOUSCATEGORIE_ID
        //  * Qu'il n'a pas de valeurs nutritives associées
        for (AlimentDTO alimentDTO : alimentDTOList) {
            assertEquals(alimentDTO.getId_sous_sous_categorie(), SOUSSOUSCATEGORIE_ID);
            assertNull(alimentDTO.getValeurs_nutritives());
        }
    }

    @Test
    @Transactional
    public void testGetAlimentsBySousSousCategorieWithValeursNutritives() throws Exception {
        final Long SOUSSOUSCATEGORIE_ID = (long) 101000;

        List<AlimentDTO> alimentDTOList = this.getAlimentBySousSousCategorie(SOUSSOUSCATEGORIE_ID, true);

        // On verifie que la liste d'aliments n'est pas nulle
        assertNotEquals(alimentDTOList, null);

        // On verifie pour chaque aliment que :
        //  * Sa sous categorie soit bien SOUSSOUSCATEGORIE_ID
        //  * Que ses valeurs nutritives aient bien le même id que l'aliment
        for (AlimentDTO alimentDTO : alimentDTOList) {
            assertEquals(alimentDTO.getId_sous_sous_categorie(), SOUSSOUSCATEGORIE_ID);
            assertEquals(alimentDTO.getValeurs_nutritives().getId_valeurs_nutritives(), alimentDTO.getId_aliment());
        }
    }

    private List<AlimentDTO> getAlimentBySousSousCategorie(final Long id, final boolean val_nutritives) throws Exception {
        MvcResult result = this.restMockMvc.perform(
                        get("/api/aliment/by_soussouscategorie/" + id)
                                .param("valeurs_nutritives", String.valueOf(val_nutritives)))
                .andExpect(status().isOk())
                .andReturn();
        return TestUtil.parseJsonArrayResponse(result, AlimentDTO.class);
    }

    @Test
    @Transactional
    //if the user input is bad, the basic input willl be "1+Avenue"
    public void testGetMostConsumedAlimentsByDepartment() throws Exception{
        final Long ALIMENTPLUSCHOISI = (long) 25628;
        List<Sondage> sodages = sondageMock.getSondagesForAlimentByDepartment();
        MvcResult result = this.restMockMvc.perform(
                get("/api/aliment/MostConsumedByDepartment/" + "91"))
                        .andExpect(status().isOk())
                        .andReturn();

        List<AlimentCountDTO> alimentCountDTO = TestUtil.parseJsonArrayResponse(result, AlimentCountDTO.class);
        String test = result.getResponse().getContentAsString();
        assertEquals(ALIMENTPLUSCHOISI,alimentCountDTO.get(0).getId_aliment());
    }
    @Test
    @Transactional
    public void testGetMostConsumedAlimentsByDepartmentBadInput() throws Exception{

        List<Sondage> sodages = sondageMock.createAndGet3SondagesStatistiques();
        MvcResult result = this.restMockMvc.perform(
                        get("/api/aliment/MostConsumedByDepartment/" + "fdp"))
                .andExpect(status().isOk())
                .andReturn();
        List<AlimentDTO> alimentDTOList = TestUtil.parseJsonArrayResponse(result, AlimentDTO.class);

    }
}
