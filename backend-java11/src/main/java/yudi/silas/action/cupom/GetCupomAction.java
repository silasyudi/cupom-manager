package yudi.silas.action.cupom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import yudi.silas.entity.Cupom;
import yudi.silas.service.CupomService;

import java.util.Optional;

@RestController
public class GetCupomAction {

    @Autowired
    private CupomService cupomService;

    @GetMapping("cupons/{id}")
    public ResponseEntity<Cupom> action(@PathVariable("id") Integer id) {
        Optional<Cupom> cupomOptional = cupomService.readById(id);

        return cupomOptional.isPresent()
                ? new ResponseEntity<>(cupomOptional.get(), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
