package mercado.alves.api.vendas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Double> {

    @Query
    Optional<Vendas> findByNumeroVenda(@Param("numeroVenda") Double numeroVenda);
}