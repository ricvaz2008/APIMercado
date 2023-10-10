package mercado.alves.api.estoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, String> {

    @Query
    Optional<Estoque> findByCodigo(@Param("codigo") String codigo);

    List<Estoque> findByCodigoContaining(String codigo);

    Optional<Estoque> findFirstByCodigoContainingAndStatus(String codigo, String status);
}