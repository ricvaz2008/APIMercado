package mercado.alves.api.controller;

import jakarta.validation.Valid;
import mercado.alves.api.estoque.DadosCadastroEstoque;
import mercado.alves.api.estoque.DadosDoEstoque;
import mercado.alves.api.estoque.Estoque;
import mercado.alves.api.estoque.EstoqueRepository;
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
@RequestMapping("estoque")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "http://127.0.0.1:5500"})
public class EstoqueController {

    @Autowired
    private EstoqueRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, String>> cadastrar(@RequestBody @Valid DadosCadastroEstoque dados) {
        repository.save(new Estoque(dados));
        Map<String, String> response = new HashMap<>();
        response.put("status", "itemCadastrado");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public Page<DadosDoEstoque> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDoEstoque::new);

    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid DadosCadastroEstoque dados) {
        try {
            var estoque = repository.getReferenceById(dados.codigo());
            estoque.atualizarInformacoes(dados);

            return ResponseEntity.ok("{\"message\": \"modificado\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred\"}");
        }
    }

    @DeleteMapping("/{codigo}")
    @Transactional
    public ResponseEntity<String> excluir(@PathVariable String codigo) {
        try {
            repository.deleteById(codigo);
            return ResponseEntity.ok("{\"message\": \"itemApagado\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Erro ao apagar o item\"}");
        }
    }

    @GetMapping("/localiza-estoque")
    public Estoque findEstoqueById(@RequestParam String codigo) {
        return repository.findByCodigo(codigo).orElse(null);
    }
}

