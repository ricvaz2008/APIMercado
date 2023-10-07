package mercado.alves.api.cliente;

import java.util.Date;

public record DadosDoCliente(String cpf,String nome,Date nascimento,String telefone,String email,String endereco,String cidade,String estado,String cep) {

    public DadosDoCliente(Cliente cliente){
        this(cliente.getCpf(), cliente.getNome(),cliente.getNascimento(),cliente.getTelefone(),cliente.getEmail(),cliente.getEndereco(),cliente.getCidade(),cliente.getEstado(),cliente.getCep());
    }
}
