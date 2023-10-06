package mercado.alves.api.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "usuarios")
@Getter@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cargo;
    private String login;
    private String senha;
    private String acesso;

    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.cargo = dados.cargo();
        this.login = dados.login();
        this.senha = dados.senha();
        this.acesso = dados.acesso();
    }
}
