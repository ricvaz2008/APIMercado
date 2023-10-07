package mercado.alves.api.itensVenda;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroItensVenda(
        @NotNull
        String numeroCupom,
        Double numeroVenda,
        Integer quantidade,
        String codigo,
        Double valor) {
}
