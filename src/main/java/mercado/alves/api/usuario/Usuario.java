package mercado.alves.api.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private Long id;
    private String nome;
    private String cargo;
    private String login;
    private String senha;
    private String acesso;

    public Usuario(DadosCadastroUsuario dados) {
        this.id = dados.id();
        this.nome = dados.nome();
        this.cargo = dados.cargo();
        this.login = dados.login();
        this.senha = dados.senha();
        this.acesso = dados.acesso();
    }

    public void atualizarInformacoes(DadosCadastroUsuario dados) {

        if(dados.nome() != null) {
            this.nome = dados.nome();
        }

        if(dados.cargo() != null) {
            this.cargo = dados.cargo();
        }

        if(dados.login() != null) {
            this.login = dados.login();
        }

        if(dados.senha() != null) {
            this.senha = dados.senha();
        }

        if(dados.acesso() != null) {
            this.acesso = dados.acesso();
        }
    }
}
