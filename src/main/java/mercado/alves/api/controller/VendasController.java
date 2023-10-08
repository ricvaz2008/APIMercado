package mercado.alves.api.controller;

import jakarta.validation.Valid;
import mercado.alves.api.vendas.DadosCadastroVendas;
import mercado.alves.api.vendas.DadosDeVendas;
import mercado.alves.api.vendas.Vendas;
import mercado.alves.api.vendas.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("vendas")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "http://127.0.0.1:5500"})
public class VendasController {

    @Autowired
    private VendasRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroVendas dados) {
        repository.save(new Vendas(dados));
    }

    @GetMapping
    public Page<DadosDeVendas> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDeVendas::new);

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosCadastroVendas dados) {
        var Vendas = repository.getReferenceById(dados.numeroVenda());
        Vendas.atualizarInformacoes(dados);

    }

    @DeleteMapping("/{numeroVenda}")
    @Transactional
    public void excluir(@PathVariable String numeroVenda) {
        repository.deleteById(numeroVenda);
    }

    @GetMapping("/localiza-venda")
    public Vendas findVendasById(@RequestParam String numeroVenda) {
        return repository.findByNumeroVenda(numeroVenda).orElse(null);
    }
}

