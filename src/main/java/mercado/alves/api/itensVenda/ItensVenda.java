package mercado.alves.api.itensVenda;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "itensvenda")
@Entity(name = "ItensVenda")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItensVenda {

    @Id
    private String numeroCupom;
    private String numeroVenda;
    private Integer quantidade;
    private String codigo;
    private Double valor;

    public ItensVenda(DadosCadastroItensVenda dados) {
        this.numeroCupom = dados.numeroCupom();
        this.numeroVenda = dados.numeroVenda();
        this.quantidade = dados.quantidade();
        this.codigo = dados.codigo();
        this.valor = dados.valor();
    }

    public void atualizarInformacoes(DadosCadastroItensVenda dados) {

        if(dados.numeroCupom() != null) {
            this.numeroCupom = dados.numeroCupom();
        }

        if(dados.numeroVenda() != null) {
            this.numeroVenda = dados.numeroVenda();
        }

        if(dados.quantidade() != null) {
            this.quantidade = dados.quantidade();
        }

        if(dados.codigo() != null) {
            this.codigo = dados.codigo();
        }

        if(dados.valor() != null) {
            this.valor = dados.valor();
        }
    }
}