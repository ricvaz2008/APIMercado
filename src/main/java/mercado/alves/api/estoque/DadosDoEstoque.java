package mercado.alves.api.estoque;

import java.util.Date;

public record DadosDoEstoque(String codigo, String produto, String lote, Integer quantidade, Double valor, Date vencimento, String status, String foto) {

    public DadosDoEstoque(Estoque estoque){
        this(estoque.getCodigo(), estoque.getProduto(), estoque.getLote(), estoque.getQuantidade(), estoque.getValor(), estoque.getVencimento(), estoque.getStatus(), estoque.getFoto());
    }
}
