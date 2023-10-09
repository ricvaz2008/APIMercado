package mercado.alves.api.vendas;

import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.util.Date;

public record DadosCadastroVendas(
        @NotNull
        String numeroVenda,
        String cpf,
        String caixa,
        Date data,
        Time hora,
        String pagamento,
        Double valorTotal) {
}
