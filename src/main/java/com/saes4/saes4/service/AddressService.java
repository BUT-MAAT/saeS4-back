package com.saes4.saes4.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saes4.saes4.model.dto.AddressDTO;
import com.saes4.saes4.model.dto.FeatureCollectionDTO;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


@Service
@Transactional
public class AddressService {
    private String basicSubstring = "1+Avenue";

    public List<AddressDTO> getAddressBySubstring(String substring){
        if(substring.replaceAll("\\+","").length()<3){
            String add = substring.substring(substring.length() -1).equals("+") ? "avenue" : "+avenue";
            substring += add;

        }

        try{
            URL url = new URL("https://api-adresse.data.gouv.fr/search/?q="+substring);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");

            InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                    ? httpConn.getInputStream()
                    : httpConn.getErrorStream();
            Scanner s = new Scanner(responseStream,"UTF-8").useDelimiter("\\A");
            String response = s.hasNext() ? s.next() : "";
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray jsonArray = jsonResponse.getJSONArray("features");
            ObjectMapper objectMapper = new ObjectMapper();
            String test = jsonArray.toString();
            List<FeatureCollectionDTO> featureDtoList = objectMapper.readValue(test, new TypeReference<List<FeatureCollectionDTO>>(){});
            List<AddressDTO> addressDTOS = new ArrayList<>();
            for(FeatureCollectionDTO feature : featureDtoList)
                addressDTOS.add(feature.getProperties());
            return addressDTOS;
        }
        catch (JSONException e){
            if(basicSubstring.equals(substring))
                return null;
            return getAddressBySubstring(basicSubstring);
        }
        catch(Exception e){
            return null;
        }
    }
}
