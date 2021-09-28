package br.com.alirismaurera.conectandoJavaComDynamodbAws.repository;

import br.com.alirismaurera.conectandoJavaComDynamodbAws.entity.Costumer;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableScan
@Repository
public interface CostumerRepository extends CrudRepository<Costumer, String> {

    @Query
    List<Costumer> findByCompanyName(String companyName);
    @Query
    Optional<Costumer> findByCompanyDocumentNumber(String companyDocumentNumber);
}
