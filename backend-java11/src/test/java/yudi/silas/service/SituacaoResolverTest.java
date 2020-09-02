package yudi.silas.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import yudi.silas.entity.Cupom;
import yudi.silas.entity.Situacao;
import yudi.silas.exception.DataInvalidaException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SituacaoResolverTest {

    @Test
    public void testExpirado() {
        Cupom cupom = new Cupom();
        cupom.setDataCadastro(Calendar.getInstance().getTime());
        cupom.setDataExpiracao(getDate("2020/01/01"));

        Situacao situacao = (new SituacaoResolver(cupom)).getSituacao();
        assertEquals(Situacao.EXPIRADO, situacao);
    }

    @Test
    public void testUsado() {
        Cupom cupom = new Cupom();
        cupom.setDataCadastro(Calendar.getInstance().getTime());
        cupom.setDataExpiracao(getDate("2020/01/01"));
        cupom.setDataUso(getDate("2020/01/01"));

        Situacao situacao = (new SituacaoResolver(cupom)).getSituacao();
        assertEquals(Situacao.USADO, situacao);
    }

    @Test
    public void testAtivo() {
        Cupom cupom = new Cupom();
        cupom.setDataCadastro(Calendar.getInstance().getTime());
        cupom.setDataExpiracao(Calendar.getInstance().getTime());

        Situacao situacao = (new SituacaoResolver(cupom)).getSituacao();
        assertEquals(Situacao.ATIVO, situacao);
    }

    @Test
    public void testDataInvalida() {
        Cupom cupom = new Cupom();
        cupom.setDataCadastro(Calendar.getInstance().getTime());
        cupom.setDataExpiracao(getDate("2020/01/01"));
        cupom.setDataUso(getDate("2020/01/02"));

        assertThrows(
                DataInvalidaException.class,
                () -> (new SituacaoResolver(cupom)).getSituacao()
        );
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
}
