package mercado.alves.api.controller;

import jakarta.validation.Valid;
import mercado.alves.api.cliente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("clientes")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "http://127.0.0.1:5500"})
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private CustomClienteItems customClienteItems;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, String>> cadastrar(@RequestBody @Valid DadosCadastroCliente dados) {
        repository.save(new Cliente(dados));
        Map<String, String> response = new HashMap<>();
        response.put("status", "itemCadastrado");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/listar")
    public Page<DadosDoCliente> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDoCliente::new);
    }

    @GetMapping("/custom-listar")
    public ResponseEntity<List<DadosCustomizaveisDoCliente>> customListar (
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder) {

        List<DadosCustomizaveisDoCliente> joinedData = customClienteItems.getJoinedData();

        if (sortBy != null) {
            System.out.println(joinedData);

            joinedData.sort((item1, item2) -> {
                int compareResult;
                switch (sortBy) {
                    case "cpf":
                        compareResult = item1.getCpf().compareTo(item2.getCpf());
                        break;
                    case "nome":
                        compareResult = item1.getNome().compareTo(item2.getNome());
                        break;
                    case "compra":
                        compareResult = item1.getData().compareTo(item2.getData());
                        break;
                    case "cupom":
                        compareResult = item1.getCupom().compareTo(item2.getCupom());
                        break;

                    default:
                        compareResult = 0;
                        break;
                }

                return sortOrder.equals("asc") ? compareResult : -compareResult;
            });
        }

        return ResponseEntity.ok(joinedData);
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

