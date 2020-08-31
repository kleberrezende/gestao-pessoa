package br.com.kleber.gestaopessoaapi.controller.home;

import br.com.kleber.gestaopessoaapi.viewmodel.mensagem.MensagemVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public ResponseEntity home() {
        return ResponseEntity.ok(new MensagemVM("Projeto Gestão de Pessoas working."));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/source")
    public ResponseEntity source() {
        return ResponseEntity.ok(new MensagemVM("https://kleber.git"));
    }

}
