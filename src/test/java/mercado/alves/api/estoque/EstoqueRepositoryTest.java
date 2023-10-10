package mercado.alves.api.estoque;

import mercado.alves.api.estoque.Estoque;
import mercado.alves.api.estoque.EstoqueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EstoqueRepositoryTest {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private TestEntityManager entityManager;

    private SimpleDateFormat dateFormat;

    @BeforeEach
    public void setUp() throws ParseException {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Create and persist sample data in the database
        Estoque estoque1 = new Estoque("111234", "Café Pilão", "336UUU98", 10, 5.0, dateFormat.parse("2023-12-31"), "Ativo", "image1.jpg");
        Estoque estoque2 = new Estoque("222222", "Açúcar União", "55OO8", 20, 10.0, dateFormat.parse("2024-06-30"), "Ativo", "image2.jpg");

        entityManager.persist(estoque1);
        entityManager.persist(estoque2);
    }

    @Test
    @DisplayName("Deve encontrar um produto com apenas uma parte do código")
    @Transactional
    public void testFindByCodigoContaining() {

        List<Estoque> foundEstoques = estoqueRepository.findByCodigoContaining("111");

        // Verify the result
        assertThat(foundEstoques).hasSize(1);
        assertThat(foundEstoques.get(0).getCodigo()).isEqualTo("111234");
        assertThat(foundEstoques.get(0).getProduto()).isEqualTo("Café Pilão");
    }

    @Test
    @DisplayName("Não deve encontrar um produto quando o código fornecido não é parte de nenhum registrado")
    @Transactional
    public void testFindByCodigoContaining_NoResults() {

        List<Estoque> foundEstoques = estoqueRepository.findByCodigoContaining("456");

        assertThat(foundEstoques).isEmpty();
    }
}