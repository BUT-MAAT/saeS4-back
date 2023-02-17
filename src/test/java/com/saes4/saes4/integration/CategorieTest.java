package com.saes4.saes4.integration;

import com.saes4.saes4.SaeS4Application;
import com.saes4.saes4.TestUtil;
import com.saes4.saes4.integration.mock.CategorieMock;
import com.saes4.saes4.model.dto.CategorieDTO;
import com.saes4.saes4.model.enums.TYPE_CATEGORIE;
import com.saes4.saes4.repository.CategorieRepository;
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
public class CategorieTest {

    @Autowired
    private MockMvc restMockMvc;

    @Test
    @Transactional
    public void testGetAllCategories() throws Exception {
        MvcResult result = this.restMockMvc.perform(get("/api/categories/categorie/all"))
                .andExpect(status().isOk())
                .andReturn();

        List<CategorieDTO> categorieDTOList = TestUtil.parseJsonArrayResponse(result, CategorieDTO.class);

        // On verifie que la liste de categories n'est pas nulle ni vide
        assertNotNull(categorieDTOList);
        assertFalse(categorieDTOList.isEmpty());

        // On vérifie que chaque categorie récupérée est du type CATEGORIE
        for(CategorieDTO categorieDTO : categorieDTOList) {
            assertEquals(categorieDTO.getType_categorie(), TYPE_CATEGORIE.CATEGORIE);
        }
    }

    @Test
    @Transactional
    public void testGetCategoriesByParentId() throws Exception {
        final Long PARENT_ID = (long) 1;
        MvcResult result = this.restMockMvc.perform(
                get("/api/categories/by_parent/" + PARENT_ID))
                .andExpect(status().isOk())
                .andReturn();

        List<CategorieDTO> categorieDTOList = TestUtil.parseJsonArrayResponse(result, CategorieDTO.class);

        // On verifie que la liste de categories n'est pas nulle
        assertNotNull(categorieDTOList);

        // On vérifie que chaque categorie récupérée :
        //   * n'est pas du type CATEGORIE
        //   * a bien PARENT_ID comme parent
        for(CategorieDTO categorieDTO : categorieDTOList) {
            assertNotEquals(categorieDTO.getType_categorie(), TYPE_CATEGORIE.CATEGORIE);
            assertEquals(categorieDTO.getCategorie_parent().getId_categorie(), PARENT_ID);
        }
    }
}