package mercado.alves.api.controller;

import jakarta.validation.Valid;
import mercado.alves.api.cliente.Cliente;
import mercado.alves.api.cliente.DadosCadastroCliente;
import mercado.alves.api.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("usuarios")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "http://127.0.0.1:5500"})
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, String>> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        repository.save(new Usuario(dados));
        Map<String, String> response = new HashMap<>();
        response.put("status", "itemCadastrado");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public Page<DadosDoUsuario> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDoUsuario::new);

    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid DadosCadastroUsuario dados) {
        try {
            var usuario = repository.getReferenceById(dados.id());
            usuario.atualizarInformacoes(dados);

            return ResponseEntity.ok("{\"message\": \"modificado\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred\"}");
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok("{\"message\": \"itemApagado\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Erro ao apagar o item\"}");
        }
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
