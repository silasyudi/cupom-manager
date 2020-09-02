package yudi.silas.action.cupom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import yudi.silas.entity.Cupom;
import yudi.silas.service.CupomService;

@RestController
public class DeleteCupomAction {

    @Autowired
    private CupomService cupomService;

    @DeleteMapping("cupons/{id}")
    public ResponseEntity<Cupom> action(@PathVariable Integer id) {
        Cupom cupom = cupomService.delete(id);
        return new ResponseEntity<>(cupom, HttpStatus.OK);
    }
}
