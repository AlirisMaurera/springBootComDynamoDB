package br.com.alirismaurera.conectandoJavaComDynamodbAws.controller;

import br.com.alirismaurera.conectandoJavaComDynamodbAws.dto.CostumerDTO;
import br.com.alirismaurera.conectandoJavaComDynamodbAws.entity.Costumer;
import br.com.alirismaurera.conectandoJavaComDynamodbAws.service.CostumerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class AppController {

    @Autowired
    private final CostumerService costumerService;

    @PostMapping("/costumer")
    public ResponseEntity<Costumer> newCostumer(@Valid @RequestBody CostumerDTO costumerDTO) {
        return new ResponseEntity(costumerService.saveCostumer(costumerDTO), HttpStatus.OK);
    }

    @GetMapping("/costumer")
    public ResponseEntity<List<Costumer>> findCostumerByName(@Param("companyName") String companyName) {
        return ResponseEntity.ok(costumerService.findByCompanyName(companyName));
    }

    @GetMapping("/costumer/all")
    public ResponseEntity<List<Costumer>> allCostumers() {
        return ResponseEntity.ok(costumerService.findAllCostumers());
    }

    @PutMapping("/costumer/{companyDocumentNumber}")
    public ResponseEntity<Costumer> updateCostumer(@PathVariable("companyDocumentNumber") String companyDocumentNumber,@RequestBody @Valid CostumerDTO costumerDTO) {
        return ResponseEntity.ok(costumerService.updateCostumer(companyDocumentNumber,costumerDTO));
    }

    @DeleteMapping("/costumer/{companyName}")
    public ResponseEntity<Costumer> disableCostumer(@PathVariable("companyName") String companyName) {
        return ResponseEntity.ok(costumerService.disableCostumer(companyName));
    }
}
