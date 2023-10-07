package mercado.alves.api.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
        @NotNull
        Long id,
        String nome,
        String cargo,
        String login,
        String senha,
        String acesso) {
}
