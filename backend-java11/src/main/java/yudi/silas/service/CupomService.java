package yudi.silas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yudi.silas.entity.Cupom;
import yudi.silas.exception.CupomExisteException;
import yudi.silas.repository.CupomRepository;

import java.util.Optional;

@Service
public class CupomService {

    @Autowired
    private CupomRepository cupomRepository;

    public Iterable<Cupom> readAll() {
        return cupomRepository.findAll();
    }

    public Optional<Cupom> readById(Integer id) {
        return cupomRepository.findById(id);
    }

    public Optional<Cupom> readByCode(String codigo) {
        return cupomRepository.findByCodigo(codigo);
    }

    public Cupom save(Cupom cupom) {
        if (isExists(cupom)) {
            throw new CupomExisteException("Este código já está registrado. Por favor, informe outro.");
        }

        return cupomRepository.save(cupom);
    }

    public Cupom delete(Integer integer) {
        Optional<Cupom> cupomOptional = readById(integer);

        if (!cupomOptional.isPresent()) {
            return null;
        }

        cupomRepository.deleteById(integer);

        return cupomOptional.get();
    }

    private boolean isExists(Cupom newCupom) {
        Optional<Cupom> cupomOptional = readByCode(newCupom.getCodigo());

        return cupomOptional.isPresent()
                && cupomOptional.get().getId() != newCupom.getId();
    }
}
