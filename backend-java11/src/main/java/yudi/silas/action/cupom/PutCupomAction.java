package yudi.silas.action.cupom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yudi.silas.entity.Cupom;
import yudi.silas.service.CupomService;

import javax.validation.Valid;

@RestController
public class PutCupomAction {

    @Autowired
    private CupomService cupomService;

    @PutMapping(value = "cupons", consumes = "application/json")
    public ResponseEntity<Cupom> action(@RequestBody @Valid Cupom cupom) {
        cupom = cupomService.save(cupom);
        return new ResponseEntity<>(cupom, HttpStatus.CREATED);
    }
}
