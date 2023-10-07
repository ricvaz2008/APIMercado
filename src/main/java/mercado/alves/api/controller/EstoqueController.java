package mercado.alves.api.controller;

import jakarta.validation.Valid;
import mercado.alves.api.estoque.DadosCadastroEstoque;
import mercado.alves.api.estoque.DadosDoEstoque;
import mercado.alves.api.estoque.Estoque;
import mercado.alves.api.estoque.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("estoque")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "https://127.0.0.1:5000"})
public class EstoqueController {

    @Autowired
    private EstoqueRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroEstoque dados) {
        repository.save(new Estoque(dados));
    }

    @GetMapping
    public Page<DadosDoEstoque> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDoEstoque::new);

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosCadastroEstoque dados) {
        var estoque = repository.getReferenceById(dados.codigo());
        estoque.atualizarInformacoes(dados);

    }

    @DeleteMapping("/{codigo}")
    @Transactional
    public void excluir(@PathVariable String codigo) {
        repository.deleteById(codigo);
    }

    @GetMapping("/localiza-estoque")
    public Estoque findEstoqueById(@RequestParam String codigo) {
        return repository.findByCodigo(codigo).orElse(null);
    }
}

