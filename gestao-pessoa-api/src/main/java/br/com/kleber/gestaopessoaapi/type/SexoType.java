package br.com.kleber.gestaopessoaapi.type;

public enum SexoType {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    public final String descricao;

    SexoType(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
