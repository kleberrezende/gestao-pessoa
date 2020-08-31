package br.com.kleber.gestaopessoaapi.viewmodel.exception;

public final class ExceptionVM {

    private final String erro;

    public ExceptionVM(String error) {
        this.erro = error;
    }

    public String getErro() {
        return erro;
    }

}
