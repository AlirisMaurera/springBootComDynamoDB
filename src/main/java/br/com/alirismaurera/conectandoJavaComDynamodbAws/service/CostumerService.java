package br.com.alirismaurera.conectandoJavaComDynamodbAws.service;

import br.com.alirismaurera.conectandoJavaComDynamodbAws.dto.CostumerDTO;
import br.com.alirismaurera.conectandoJavaComDynamodbAws.entity.Costumer;

import java.util.List;

public interface CostumerService {

    Costumer saveCostumer(CostumerDTO costumerDTO);
    List<Costumer> findAllCostumers();
    List<Costumer> findByCompanyName(String companyName);
    Costumer updateCostumer(String companyDocumentNumber, CostumerDTO costumerDTO);
    Costumer disableCostumer(String companyDocumentNumber);
}
