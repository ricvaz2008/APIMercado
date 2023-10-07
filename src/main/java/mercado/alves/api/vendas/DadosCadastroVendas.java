package mercado.alves.api.vendas;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroVendas(
        @NotNull
        Double numeroVenda,
        String cpf,
        String caixa,
        Double valorTotal) {
}
