package io.jyotirmay.digitaltwin.cloudapi.v1.repository;

import io.jyotirmay.digitaltwin.cloudapi.v1.entity.MqttClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<MqttClientEntity, Long> {

    MqttClientEntity findByClientId(String clientId);

    void deleteByClientId(String clientId);



}
