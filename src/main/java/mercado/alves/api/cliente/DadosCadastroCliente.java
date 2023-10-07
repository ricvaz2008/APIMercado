package mercado.alves.api.cliente;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroCliente(
        @NotNull
        String cpf,
        String nome,
        Date nascimento,
        String telefone,
        String email,
        String endereco,
        String cidade,
        String estado,
        String cep) {
}
