package mercado.alves.api.controller;

import jakarta.validation.Valid;
import mercado.alves.api.itensVenda.*;
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
@RequestMapping("itensVenda")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "http://127.0.0.1:5500"})
public class ItensVendaController {

    @Autowired
    private ItensVendaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, String>> cadastrar(@RequestBody @Valid DadosCadastroItensVenda dados) {
        repository.save(new ItensVenda(dados));
        Map<String, String> response = new HashMap<>();
        response.put("status", "vendaFinalizada");
        return ResponseEntity.status(HttpStatus.OK).body(response);
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

