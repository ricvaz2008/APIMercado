package mercado.alves.api.controller;

import jakarta.validation.Valid;
import mercado.alves.api.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("usuarios")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "https://127.0.0.1:5000"})
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        System.out.println(dados);
        repository.save(new Usuario(dados));
    }

    @GetMapping
    public Page<DadosDoUsuario> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDoUsuario::new);

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosCadastroUsuario dados) {
        var funcionario = repository.getReferenceById(dados.id());
        funcionario.atualizarInformacoes(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/verifica-acesso")
    public ResponseEntity<DadoDeAcesso> verificaAcesso(
            @RequestParam("login") String login,
            @RequestParam("senha") String senha) {

        System.out.println(login);
        System.out.println(senha);
        DadoDeAcesso acesso = repository.findAcessoByLoginAndSenha(login, senha);

        if (acesso != null) {
            return ResponseEntity.ok(acesso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/localiza-usuario")
    public Usuario findUsuarioById(@RequestParam Long id) {
        return repository.findById(id).orElse(null);
    }
}
