package yudi.silas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yudi.silas.entity.Cupom;
import yudi.silas.entity.Situacao;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CupomRepository extends CrudRepository<Cupom, Integer> {

    Optional<Cupom> findByCodigo(String codigo);

    @Query(
            "SELECT c FROM Cupom c "
                    + " WHERE (:situacao IS NULL OR c.situacao = :situacao) "
                    + " AND (:dataInicio IS NULL OR c.dataExpiracao >= :dataInicio) "
                    + " AND (:dataFim IS NULL OR c.dataExpiracao >= :dataFim) "
    )
    Iterable<Cupom> findBySituacaoAndIntervaloDataExpiracao(
            Situacao situacao,
            Date dataInicio,
            Date dataFim
    );
}
