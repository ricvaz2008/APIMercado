package mercado.alves.api.vendas;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "vendas")
@Entity(name = "vendas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Vendas {

    @Id
    private String numeroVenda;
    private String cpf;
    private String caixa;
    private Double valorTotal;

    public Vendas(DadosCadastroVendas dados) {
        this.numeroVenda = dados.numeroVenda();
        this.cpf = dados.cpf();
        this.caixa = dados.caixa();
        this.valorTotal = dados.valorTotal();
    }

    public void atualizarInformacoes(DadosCadastroVendas dados) {

        if(dados.numeroVenda() != null) {
            this.numeroVenda = dados.numeroVenda();
        }

        if(dados.cpf() != null) {
            this.cpf = dados.cpf();
        }

        if(dados.caixa() != null) {
            this.caixa = dados.caixa();
        }

        if(dados.valorTotal() != null) {
            this.valorTotal = dados.valorTotal();
        }
    }
}