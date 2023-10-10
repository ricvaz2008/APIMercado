package mercado.alves.api.vendas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, String> {

    Page<Vendas> findAllByCpf(@Param("cpf") String cpf, Pageable pageable);

    @Query
    Optional<Vendas> findByCupom(@Param("cupom") String cupom);
}