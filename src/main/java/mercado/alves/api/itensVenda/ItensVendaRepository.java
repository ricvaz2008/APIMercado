package mercado.alves.api.itensVenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItensVendaRepository extends JpaRepository<ItensVenda, String> {
}