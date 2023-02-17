package com.saes4.saes4.integration;

import com.saes4.saes4.TestUtil;
import com.saes4.saes4.integration.mock.SondageMock;
import com.saes4.saes4.model.UniqueData;
import com.saes4.saes4.model.UniqueResponse;
import com.saes4.saes4.model.entities.Sondage;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UtilsTest {

    @Autowired
    private MockMvc restMockMvc;

    @Autowired
    private SondageMock sondageMock;

    @Test
    @Transactional
    public void testMailIsValideWhereIsValide() throws Exception {
        // On crée un sondage
        Sondage sondage = sondageMock.createAndGetSondage();
        String VALID_MAIL = "valid@domaine.com";

        // On prépare le payload avec un email valide (inutilisé)
        UniqueData<String> data = new UniqueData<>();
        data.setData(VALID_MAIL);

        MvcResult result = this.restMockMvc.perform(
                        post("/api/utils/mail_valide")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(data)))
                .andExpect(status().isOk())
                .andReturn();

        // On récupère la réponse
        UniqueResponse<Boolean> response = TestUtil.parseJsonResponse(result, UniqueResponse.class);

        assertNotNull(response);
        // L'email doit être valide
        assertTrue(response.getResponse());
    }

    @Test
    @Transactional
    public void testMailIsValideWhereIsNotValide() throws Exception {
        // On crée un sondage
        Sondage sondage = sondageMock.createAndGetSondage();
        // email utilisé dans les mocks
        String INVALID_MAIL = "nom.prenom@domaine.com";

        // On prépare le payload avec un email valide (inutilisé)
        UniqueData<String> data = new UniqueData<>();
        data.setData(INVALID_MAIL);

        MvcResult result = this.restMockMvc.perform(
                        post("/api/utils/mail_valide")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(data)))
                .andExpect(status().isOk())
                .andReturn();

        // On récupère la réponse
        UniqueResponse<Boolean> response = TestUtil.parseJsonResponse(result, UniqueResponse.class);

        assertNotNull(response);
        // L'email doit être invalide
        assertFalse(response.getResponse());
    }

}
