package yudi.silas.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Lista")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"codigo"})})
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "O código é obrigatório.")
    @Pattern(
            regexp = "^([0-9]*[A-Z]+|[A-Z]+[0-9]*)+[A-Z0-9]*$",
            message = "O código deve conter apenas letras e números, e deve conter pelo menos uma letra."
    )
    private String codigo;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "A data de expiração é obrigatória.")
    private Date dataExpiracao;

    @PastOrPresent(message = "A data de uso não pode ser uma data futura.")
    private Date dataUso;

    @NotNull(message = "A situação é obrigatória.")
    private Situacao situacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Date getDataUso() {
        return dataUso;
    }

    public void setDataUso(Date dataUso) {
        this.dataUso = dataUso;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cupom cupom = (Cupom) o;
        return codigo.equals(cupom.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
