package br.com.kleber.gestaopessoaapi.mapper.pessoa;

import br.com.kleber.gestaopessoaapi.model.pessoa.Pessoa;
import br.com.kleber.gestaopessoaapi.viewmodel.pessoa.PessoaVM;
import java.util.List;
import java.util.stream.Collectors;

public final class PessoaMapper {

    public static Pessoa fromVM(PessoaVM pessoaVM) {
        if (pessoaVM == null) {
            return null;
        }
        final Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaVM.getId());
        pessoa.setNome(pessoaVM.getNome());
        pessoa.setSexo(pessoaVM.getSexo());
        pessoa.setEmail(pessoaVM.getEmail());
        pessoa.setDataNascimento(pessoaVM.getDataNascimento());
        pessoa.setNaturalidade(pessoaVM.getNaturalidade());
        pessoa.setNacionalidade(pessoaVM.getNacionalidade());
        pessoa.setCpf(pessoaVM.getCpf());
        pessoa.setDataCadastro(pessoaVM.getDataCadastro());
        pessoa.setDataAtualizacao(pessoaVM.getDataAtualizacao());
        return pessoa;
    }

    public static List<Pessoa> fromVM(List<PessoaVM> pessoaVMs) {
        return pessoaVMs.stream().map(PessoaMapper::fromVM).collect(Collectors.toList());
    }

    public static PessoaVM toVM(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }
        final PessoaVM pessoaVM = new PessoaVM();
        pessoaVM.setId(pessoa.getId());
        pessoaVM.setNome(pessoa.getNome());
        pessoaVM.setSexo(pessoa.getSexo());
        pessoaVM.setEmail(pessoa.getEmail());
        pessoaVM.setDataNascimento(pessoa.getDataNascimento());
        pessoaVM.setNaturalidade(pessoa.getNaturalidade());
        pessoaVM.setNacionalidade(pessoa.getNacionalidade());
        pessoaVM.setCpf(pessoa.getCpf());
        pessoaVM.setDataCadastro(pessoa.getDataCadastro());
        pessoaVM.setDataAtualizacao(pessoa.getDataAtualizacao());
        return pessoaVM;
    }

    public static List<PessoaVM> toVM(List<Pessoa> pessoas) {
        return pessoas.stream().map(PessoaMapper::toVM).collect(Collectors.toList());
    }

}
