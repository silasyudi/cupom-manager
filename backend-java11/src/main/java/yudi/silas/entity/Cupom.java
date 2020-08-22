package yudi.silas.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Cupom")
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

    @NotNull(message = "A data de cadastro é obrigatória.")
    private Date dataCadastro;

    @NotNull(message = "A data de expiração é obrigatória.")
    private Date dataExpiracao;

    @PastOrPresent(message = "A data de uso não pode ser uma data futura.")
    private Date dataUso;

    @Min(value = 1, message = "O valor deve ser a partir de R$ 1,00.")
    private Float valor;

    @NotNull(message = "A situação é obrigatória.")
    @Enumerated(EnumType.STRING)
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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
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
