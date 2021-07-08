package io.jyotirmay.digitaltwin.cloudapi.v1.repository;

import io.jyotirmay.digitaltwin.cloudapi.v1.entity.MqttClientEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

    private static final Logger LOGGER = LogManager.getLogger(ClientRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public MqttClientEntity registerClient(MqttClientEntity mqttClientEntity){

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("mqtt_client")
                .usingGeneratedKeyColumns("id");

        Number number = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(mqttClientEntity));
        mqttClientEntity.setId(number.longValue());
        return mqttClientEntity;

    }
}
