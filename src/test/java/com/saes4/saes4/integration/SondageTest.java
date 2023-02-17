package com.saes4.saes4.integration;

import com.saes4.saes4.TestUtil;
import com.saes4.saes4.integration.mock.SondageMock;
import com.saes4.saes4.mapper.SondageMapper;
import com.saes4.saes4.model.entities.Sondage;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SondageTest {

    @Autowired
    private MockMvc restMockMvc;

    @Autowired
    private SondageMock sondageMock;

    @Autowired
    private SondageMapper sondageMapper;

    @Test
    @Transactional
    public void testCreateSondage() throws Exception {
        Sondage sondage = sondageMock.getSondage();

        MvcResult result = this.restMockMvc.perform(
                post("/api/sondage/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(sondageMapper.sondageToSondageDTO(sondage))))
                .andExpect(status().isOk())
                .andReturn();

        // On recupere le dernier sondage du repository (notre insertion)
        Sondage sondageRequested = TestUtil.parseJsonResponse(result, Sondage.class);

        assertNotNull(sondageRequested);
        // Maintenant que l'insertion est faite, l'id doit être défini
       // assertNotNull(sondageRequested.getId_personne());
        // On vérifie que les attributs de sondage valent ceux de notre insertion
        assertEquals(sondage.getNom(), sondageRequested.getNom());
        assertEquals(sondage.getPrenom(), sondageRequested.getPrenom());
        assertEquals(sondage.getMail(), sondageRequested.getMail());
        assertEquals(sondage.getCode_postal(), sondageRequested.getCode_postal());
        assertEquals(sondage.getVille(), sondageRequested.getVille());
        assertEquals(sondage.getDate_reponse(), sondageRequested.getDate_reponse());
        List<Long> alimentsId = sondage.getListe_aliments()
                .stream()
                .map(aliment -> aliment.getId_aliment())
                .toList();
        List<Long> alimentsRequestedId = sondage.getListe_aliments()
                .stream()
                .map(aliment -> aliment.getId_aliment())
                .toList();
        assertTrue(alimentsId.equals(alimentsRequestedId));
    }
}
