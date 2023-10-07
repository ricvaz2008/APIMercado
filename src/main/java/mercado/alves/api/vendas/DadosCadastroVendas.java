package mercado.alves.api.vendas;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroVendas(
        @NotNull
        String numeroVenda,
        String cpf,
        String caixa,
        Double valorTotal) {
}
