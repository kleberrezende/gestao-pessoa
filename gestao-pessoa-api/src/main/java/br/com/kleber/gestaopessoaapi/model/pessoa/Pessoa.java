package br.com.kleber.gestaopessoaapi.model.pessoa;

import br.com.kleber.gestaopessoaapi.model.model.ModelAbs;
import br.com.kleber.gestaopessoaapi.model.model.ModelImpl;
import br.com.kleber.gestaopessoaapi.type.SexoType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = {"id"})
@Entity
@Table(name = "pessoa", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cpf"})
})
public class Pessoa extends ModelAbs implements ModelImpl<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo Nome é obrigatório.")
    @Size(min = 3, max = 60, message = "Campo Nome deve ter entre {min} e {max} caracteres.")
    @Column(name = "nome", length = 60, nullable = false)
    private String nome;

    @Column(name = "sexo", length = 15)
    @Enumerated(EnumType.STRING)
    private SexoType sexo;

    @Size(min = 5, max = 100, message = "Campo Nome deve ter entre {min} e {max} caracteres.")
    @Column(name = "email", length = 100)
    @Email(message = "Formato de e-mail inválido.")
    private String email;

    @NotNull(message = "Campo data nascimento é obrigatório.")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "naturalidade", length = 50)
    private String naturalidade;

    @Column(name = "nacionalidade", length = 50)
    private String nacionalidade;

    @NotNull(message = "Campo CPF é obrigatório.")
    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @NotNull(message = "Campo data cadastro é obrigatório.")
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @NotNull(message = "Campo data atualização é obrigatório.")
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Override
    public Long getId() {
        return id;
    }

}
