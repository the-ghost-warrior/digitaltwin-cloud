package io.jyotirmay.digitaltwin.cloudapi.v1.resource;

import io.jyotirmay.digitaltwin.cloudapi.v1.entity.MqttClientEntity;
import io.jyotirmay.digitaltwin.cloudapi.v1.model.MqttClient;
import io.jyotirmay.digitaltwin.cloudapi.v1.repository.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static io.jyotirmay.digitaltwin.cloudapi.v1.util.CommonUtil.isNotNull;
import static io.jyotirmay.digitaltwin.cloudapi.v1.util.CommonUtil.isNull;

@RestController
public class ClientResource {

    private static final Logger LOGGER = LogManager.getLogger(ClientResource.class);

    private ClientRepository clientRepository;

    @Autowired
    private void setClientRepository(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @PostMapping(value = "/v1/client/{clientId}")
    public ResponseEntity<String> registerClient(@PathVariable(value = "clientId") String clientId,
                                                     @RequestBody(required = false) MqttClient mqttClient){
        LOGGER.info("Request received to register client with Id: {}", clientId);

        if(!isNotNull(mqttClient)){
            return new ResponseEntity<>("Missing Mqtt Client properties in body.", HttpStatus.BAD_REQUEST);
        }

        if(isNull(mqttClient.getClientId()) || !mqttClient.getClientId().equalsIgnoreCase(clientId)){
            return new ResponseEntity<>("Incorrect client-ID, not matching with body.", HttpStatus.BAD_REQUEST);
        }

        MqttClientEntity mqttClientEntity = new MqttClientEntity(mqttClient);
        mqttClientEntity.setActive(true);
        mqttClientEntity.setCreatedOn(new Date());

        boolean clientIdExists = clientRepository.checkIfMqttClientExists(clientId);
        if(clientIdExists){
            return new ResponseEntity<>("Client-ID already exists", HttpStatus.BAD_REQUEST);
        }

        try{
           clientRepository.createClient(mqttClientEntity);
           LOGGER.info("The client {} registered successfully with id:{}", mqttClientEntity.getClientId(),
                   mqttClientEntity.getId());
           return new ResponseEntity<>("Client " + mqttClientEntity.getClientId() + " registered successfully",
                   HttpStatus.CREATED);
        }catch (Exception e){
            LOGGER.info("Exception occurred while registering client, {}", e.getMessage());
            return new ResponseEntity<>("Error occurred while registering client", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/v1/clients")
    public ResponseEntity<List<String>> getRegisteredClients(){
        LOGGER.info("Request received to get list of registered clients.");
        try{
            return new ResponseEntity<>(clientRepository.getMqttClients(), HttpStatus.OK);
        }catch(Exception e){
            LOGGER.error("Exception occurred while fetching registered mqtt clients, {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/v1/client/{clientId}")
    public ResponseEntity<MqttClient> getRegisteredClient(@PathVariable("clientId") String clientId){
        LOGGER.info("Request received to get registered client with Id: {}", clientId);

        try{
                MqttClientEntity mqttClientEntity = clientRepository.getMqttClientByClientId(clientId);
                return new ResponseEntity<>(new MqttClient(mqttClientEntity), HttpStatus.OK);
        }catch(Exception e){
            LOGGER.error("Exception occurred while getting mqtt properties for client: {}, {}", clientId,
                    e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/v1/client/{clientId}")
    public ResponseEntity<String> deRegisterClient(@PathVariable("clientId") String clientId){
        LOGGER.info("Request received to de-register client with Id: {}", clientId);

        boolean clientIdExists = clientRepository.checkIfMqttClientExists(clientId);
        if(!clientIdExists){
            return new ResponseEntity<>("Client-ID does not exist", HttpStatus.BAD_REQUEST);
        }

        try{
           clientRepository.deleteMqttClientById(clientId);
           return new ResponseEntity<>("Client "+ clientId + " was deleted successfully.", HttpStatus.OK);
        } catch(Exception e){
            LOGGER.error("Exception occurred while deleting mqtt properties for client: {}, {}", clientId,
                    e.getMessage());
            return new ResponseEntity<>("Error occurred while deleting client",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/v1/clients")
    public ResponseEntity<String> deRegisterClients(){
        LOGGER.info("Request received to de-register all registered clients.");
        try{
            clientRepository.deleteAllMqttClients();
            return new ResponseEntity<>("All clients were deleted successfully.", HttpStatus.OK);
        } catch(Exception e){
            LOGGER.error("Exception occurred while deleting mqtt properties for all clients, {}",
                    e.getMessage());
            return new ResponseEntity<>("Error occurred while deleting all clients",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
