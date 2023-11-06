package com.practice.myapi.repositories;

import com.practice.myapi.model.electronicProduct;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ElectronicRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ElectronicMapper mapper = new ElectronicMapper();
    private final String table = "electronic_product";
    private final TransactionTemplate transactionTemplate;
    public ElectronicRepository(NamedParameterJdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    public List<electronicProduct> getAlLElectronics(){
        String sql = "select * from " + table;
        return jdbcTemplate.query(sql,mapper);//los datos que vengan de sql hay que mapearlos a java
    }

    private static class ElectronicMapper implements RowMapper<electronicProduct> {
        //Alt + enter para implementar metodos
        @Override
        public electronicProduct mapRow(ResultSet rs, int rowNum) throws SQLException {//reescribimos este metodo para coincidir con los datos de nuestra tabla
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            return new electronicProduct(id,name,price);
        }
    }

    public String createElectronic(electronicProduct newElectronic) {
        try {
            SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("name", newElectronic.name)
                    .addValue("price", newElectronic.price);
            String insertQuery = "INSERT INTO " + table + " (name, price) VALUES (:name, :price)";// : es sintaxis de jdbc para mandar parametros nombrados especificamente
            jdbcTemplate.update(insertQuery, parameterSource);
        } catch (Exception e) {
            // En caso de error, realizar un rollback de la transacción
            throw e;
        }
        return "Datos insertados correctamente";
    }

    public String insertElectronicProducts(List<electronicProduct> newElectronic) {
        // Iniciar una transacción
        transactionTemplate.execute(status -> {
            try {
                for (electronicProduct product : newElectronic) {
                    SqlParameterSource parameterSource = new MapSqlParameterSource()
                            .addValue("name", product.name)
                            .addValue("price", product.price);
                    String insertQuery = "INSERT INTO " + table + " (name, price) VALUES (:name, :price)";
                    jdbcTemplate.update(insertQuery, parameterSource);
                }
                return null; // Retornar null
            } catch (Exception e) {
                status.setRollbackOnly(); // Realizar un rollback en caso de excepción
                throw e;
            }
        });
        return "Datos insertados correctamente";
    }
}
