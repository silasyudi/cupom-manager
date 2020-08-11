package yudi.silas.action.cupom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yudi.silas.entity.Cupom;
import yudi.silas.service.CupomService;

@RestController
public class GetAllCupomAction {

    @Autowired
    private CupomService cupomService;

    @GetMapping("cupons")
    public ResponseEntity<Iterable<Cupom>> action() {
        Iterable<Cupom> listas = cupomService.readAll();
        return new ResponseEntity<>(listas, HttpStatus.OK);
    }
}
