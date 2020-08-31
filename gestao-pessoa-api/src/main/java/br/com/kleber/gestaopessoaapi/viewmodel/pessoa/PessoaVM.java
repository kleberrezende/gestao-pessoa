package br.com.kleber.gestaopessoaapi.viewmodel.pessoa;

import br.com.kleber.gestaopessoaapi.type.SexoType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PessoaVM {

    private Long id;

    private String nome;

    private SexoType sexo;

    private String email;

    private LocalDate dataNascimento;

    private String naturalidade;

    private String nacionalidade;

    private String cpf;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

}
