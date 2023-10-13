package mercado.alves.api.cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomClienteItems {
    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    public List<DadosCustomizaveisDoCliente> getJoinedData() {
        String jpql = "SELECT c.cpf, c.nome, v.data, v.cupom, v.hora " +
                "FROM clientes c " +
                "JOIN Vendas v ON c.cpf = v.cpf " +
                "WHERE (c.cpf, c.nome, v.cupom, v.data) IN (" +
                "SELECT c1.cpf, c1.nome, v1.cupom, MAX(v1.data) as max_data " +
                "FROM clientes c1 " +
                "JOIN Vendas v1 ON c1.cpf = v1.cpf " +
                "GROUP BY c1.cpf, c1.nome, v1.cupom)";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);

        List<DadosCustomizaveisDoCliente> dadosCustomizaveisDoClienteList = new ArrayList<>();

        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            String cpf = (String) result[0];
            String nome = (String) result[1];
            Date data = (Date) result[2];
            String cupom = (String) result[3];
            Time hora = (Time) result[4];

            DadosCustomizaveisDoCliente dadosCustomizaveisDoCliente = new DadosCustomizaveisDoCliente(cpf, nome, data, cupom, hora);
            dadosCustomizaveisDoClienteList.add(dadosCustomizaveisDoCliente);
        }
        return dadosCustomizaveisDoClienteList;
    }
}