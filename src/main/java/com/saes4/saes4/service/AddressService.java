package com.saes4.saes4.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saes4.saes4.model.dto.AddressDTO;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


@Service
@Transactional
public class AddressService {

    public List<AddressDTO> getAddressBySubstring(String substring){
        try{
            URL url = new URL("https://api-adresse.data.gouv.fr/search/?q=16+Ruelle");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");

            InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                    ? httpConn.getInputStream()
                    : httpConn.getErrorStream();
            Scanner s = new Scanner(responseStream).useDelimiter("\\A");
            String response = s.hasNext() ? s.next() : "";
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray jsonArray = jsonResponse.getJSONArray("features");

            return null;
        }
        catch(Exception e){
            return null;
        }
    }
}
