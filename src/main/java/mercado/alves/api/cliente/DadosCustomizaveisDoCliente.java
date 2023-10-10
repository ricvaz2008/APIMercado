package mercado.alves.api.cliente;

import java.util.Date;

public class DadosCustomizaveisDoCliente {
    private String cpf;
    private String nome;
    private Date data;
    private String cupom;

    public DadosCustomizaveisDoCliente(
            String cpf,
            String nome,
            Date data,
            String cupom
    ) {
        this.cpf = cpf;
        this.nome = nome;
        this.data = data;
        this.cupom = cupom;
    }
    public String getCpf() {
        return cpf;
    }
    public String getNome() {
        return nome;
    }
    public Date getData() {
        return data;
    }
    public String getCupom() {
        return cupom;
    }
}

