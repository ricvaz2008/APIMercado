package mercado.alves.api.estoque;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroEstoque(
        @NotNull
        String codigo,
        String produto,
        String lote,
        Integer quantidade,
        Double valor,
        Date vencimento,
        String status,
        String foto) {
}
