package com.saes4.saes4.integration;

import com.saes4.saes4.TestUtil;
import com.saes4.saes4.model.dto.AddressDTO;
import com.saes4.saes4.model.dto.AlimentDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class AddressTest {
    @Autowired
    private MockMvc restMockMvc;

    @Test
    @Transactional
    //if length < 3 then substring += "Avenue"
    public void testStringLengthInf3() throws Exception {
        String substring1 = "1";
        String substring2 = "1+";
        MvcResult result1 = this.restMockMvc.perform(get("/api/address/getaddress/"+substring1))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult result2 = this.restMockMvc.perform(get("/api/address/getaddress/"+substring2))
                .andExpect(status().isOk())
                .andReturn();

        List<AddressDTO> addresses1 = TestUtil.parseJsonArrayResponse(result1, AddressDTO.class);
        List<AddressDTO> addresses2 = TestUtil.parseJsonArrayResponse(result2, AddressDTO.class);
        assertEquals(addresses1,addresses2);
    }
    @Test
    @Transactional
    //if the user input is bad, the basic input willl be "1+Avenue"
    public void testBadInput() throws Exception{
        String badSubstring = "]]]]";
        String basicInput = "1+Avenue";
        MvcResult result1 = this.restMockMvc.perform(get("/api/address/getaddress/"+badSubstring))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult result2 = this.restMockMvc.perform(get("/api/address/getaddress/"+basicInput))
                .andExpect(status().isOk())
                .andReturn();

        List<AddressDTO> addresses1 = TestUtil.parseJsonArrayResponse(result1, AddressDTO.class);
        List<AddressDTO> addresses2 = TestUtil.parseJsonArrayResponse(result2, AddressDTO.class);
        assertEquals(addresses1,addresses2);
    }
}
