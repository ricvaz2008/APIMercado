package mercado.alves.api.controller;

import jakarta.validation.Valid;
import mercado.alves.api.estoque.Estoque;
import mercado.alves.api.itensVenda.*;
import mercado.alves.api.vendas.Vendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("itensVenda")
@CrossOrigin(origins = {"https://brave-plant-0b3e4cc0f.3.azurestaticapps.net", "http://127.0.0.1:5500"})
public class ItensVendaController {

    @Autowired
    private ItensVendaRepository repository;

    @Autowired
    private CustomItems customItems;

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
    public ResponseEntity<List<DadosDoCupom>> getLocalizaItemVendaData(@RequestParam String partialNumeroCupom) {
        List<DadosDoCupom> itemVendaData = customItems.getLocalizaItemVendaData(partialNumeroCupom);
        return ResponseEntity.ok(itemVendaData);
    }
    @GetMapping("/joined-data")
    public ResponseEntity<List<DadosCustomizaveis>> getJoinedData(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder) {

        List<DadosCustomizaveis> joinedData = customItems.getJoinedData();

        if (sortBy != null) {

            joinedData.sort((item1, item2) -> {
                int compareResult;
                switch (sortBy) {
                    case "cpf":
                        compareResult = item1.getCpf().compareTo(item2.getCpf());
                        break;
                    case "valorTotal":
                        compareResult = Double.compare(item1.getValorTotal(), item2.getValorTotal());
                        break;
                    case "data":
                        compareResult = item1.getData().compareTo(item2.getData());
                        break;
                    case "codigo":
                        compareResult = item1.getCodigo().compareTo(item2.getCodigo());
                        break;
                    case "cupom":
                        compareResult = item1.getCupom().compareTo(item2.getCupom());
                        break;
                    case "produto":
                        compareResult = item1.getProduto().compareTo(item2.getProduto());
                        break;
                    case "pagamento":
                        compareResult = item1.getPagamento().compareTo(item2.getPagamento());
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
}