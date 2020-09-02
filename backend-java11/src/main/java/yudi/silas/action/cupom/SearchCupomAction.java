package yudi.silas.action.cupom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yudi.silas.entity.Cupom;
import yudi.silas.entity.Situacao;
import yudi.silas.service.CupomService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SearchCupomAction {

    @Autowired
    private CupomService cupomService;

    @GetMapping("cupons/pesquisa-avancada")
    public ResponseEntity<Iterable<Cupom>> action(
            @RequestParam(required = false) String situacao,
            @RequestParam(required = false) String dataInicial,
            @RequestParam(required = false) String dataFinal
    ) throws ParseException {
        Iterable<Cupom> listas = cupomService.readByParams(
                parseSituacao(situacao),
                parseDate(dataInicial),
                parseDate(dataFinal)
        );
        return new ResponseEntity<>(listas, HttpStatus.OK);
    }

    private Situacao parseSituacao(String situacao) {
        if (situacao == null || situacao.isEmpty()) {
            return null;
        }

        return Situacao.valueOf(situacao);
    }

    private Date parseDate(String date) throws ParseException {
        if (date == null || date.isEmpty()) {
            return null;
        }

        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }
}
