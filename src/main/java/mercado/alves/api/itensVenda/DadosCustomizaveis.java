package mercado.alves.api.itensVenda;

import java.util.Date;

public class DadosCustomizaveis {
        private String cpf;
        private Double valorTotal;
        private Date data;
        private String codigo;
        private String cupom;
        private String produto;
        private String pagamento;

        public DadosCustomizaveis(
                String cpf,
                Double valorTotal,
                Date data,
                String codigo,
                String cupom,
                String produto,
                String pagamento
        ) {
                this.cpf = cpf;
                this.valorTotal = valorTotal;
                this.data = data;
                this.codigo = codigo;
                this.cupom = cupom;
                this.produto = produto;
                this.pagamento = pagamento;
        }
        public String getCpf() {
                return cpf;
        }

        public Double getValorTotal() {
                return valorTotal;
        }

        public Date getData() {
                return data;
        }

        public String getCodigo() {
                return codigo;
        }

        public String getCupom() {
                return cupom;
        }

        public String getProduto() {
                return produto;
        }

        public String getPagamento() {
                return pagamento;
        }
}

