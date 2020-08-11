package yudi.silas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yudi.silas.entity.Cupom;

import java.util.Optional;

@Repository
public interface CupomRepository extends CrudRepository<Cupom, Integer> {

    Optional<Cupom> findByCodigo(String codigo);
}
