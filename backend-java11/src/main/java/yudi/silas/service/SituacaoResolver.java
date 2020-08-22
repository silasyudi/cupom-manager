package yudi.silas.service;

import yudi.silas.entity.Cupom;
import yudi.silas.entity.Situacao;
import yudi.silas.exception.DataInvalidaException;

import java.util.Calendar;
import java.util.Date;

public class SituacaoResolver {

    private Date uso;
    private Date expiracao;
    private Date cadastro;

    public SituacaoResolver(Cupom cupom) {
        this.uso = cupom.getDataUso() != null ? formataSemHora(cupom.getDataUso()) : null;
        this.expiracao = formataSemHora(cupom.getDataExpiracao());
        this.cadastro = formataSemHora(cupom.getDataCadastro());
    }

    public Situacao getSituacao() {
        checkDatasInvalidas();

        if (isExpirado()) {
            return Situacao.EXPIRADO;
        }

        if (isUsado()) {
            return Situacao.USADO;
        }

        return Situacao.ATIVO;
    }

    private void checkDatasInvalidas() {
        if (uso == null) {
            return;
        }

        if (uso.compareTo(expiracao) > 0) {
            throw new DataInvalidaException("A data de uso não pode ser posterior à data de expiração.");
        }
    }

    private boolean isExpirado() {
        return expiracao.compareTo(cadastro) < 0 && uso == null;
    }

    private boolean isUsado() {
        return uso != null;
    }

    private Date formataSemHora(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
