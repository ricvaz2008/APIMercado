package mercado.alves.api.vendas;

import java.sql.Time;
import java.util.Date;

public record DadosDeVendas(String numeroVenda, String cpf, String caixa, Date data, Time hora, String pagamento, String cupom, Double valorTotal) {

    public DadosDeVendas(Vendas vendas){
        this(vendas.getNumeroVenda(),vendas.getCpf(),vendas.getCaixa(),vendas.getData(),vendas.getHora(),vendas.getPagamento(),vendas.getCupom(),vendas.getValorTotal());
    }
}
