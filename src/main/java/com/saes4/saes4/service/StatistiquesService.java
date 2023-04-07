package com.saes4.saes4.service;

import com.saes4.saes4.mapper.AlimentMapper;
import com.saes4.saes4.mapper.CategorieMapper;
import com.saes4.saes4.mapper.StatistiquesMapper;
import com.saes4.saes4.model.dto.CategorieDTO;
import com.saes4.saes4.model.dto.StatistiquesDTO;
import com.saes4.saes4.model.dto.statistiques.AlimentCountDTO;
import com.saes4.saes4.model.dto.statistiques.AlimentStatistiquesDTO;
import com.saes4.saes4.model.dto.statistiques.CategorieStatistiqueDTO;
import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.Categorie;
import com.saes4.saes4.model.entities.Sondage;
import com.saes4.saes4.model.entities.Statistiques;
import com.saes4.saes4.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.AttributeException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class StatistiquesService {

    @Autowired
    AlimentMapper alimentMapper;

    @Autowired
    CategorieMapper categorieMapper;

    @Autowired
    AlimentRepository alimentRepository;

    @Autowired
    CategorieRepository categorieRepository;

    @Autowired
    LiaisonAlimentSondageRepository liaisonAlimentSondageRepository;

    @Autowired
    private StatistiquesRepository statistiquesRepository;

    @Autowired
    StatistiquesMapper statistiquesMapper;

    @Autowired
    SondageRepository sondageRepository;

    public StatistiquesDTO getLastStatistiques() {
        Statistiques statistiques = statistiquesRepository.findTopByOrderByTimelogDesc();
        StatistiquesDTO statistiquesDTO;
        if (statistiques == null) {
            statistiquesDTO = new StatistiquesDTO();
            statistiquesDTO.setNombre_reponses((long) 0);
            return statistiquesDTO;
        }
        statistiquesDTO = statistiquesMapper.complementStatistiquesGeneralesToStatistiquesGeneralesDTO(
                statistiques,
                statistiquesMapper.statistiquesGeneralesToStatistiquesGeneralesDTO(statistiques)
        );

        // On s'occupe à present des top 10 des Aliments
        // Recuperer le classement des 10 premiers par les id
        List<Aliment> top10Aliments = new ArrayList<>();
        liaisonAlimentSondageRepository.get10MostSelectedAlimentsId()
                .stream()
                .forEach(id -> top10Aliments.add(alimentRepository.findById(id).get()));
        // On defini dans les statistiques le classement des 10 premiers aliments
        statistiquesDTO.setAliments_top_10(alimentMapper.alimentToAlimentStatistiquesDTOList(top10Aliments));
        // Recuperer le nombre de selection des 10 premiers (triés, synchronisé avec resultat precedent)
        List<Long> top10AlimentsCount =  liaisonAlimentSondageRepository.get10MostSelectedAlimentsCount();
        // On affecte le nombre de selections aux aliments les plus choisis et on leur donne leur rang
        for(int i = 0; i < statistiquesDTO.getAliments_top_10().size(); i++) {
            AlimentStatistiquesDTO aliment = statistiquesDTO.getAliments_top_10().get(i);
            aliment.setNb_selections(top10AlimentsCount.get(i));
            aliment.setRank(i + 1);
        }


        // On s'occupe à present du classement des categories
        // Recuperer le classement des categories par les id
        List<Categorie> categoriesSorted = new ArrayList<>();
        liaisonAlimentSondageRepository.getSortedCategoriesId()
                .stream()
                .forEach(id -> categoriesSorted.add(categorieRepository.findById(id).get()));
        // On defini dans les statistiques le classement des categories
        statistiquesDTO.setCategories_triees(categorieMapper.categorieToCategorieStatistiquesDTOList(categoriesSorted));
        // Recuperer le nombre de selection des categories (triés, synchronisé avec resultat precedent)
        List<Long> sortedCategoriesCount =  liaisonAlimentSondageRepository.getSortedCategorieCount();
        // On affecte le nombre de selections aux categories et on leur donne leur rang
        for(int i = 0; i < statistiquesDTO.getCategories_triees().size(); i++) {
            CategorieStatistiqueDTO categorie = statistiquesDTO.getCategories_triees().get(i);
            categorie.setNb_selections(sortedCategoriesCount.get(i));
            categorie.setRank(i + 1);
        }

        return statistiquesDTO;
    }
    public List<AlimentCountDTO> getMostConsumedAlimentsByDepartment(String department,Long size) {
        final String pattern = "([0-8]{1}[0-9]{1})|([9]{1}[0-5]{1})|(97[1-46])";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(department);
        if(!m.matches())
            return new LinkedList<>();
        List<AlimentCountDTO> count = new LinkedList<>();
        List<Sondage> sondages = sondageRepository.findAll();
        sondages = sondages.stream()
                .filter(sondage -> sondage.getCode_postal().substring(0,department.length()).equals((department)))
                .toList();


        Iterator<AlimentCountDTO> it;
        AlimentCountDTO alimentCountDTO;
        boolean found = false;
        for(Sondage sondage : sondages){
            for(Aliment aliment : sondage.getListe_aliments()){
                it = count.iterator();
                while(it.hasNext()){
                    alimentCountDTO = it.next();
                    if(alimentCountDTO.getId_aliment().equals(aliment.getId_aliment())) {
                        alimentCountDTO.setNbChoisi((Long) (alimentCountDTO.getNbChoisi() + 1));
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    alimentCountDTO = alimentMapper.alimentToAlimentCountDTONoValeursNutritives(aliment);
                    alimentCountDTO.setNbChoisi((long) 1);
                    count.add(alimentCountDTO);
                }
                found = false;
            }
        }

        Collections.sort(count);
        if(size == null)
            return count;
        List<AlimentCountDTO> result = count.size() < size.intValue() ? count : count.subList(0,size.intValue());
        return result;
    }
}
