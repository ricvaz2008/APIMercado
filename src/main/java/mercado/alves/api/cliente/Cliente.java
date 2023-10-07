package mercado.alves.api.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "clientes")
@Entity(name = "clientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    private String cpf;
    private String nome;
    private Date nascimento;
    private String telefone;
    private String email;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;

    public Cliente(DadosCadastroCliente dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
        this.nascimento = dados.nascimento();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.endereco = dados.endereco();
        this.cidade = dados.cidade();
        this.estado = dados.estado();
        this.cep = dados.cep();
    }

    public void atualizarInformacoes(DadosCadastroCliente dados) {

        if(dados.cpf() != null) {
            this.cpf = dados.cpf();
        }

        if(dados.nome() != null) {
            this.nome = dados.nome();
        }

        if(dados.nascimento() != null) {
            this.nascimento = dados.nascimento();
        }

        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if(dados.email() != null) {
            this.email = dados.email();
        }

        if(dados.endereco() != null) {
            this.endereco = dados.endereco();
        }

        if(dados.cidade() != null) {
            this.cidade = dados.cidade();
        }

        if(dados.estado() != null) {
            this.estado = dados.estado();
        }

        if(dados.cep() != null) {
            this.cep = dados.cep();
        }
    }
}