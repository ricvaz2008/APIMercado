package mercado.alves.api.controller;

import jakarta.validation.Valid;
import mercado.alves.api.estoque.DadosCadastroEstoque;
import mercado.alves.api.estoque.DadosDoEstoque;
import mercado.alves.api.estoque.Estoque;
import mercado.alves.api.estoque.EstoqueRepository;
import mercado.alves.api.itensVenda.DadosCadastroItensVenda;
import mercado.alves.api.itensVenda.DadosDeItensVenda;
import mercado.alves.api.itensVenda.ItensVenda;
import mercado.alves.api.itensVenda.ItensVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("itensVenda")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "https://127.0.0.1:5000"})
public class ItensVendaController {

    @Autowired
    private ItensVendaRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroItensVenda dados) {
        repository.save(new ItensVenda(dados));
    }

    @GetMapping
    public Page<DadosDeItensVenda> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDeItensVenda::new);

    }

    @GetMapping("/localiza-itemVenda")
    public ItensVenda findItemVendaById(@RequestParam String numeroCupom) {
        return repository.findByNumeroCupom(numeroCupom).orElse(null);
    }
}

