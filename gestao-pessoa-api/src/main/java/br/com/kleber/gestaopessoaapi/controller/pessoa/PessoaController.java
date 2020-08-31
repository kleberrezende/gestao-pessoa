package br.com.kleber.gestaopessoaapi.controller.pessoa;

import br.com.kleber.gestaopessoaapi.mapper.pessoa.PessoaMapper;
import br.com.kleber.gestaopessoaapi.service.pessoa.PessoaService;
import br.com.kleber.gestaopessoaapi.viewmodel.mensagem.MensagemVM;
import br.com.kleber.gestaopessoaapi.viewmodel.pessoa.PessoaVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @RequestMapping(method = RequestMethod.POST, value = "/salvar")
    public ResponseEntity salvar(@RequestBody PessoaVM pessoaVM) {
        return ResponseEntity.ok(PessoaMapper.toVM(pessoaService.salvar(PessoaMapper.fromVM(pessoaVM))));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/remover")
    public ResponseEntity remover(@RequestBody PessoaVM pessoaVM) {
        pessoaService.remover(PessoaMapper.fromVM(pessoaVM));
        return ResponseEntity.ok(new MensagemVM("Pessoa removida com sucesso."));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/buscarPorId")
    public ResponseEntity buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(PessoaMapper.toVM(pessoaService.buscarPorId(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/buscar")
    public ResponseEntity buscar(@RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf) {
        return ResponseEntity.ok(PessoaMapper.toVM(pessoaService.buscar(nome, cpf)));
    }

}
