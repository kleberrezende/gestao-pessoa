package br.com.kleber.gestaopessoaapi.service.pessoa;

import br.com.kleber.gestaopessoaapi.exception.BusinessException;
import br.com.kleber.gestaopessoaapi.model.pessoa.Pessoa;
import br.com.kleber.gestaopessoaapi.model.pessoa.QPessoa;
import br.com.kleber.gestaopessoaapi.repository.pessoa.PessoaRepository;
import br.com.kleber.gestaopessoaapi.util.DateTimeUtils;
import br.com.kleber.gestaopessoaapi.util.StringUtils;
import br.com.kleber.gestaopessoaapi.util.ValidarCpfCnpj;
import com.querydsl.core.BooleanBuilder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoa) {
        validar(pessoa);
        if (pessoa.getId() == null) {
            pessoa.setDataCadastro(DateTimeUtils.getLocalDateTimeNow());
        }
        pessoa.setDataAtualizacao(DateTimeUtils.getLocalDateTimeNow());
        pessoaRepository.save(pessoa);
        return pessoa;
    }

    private void validar(Pessoa pessoa) {
        Assert.isTrue(pessoa != null, "Objeto pessoa não pode ser nulo.");
        Assert.isTrue(!StringUtils.isStringEmpty(pessoa.getNome()), "Campo nome é obrigatório.");
        Assert.isTrue(pessoa.getEmail() == null
                || StringUtils.isValidEmail(pessoa.getEmail()), "Campo e-mail está incorreto.");
        Assert.isTrue(pessoa.getDataNascimento() != null
                && pessoa.getDataNascimento().isBefore(DateTimeUtils.getLocalDateNow()),
                "Campo data de nascimento está incorreto.");
        Assert.isTrue(ValidarCpfCnpj.isCpfCnpj(pessoa.getCpf()), "Campo CPF está incorreto.");

        Pessoa pessoaR = buscarPorCpf(pessoa.getCpf());
        Assert.isTrue(pessoaR == null
                || pessoaR.equals(pessoa), "Já existe uma pessoa cadastrada com o mesmo CPF.");
    }

    public void remover(Pessoa pessoa) {
        pessoaRepository.remove(pessoa);
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa buscarPorCpf(String cpf) {
        cpf = StringUtils.trim(cpf);
        return pessoaRepository.buscarPorCpf(cpf)
                .map(obj -> {
                    return obj;
                })
                .orElse(null);
    }

    public List<Pessoa> buscar(String nome, String cpf) {
        nome = StringUtils.trim(nome);
        cpf = StringUtils.trim(cpf);
        BooleanBuilder where = new BooleanBuilder();
        QPessoa qPessoa = QPessoa.pessoa;

        if (!StringUtils.isStringEmpty(nome)) {
            where.and(qPessoa.nome.startsWith(nome));
        }

        if (!StringUtils.isStringEmpty(cpf)) {
            where.and(qPessoa.cpf.startsWith(cpf));
        }

        if (where.hasValue()) {
            return pessoaRepository.buscar(where,
                    qPessoa.nome.asc(),
                    qPessoa.cpf.asc());
        } else {
            throw new BusinessException("Parâmetros insuficiêntes para busca de Pessoa.");
        }
    }

}
