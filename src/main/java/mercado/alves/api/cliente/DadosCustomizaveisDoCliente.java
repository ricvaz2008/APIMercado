package mercado.alves.api.cliente;

import java.sql.Time;
import java.util.Date;

public class DadosCustomizaveisDoCliente {
    private String cpf;
    private String nome;
    private Date data;
    private String cupom;
    private Time hora;

    public DadosCustomizaveisDoCliente(
            String cpf,
            String nome,
            Date data,
            String cupom,
            Time hora
    ) {
        this.cpf = cpf;
        this.nome = nome;
        this.data = data;
        this.cupom = cupom;
        this.hora = hora;
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
    public Time getHora(){return hora;}
}

