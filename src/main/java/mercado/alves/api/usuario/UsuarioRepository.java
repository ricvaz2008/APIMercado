package mercado.alves.api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT new mercado.alves.api.usuario.DadoDeAcesso(u.acesso) FROM usuarios u WHERE u.login = :login AND u.senha = :senha")
    DadoDeAcesso findAcessoByLoginAndSenha(@Param("login") String login, @Param("senha") String senha);
    @Query
    Optional<Usuario> findById(@Param("id") Long id);
}