package mercado.alves.api.estoque;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "estoque")
@Entity(name = "estoque")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {

    @Id
    private String codigo;
    private String produto;
    private String lote;
    private Integer quantidade;
    private Double valor;
    private Date vencimento;
    private String status;
    private String foto;

    public Estoque(DadosCadastroEstoque dados) {
        this.codigo = dados.codigo();
        this.produto = dados.produto();
        this.lote = dados.lote();
        this.quantidade = dados.quantidade();
        this.valor = dados.valor();
        this.vencimento = dados.vencimento();
        this.status = dados.status();
        this.foto = dados.foto();
    }

    public void atualizarInformacoes(DadosCadastroEstoque dados) {

        if(dados.codigo() != null) {
            this.codigo = dados.codigo();
        }

        if(dados.produto() != null) {
            this.produto = dados.produto();
        }

        if(dados.lote() != null) {
            this.lote = dados.lote();
        }

        if(dados.quantidade() != null) {
            this.quantidade = dados.quantidade();
        }

        if(dados.valor() != null) {
            this.valor = dados.valor();
        }

        if(dados.vencimento() != null) {
            this.vencimento = dados.vencimento();
        }

        if(dados.status() != null) {
            this.status = dados.status();
        }

        if(dados.foto() != null) {
            this.foto = dados.foto();
        }
    }
}