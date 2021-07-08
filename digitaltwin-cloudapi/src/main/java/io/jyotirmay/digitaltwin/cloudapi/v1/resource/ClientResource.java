package io.jyotirmay.digitaltwin.cloudapi.v1.resource;

import io.jyotirmay.digitaltwin.cloudapi.v1.entity.MqttClientEntity;
import io.jyotirmay.digitaltwin.cloudapi.v1.model.MqttClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
public class ClientResource {

    private static final Logger LOGGER = LogManager.getLogger(ClientResource.class);


    @PostMapping(value = "/v1/client/{clientId}")
    public ResponseEntity<HttpStatus> registerClient(@PathVariable("clientId") String clientId,
                                                     @RequestBody MqttClient mqttClient){
        LOGGER.info("Request received to register client with Id: {}", clientId);
        MqttClientEntity mqttClientEntity = new MqttClientEntity(mqttClient);
        mqttClientEntity.setActive(true);
        mqttClientEntity.setCreatedOn(new Date());

        return null;
    }

    @GetMapping(value = "/v1/clients")
    public ResponseEntity<List<String>> getRegisteredClients(){
        LOGGER.info("Request received to get list of registered clients.");

        return null;
    }

    @GetMapping(value = "/v1/client/{clientId}")
    public ResponseEntity<MqttClient> getRegisteredClient(@PathVariable("clientId") String clientId){
        LOGGER.info("Request received to get registered client with Id: {}", clientId);

        return null;
    }

    @DeleteMapping(value = "/v1/client/{clientId}")
    public ResponseEntity<HttpStatus> deRegisterClient(@PathVariable("clientId") String clientId){
        LOGGER.info("Request received to de-register client with Id: {}", clientId);

        return null;
    }

    @DeleteMapping(value = "/v1/clients")
    public ResponseEntity<List<String>> deRegisterClients(){
        LOGGER.info("Request received to de-register all registered clients.");

        return null;
    }

}
