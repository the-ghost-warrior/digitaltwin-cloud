package io.jyotirmay.digitaltwin.cloudapi.v1.repository;

import io.jyotirmay.digitaltwin.cloudapi.v1.entity.MqttClientEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClientRepository {

    private static final Logger LOGGER = LogManager.getLogger(ClientRepository.class);

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public MqttClientEntity createClient(MqttClientEntity mqttClientEntity){

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("mqtt_client")
                .usingGeneratedKeyColumns("id");

        Number number = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(mqttClientEntity));
        mqttClientEntity.setId(number.longValue());
        return mqttClientEntity;

    }

    public boolean checkIfMqttClientExists(String clientId){
        String query = "select exists(select 1 from mqtt_client where client_id=:clientId)";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("clientId", clientId);

        return namedParameterJdbcTemplate.query(query, parameters, rs ->  {
            rs.next();
           return rs.getBoolean("exists");
        });
    }

    public List<String> getMqttClients(){

        String query = "select * from mqtt_client";

        return namedParameterJdbcTemplate.query(query, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
               return resultSet.getString("client_id");
            }
        });
    }

    public MqttClientEntity getMqttClientByClientId(String clientId){
        String query = "select * from mqtt_client where client_id=:clientId";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("clientId", clientId);

        return namedParameterJdbcTemplate.query(query, parameters, resultSet ->  {
            resultSet.next();
            MqttClientEntity mqttClientEntity = new MqttClientEntity(
                    resultSet.getString("client_id"),
                    resultSet.getBoolean("clean_session"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("last_will_topic"),
                    resultSet.getInt("last_will_qos"),
                    resultSet.getString("last_will_message"),
                    resultSet.getBoolean("last_will_retain"),
                    resultSet.getInt("keep_alive"));
            return mqttClientEntity;
        });

    }

    public void deleteMqttClientById(String clientId){
        String query = "delete from mqtt_client where client_id = :clientId";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("clientId", clientId);
        namedParameterJdbcTemplate.update(query, parameters);
    }

    public void deleteAllMqttClients(){
        String query = "delete from mqtt_client";
        namedParameterJdbcTemplate.update(query, new HashMap<>());
    }


}
