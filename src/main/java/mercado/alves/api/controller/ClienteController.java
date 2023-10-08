package mercado.alves.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import mercado.alves.api.cliente.ClienteRepository;
import mercado.alves.api.cliente.Cliente;
import mercado.alves.api.cliente.DadosDoCliente;
import mercado.alves.api.cliente.DadosCadastroCliente;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("clientes")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "http://127.0.0.1:5500"})
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, String>> cadastrar(@RequestBody @Valid DadosCadastroCliente dados) {
        repository.save(new Cliente(dados));
        Map<String, String> response = new HashMap<>();
        response.put("status", "itemCadastrado");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public Page<DadosDoCliente> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDoCliente::new);

    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid DadosCadastroCliente dados) {
        try {
            var cliente = repository.getReferenceById(dados.cpf());
            cliente.atualizarInformacoes(dados);

            return ResponseEntity.ok("{\"message\": \"modificado\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred\"}");
        }
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<String> excluir(@PathVariable String cpf) {
        try {
            repository.deleteById(cpf);
            return ResponseEntity.ok("{\"message\": \"itemApagado\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Erro ao apagar o item\"}");
        }
    }

    @GetMapping("/localiza-cliente")
    public Cliente findClienteById(@RequestParam String cpf) {
        return repository.findByCpf(cpf).orElse(null);
    }
}

