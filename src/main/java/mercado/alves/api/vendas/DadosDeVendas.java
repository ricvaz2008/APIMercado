package mercado.alves.api.vendas;

public record DadosDeVendas(Double numeroVenda, String cpf, String caixa, Double valorTotal) {

    public DadosDeVendas(Vendas vendas){
        this(vendas.getNumeroVenda(),vendas.getCpf(),vendas.getCaixa(),vendas.getValorTotal());
    }
}