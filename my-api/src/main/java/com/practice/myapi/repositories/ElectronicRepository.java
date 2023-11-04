package com.practice.myapi.repositories;

import com.practice.myapi.model.electronicProduct;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ElectronicRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ElectronicMapper mapper = new ElectronicMapper();
    public ElectronicRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<electronicProduct> getAlLElectronics(){
        String sql = "select * from electronic_product";
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
}
