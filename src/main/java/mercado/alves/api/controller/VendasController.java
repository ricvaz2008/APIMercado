package mercado.alves.api.controller;

import jakarta.validation.Valid;
import mercado.alves.api.usuario.DadosCadastroUsuario;
import mercado.alves.api.usuario.Usuario;
import mercado.alves.api.vendas.DadosCadastroVendas;
import mercado.alves.api.vendas.DadosDeVendas;
import mercado.alves.api.vendas.Vendas;
import mercado.alves.api.vendas.VendasRepository;
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
@RequestMapping("vendas")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "http://127.0.0.1:5500"})
public class VendasController {

    @Autowired
    private VendasRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, String>> cadastrar(@RequestBody @Valid DadosCadastroVendas dados) {
        repository.save(new Vendas(dados));
        Map<String, String> response = new HashMap<>();
        response.put("status", "itemCadastrado");
        return ResponseEntity.status(HttpStatus.OK).body(response);
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

    @GetMapping("/localiza-venda")
    public Vendas findVendasById(@RequestParam String cupom) {
        return repository.findByCupom(cupom).orElse(null);
    }

    @GetMapping("/busca-customizada")
    public Vendas customSearch(@RequestParam String cupom) {
        return repository.findByCupom(cupom).orElse(null);
    }
}

