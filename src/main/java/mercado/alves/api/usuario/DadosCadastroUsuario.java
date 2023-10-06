package mercado.alves.api.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,
        @NotBlank
        String cargo,
        @NotBlank
        String login,
        @NotBlank
        String senha,
        @NotBlank
        String acesso) {
}
