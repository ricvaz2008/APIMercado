package mercado.alves.api.itensVenda;

import java.util.Date;

public class DadosDoCupom {
    private Integer quantidade;
    private Double valorTotal;
    private String produto;

    public DadosDoCupom(
            Integer quantidade,
            Double valorTotal,
            String produto
    ) {
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.produto = produto;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public Double getValorTotal() {
        return valorTotal;
    }
    public String getProduto() {
        return produto;
    }
    }


