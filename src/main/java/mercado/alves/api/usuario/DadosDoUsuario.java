package mercado.alves.api.usuario;

public record DadosDoUsuario(Long id, String nome, String cargo, String login, String senha, String acesso) {

    public DadosDoUsuario(Usuario usuario){
        this(usuario. getId(), usuario.getNome(), usuario.getCargo(), usuario.getLogin(), usuario.getSenha(), usuario.getAcesso());
    }
}
