package mercado.alves.api.estoque;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroEstoque(
        @NotNull
        String codigo,
        String produto,
        String lote,
        Integer quantidade,
        Double valor,
        Date vencimento,
        String status,
        String foto) {
        public void setCodigo(String number) {
        }


        public void setProduto(String chicleteComBanana) {
        }

        public void setLote(String s) {
        }

        public void setQuantidade(int i) {
        }

        public void setValor(double v) {
        }

        public void setVencimento(String s) {
        }

        public void setStatus(String ativo) {
        }

        public void setFoto(String s) {
        }
}
