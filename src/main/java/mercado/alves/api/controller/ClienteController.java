package mercado.alves.api.controller;

import jakarta.validation.Valid;
import mercado.alves.api.cliente.Cliente;
import mercado.alves.api.cliente.ClienteRepository;
import mercado.alves.api.cliente.DadosCadastroCliente;
import mercado.alves.api.cliente.DadosDoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("clientes")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "https://127.0.0.1:5000"})
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroCliente dados) {
        repository.save(new Cliente(dados));
    }

    @GetMapping
    public Page<DadosDoCliente> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosDoCliente::new);

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosCadastroCliente dados) {
        var cliente = repository.getReferenceById(dados.cpf());
        cliente.atualizarInformacoes(dados);

    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public void excluir(@PathVariable String cpf) {
        repository.deleteById(cpf);
    }

    @GetMapping("/localiza-cliente")
    public Cliente findUsuarioByCpf(@RequestParam String cpf) {
        return repository.findByCpf(cpf).orElse(null);
    }
}
