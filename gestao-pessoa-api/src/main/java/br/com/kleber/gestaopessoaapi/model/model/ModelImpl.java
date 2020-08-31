package br.com.kleber.gestaopessoaapi.model.model;

import java.io.Serializable;

public interface ModelImpl<ID extends Serializable> extends Serializable {

    ID getId();

}
