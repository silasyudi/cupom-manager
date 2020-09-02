package yudi.silas.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yudi.silas.entity.Cupom;
import yudi.silas.entity.Situacao;
import yudi.silas.exception.CupomExisteException;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CupomServiceTest {

    @Autowired
    private CupomService cupomService;

    @AfterEach
    public void tearDown() {
        Iterator<Cupom> iterator = cupomService.readAll().iterator();

        while (iterator.hasNext()) {
            cupomService.delete(iterator.next().getId());
        }
    }

    @Test
    public void testAdicionaCupom() {
        saveCupom("A1B2C3", "Descricao", 1f, "2020/01/01", null);
    }

    @Test
    public void testAdicionaMaisDeUmCupom() {
        saveCupom("A1B2C3", "Descricao", 1f, "2020/01/01", null);
        saveCupom("A4B5C6", "Descricao", 1f, "2020/01/01", null);
    }

    @Test
    public void testAdicionaCupomJaExistente() {
        saveCupom("A1B2C3", "Descricao", 1f, "2020/01/01", null);

        assertThrows(
                CupomExisteException.class,
                () -> saveCupom("A1B2C3", "Descricao", 1f, "2020/01/01", null)
        );
    }

    @Test
    public void testUpdateCupom() {
        Cupom cupom = saveCupom("A1B2C3", "Descricao", 1f, "2020/01/01", null);
        cupom.setCodigo("A4B5C6");
        cupom = cupomService.save(cupom);
        assertCupomSalvo(cupom, "A4B5C6");

        cupom.setDescricao("Outra descricao");
        cupom = cupomService.save(cupom);
        assertCupomSalvo(cupom, "A4B5C6");
    }

    @Test
    public void testDeleteCupom() {
        Cupom cupom = saveCupom("A1B2C3", "Descricao", 1f, "2020/01/01", null);
        cupomService.delete(cupom.getId());
        assertCupomNaoSalvo(cupom);
    }

    @Test
    public void testDeleteCupomInexistente() {
        Cupom cupom = cupomService.delete(1);
        assertNull(cupom);
    }

    @Test
    public void testGetAll() {
        assertEquals(0, getAll().size());

        Cupom cupom = saveCupom("A1B2C3", "Descricao", 1f, "2020/01/01", null);
        assertEquals(1, getAll().size());

        cupom.setCodigo("A4B5C6");
        cupomService.save(cupom);
        assertEquals(1, getAll().size());

        saveCupom("D4E5F6", "Descricao", 1f, "2020/01/01", null);
        assertEquals(2, getAll().size());
    }

    @Test
    public void testGetByParams() {
        Cupom cupom1 = saveCupom("A1B2C3", "Expirado", 1f, "2020/01/01", null);
        Cupom cupom2 = saveCupom("D4E5F6", "Usado", 1f, "2020/02/01", "2020/02/01");
        Cupom cupom3 = saveCupom("G7H8I9", "Ativo", 1f, "2030/02/01", null);
        assertEquals(3, getAll().size());

        List<Cupom> expirados = (List<Cupom>) cupomService.readByParams(Situacao.EXPIRADO, null, null);
        assertEquals(1, expirados.size());
        assertTrue(expirados.get(0).equals(cupom1));

        List<Cupom> usados = (List<Cupom>) cupomService.readByParams(Situacao.USADO, null, null);
        assertEquals(1, usados.size());
        assertTrue(usados.get(0).equals(cupom2));

        List<Cupom> ativos = (List<Cupom>) cupomService.readByParams(Situacao.ATIVO, null, null);
        assertEquals(1, ativos.size());
        assertTrue(ativos.get(0).equals(cupom3));

        List<Cupom> apenasInicial = (List<Cupom>) cupomService.readByParams(null, getDate("2020/02/02"), null);
        assertEquals(1, apenasInicial.size());
        assertTrue(apenasInicial.get(0).equals(cupom3));

        List<Cupom> apenasFinal = (List<Cupom>) cupomService.readByParams(null, null, getDate("2020/01/01"));
        assertEquals(1, apenasFinal.size());
        assertTrue(apenasFinal.get(0).equals(cupom1));

        List<Cupom> inicialFinal = (List<Cupom>) cupomService.readByParams(null, getDate("2020/01/02"), getDate("2030/01/31"));
        assertEquals(1, inicialFinal.size());
        assertTrue(inicialFinal.get(0).equals(cupom2));
    }

    private Date getDate(String date) {
        Date formattedDate = null;

        try {
            if (date != null) {
                formattedDate = (new SimpleDateFormat("yyyy/MM/dd")).parse(date);
            }
        } catch (Exception exception) {
            fail();
        }

        return formattedDate;
    }

    private Cupom saveCupom(
            String codigo,
            String descricao,
            Float valor,
            String expiracao,
            String uso
    ) {
        Date now = Calendar.getInstance().getTime();

        Cupom cupom = new Cupom();
        cupom.setCodigo(codigo);
        cupom.setDescricao(descricao);
        cupom.setValor(valor);
        cupom.setDataCadastro(now);
        cupom.setDataExpiracao(getDate(expiracao));
        cupom.setDataUso(getDate(uso));

        cupom = cupomService.save(cupom);

        assertCupomSalvo(cupom, codigo);

        return cupom;
    }

    private void assertCupomSalvo(Cupom cupom, String codigo) {
        Optional<Cupom> cupomOptional = cupomService.readByCode(codigo);

        assertTrue(cupomOptional.isPresent());
        assertTrue(cupomOptional.get().equals(cupom));
        assertNotNull(cupom.getId());
        assertEquals(codigo, cupom.getCodigo());
    }

    private void assertCupomNaoSalvo(Cupom cupom) {
        Optional<Cupom> cupomOptional = cupomService.readByCode(cupom.getCodigo());
        assertFalse(cupomOptional.isPresent());
    }

    private List<Cupom> getAll() {
        return (List<Cupom>) cupomService.readAll();
    }
}
