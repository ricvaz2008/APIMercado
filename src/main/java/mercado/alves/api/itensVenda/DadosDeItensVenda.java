package mercado.alves.api.itensVenda;

import java.util.Date;

public record DadosDeItensVenda(String numeroCupom, Double numeroVenda, Integer quantidade, String codigo, Double valor) {

    public DadosDeItensVenda(ItensVenda itensVenda){
        this(itensVenda.getNumeroCupom(), itensVenda.getNumeroVenda(), itensVenda.getQuantidade(), itensVenda.getCodigo(), itensVenda.getValor());
    }
}
