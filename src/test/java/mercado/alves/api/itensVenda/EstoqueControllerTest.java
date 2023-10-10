import com.fasterxml.jackson.databind.ObjectMapper;
import mercado.alves.api.ApiApplication;
import mercado.alves.api.estoque.DadosCadastroEstoque;
import mercado.alves.api.estoque.EstoqueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest (classes = ApiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EstoqueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EstoqueRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve criar um novo item no estoque")
    public void testCreateProductInInventory() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date vencimento = dateFormat.parse("08/08/2025");

        DadosCadastroEstoque dadosCadastroEstoque = new DadosCadastroEstoque(
                "12345",
                "Chiclete com Banana",
                "43JAN23",
                100,
                5.25,
                vencimento,
                "Ativo",
                "./assets/Chiclete"
        );


        String requestBody = objectMapper.writeValueAsString(dadosCadastroEstoque);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:5000/estoque")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).contains("itemCadastrado");

        assertThat(repository.count()).isEqualTo(1);
    }
}
