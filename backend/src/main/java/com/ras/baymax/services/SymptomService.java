package com.ras.baymax.services;

import com.ras.baymax.entities.Symptom;
import com.ras.baymax.repositories.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    public Map<String, List<String>> getSymptoms(List<Symptom> symptoms) {
        List<String> symptomList = new ArrayList<String>();
        Map<String, List<String>> symptomMap = new HashMap<String, List<String>>();
        symptoms.forEach(symptom -> symptomList.add(symptom.getEn()));
        symptomMap.put("symptons", symptomList);
        return symptomMap;
    }

    public Symptom save(Symptom symptom) {
        return symptomRepository.save(symptom);
    }
}
