package mercado.alves.api.itensVenda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomItems {
    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    public List<DadosCustomizaveis> getJoinedData() {
        String jpql = "SELECT v.cpf, i.valor, v.data, v.pagamento, i.codigo, i.numeroCupom, e.produto " +
                "FROM Vendas v " +
                "JOIN ItensVenda i ON v.numeroVenda = i.numeroVenda " +
                "JOIN Estoque e ON i.codigo = e.codigo";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);

        List<DadosCustomizaveis> dadosCustomizaveisList = new ArrayList<>();

        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            String cpf = (String) result[0];
            Double valorTotal = (Double) result[1];
            Date data = (Date) result[2];
            String pagamento = (String) result[3];
            String codigo = (String) result[4];
            String cupom = (String) result[5];
            String produto = (String) result[6];

            DadosCustomizaveis dadosCustomizaveis = new DadosCustomizaveis(cpf, valorTotal, data, codigo, cupom, produto, pagamento);
            dadosCustomizaveisList.add(dadosCustomizaveis);
        }

        return dadosCustomizaveisList;
    }
    public List<DadosDoCupom> getLocalizaItemVendaData(String partialNumeroCupom) {
        String jpql = "SELECT i.quantidade, i.valor, e.produto\n" +
                "FROM ItensVenda i\n" +
                "JOIN Vendas v ON v.numeroVenda = i.numeroVenda\n" +
                "JOIN Estoque e ON i.codigo = e.codigo\n" +
                "WHERE i.numeroCupom LIKE :partialNumeroCupom";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("partialNumeroCupom", "%" + partialNumeroCupom + "%");

        List<DadosDoCupom> itemVendaDataList = new ArrayList<>();

        List<Object[]> results = query.getResultList();
        for (Object[] result : results) {
            Integer quantidade = (Integer) result[0];
            Double valorTotal = (Double) result[1];
            String produto = (String) result[2];

            DadosDoCupom dadosDoCupom = new DadosDoCupom(quantidade, valorTotal, produto);
            itemVendaDataList.add(dadosDoCupom);
        }

        return itemVendaDataList;
    }
}