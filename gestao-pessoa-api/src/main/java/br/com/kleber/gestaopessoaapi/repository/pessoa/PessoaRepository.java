package br.com.kleber.gestaopessoaapi.repository.pessoa;

import br.com.kleber.gestaopessoaapi.model.pessoa.Pessoa;
import br.com.kleber.gestaopessoaapi.model.pessoa.QPessoa;
import br.com.kleber.gestaopessoaapi.repository.Abstract.AbstractRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaRepository extends AbstractRepository<Pessoa, Long> {

    public Optional<Pessoa> buscarPorCpf(String cpf) {
        JPAQuery<?> query = createJPAQuery();
        QPessoa qPessoa = QPessoa.pessoa;
        Pessoa pessoa = query.select(qPessoa)
                .from(qPessoa)
                .where(qPessoa.cpf.eq(cpf))
                .fetchOne();
        return Optional.ofNullable(pessoa);
    }

    public List<Pessoa> buscar(BooleanBuilder where, OrderSpecifier<?>... order) {
        JPAQuery<?> query = createJPAQuery();
        QPessoa qPessoa = QPessoa.pessoa;
        return query.select(qPessoa)
                .from(qPessoa)
                .where(where)
                .orderBy(order)
                .fetch();
    }

}
