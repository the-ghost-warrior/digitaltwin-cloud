package io.jyotirmay.digitaltwin.cloudapi.v1.resource;

import io.jyotirmay.digitaltwin.cloudapi.v1.entity.MqttClientEntity;
import io.jyotirmay.digitaltwin.cloudapi.v1.model.MqttClient;
import io.jyotirmay.digitaltwin.cloudapi.v1.model.ResponseModel;
import io.jyotirmay.digitaltwin.cloudapi.v1.repository.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;

import static org.springframework.http.MediaType.*;
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
    private void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping(value = "/v1/cloud/client/{clientId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> registerClient(@PathVariable(value = "clientId") String clientId,
                                                        @RequestBody(required = false) MqttClient mqttClient) {
        LOGGER.info("Request received to register client with Id: {}", clientId);

        if (!isNotNull(mqttClient)) {
            ResponseModel responseModel = new ResponseModel(BAD_REQUEST.value(), "Missing Mqtt Client properties in body.", null);
            return new ResponseEntity<>(responseModel, BAD_REQUEST);
        }

        if (isNull(mqttClient.getClientId()) || !mqttClient.getClientId().equalsIgnoreCase(clientId)) {
            ResponseModel responseModel = new ResponseModel(BAD_REQUEST.value(), "Incorrect client-ID, not matching with body.", null);
            return new ResponseEntity<>(responseModel, BAD_REQUEST);
        }

        MqttClientEntity mqttClientEntity = new MqttClientEntity(mqttClient);
        mqttClientEntity.setActive(true);
        mqttClientEntity.setCreatedOn(new Date());

        boolean clientIdExists = clientRepository.checkIfMqttClientExists(clientId);
        if (clientIdExists) {
            ResponseModel responseModel = new ResponseModel(BAD_REQUEST.value(), "Client-ID already exists", null);
            return new ResponseEntity<>(responseModel, BAD_REQUEST);
        }

        try {
            clientRepository.createClient(mqttClientEntity);
            LOGGER.info("The client {} registered successfully with id:{}", mqttClientEntity.getClientId(),
                    mqttClientEntity.getId());
            ResponseModel responseModel = new ResponseModel(CREATED.value(), "Client " + mqttClientEntity.getClientId() +
                    " registered successfully", null);
            return new ResponseEntity<>(responseModel, CREATED);
        } catch (Exception e) {
            LOGGER.info("Exception occurred while registering client, {}", e.getMessage());
            ResponseModel responseModel = new ResponseModel(INTERNAL_SERVER_ERROR.value(), "Error occurred while registering client", null);
            return new ResponseEntity<>(responseModel, INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/v1/cloud/clients", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> getRegisteredClients() {
        LOGGER.info("Request received to get list of registered clients.");
        try {
            List<String> clients = clientRepository.getMqttClients();
            ResponseModel responseModel = new ResponseModel(OK.value(),
                    "Successful", clients);
            return new ResponseEntity<>(responseModel, OK);
        } catch (Exception e) {
            LOGGER.error("Exception occurred while fetching registered mqtt clients, {}", e.getMessage());
            ResponseModel responseModel = new ResponseModel(INTERNAL_SERVER_ERROR.value(),
                    "Error occurred while fetching registered clients", null);
            return new ResponseEntity<>(responseModel, INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/v1/cloud/client/{clientId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> getRegisteredClient(@PathVariable("clientId") String clientId) {
        LOGGER.info("Request received to get registered client with Id: {}", clientId);

        try {
            MqttClientEntity mqttClientEntity = clientRepository.getMqttClientByClientId(clientId);
            if (isNotNull(mqttClientEntity)) {
                ResponseModel responseModel = new ResponseModel(OK.value(),
                        "Successful", new MqttClient(mqttClientEntity));
                return new ResponseEntity<>(responseModel, OK);
            }
            ResponseModel responseModel = new ResponseModel(OK.value(),
                    "There is no registered client.", new MqttClient(mqttClientEntity));
            return new ResponseEntity<>(responseModel, OK);

        } catch (Exception e) {
            LOGGER.error("Exception occurred while getting mqtt properties for client: {}, {}", clientId,
                    e.getMessage());
            ResponseModel responseModel = new ResponseModel(INTERNAL_SERVER_ERROR.value(),
                    "Error occurred while getting properties of client " + clientId, null);
            return new ResponseEntity<>(responseModel,INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/v1/cloud/client/{clientId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> deRegisterClient(@PathVariable("clientId") String clientId) {
        LOGGER.info("Request received to de-register client with Id: {}", clientId);

        boolean clientIdExists = clientRepository.checkIfMqttClientExists(clientId);
        if (!clientIdExists) {
            ResponseModel responseModel = new ResponseModel(BAD_REQUEST.value(),
                    "Client-ID does not exist", null);
            return new ResponseEntity<>(responseModel, BAD_REQUEST);
        }

        try {
            clientRepository.deleteMqttClientById(clientId);
            ResponseModel responseModel = new ResponseModel(OK.value(),
                    "Client " + clientId + " was deleted successfully.", null);
            return new ResponseEntity<>(responseModel, OK);
        } catch (Exception e) {
            LOGGER.error("Exception occurred while deleting mqtt properties for client: {}, {}", clientId,
                    e.getMessage());
            ResponseModel responseModel = new ResponseModel(INTERNAL_SERVER_ERROR.value(),
                    "Error occurred while deleting client" + clientId, null);
            return new ResponseEntity<>(responseModel, INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/v1/cloud/clients", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> deRegisterClients() {
        LOGGER.info("Request received to de-register all registered clients.");
        try {
            clientRepository.deleteAllMqttClients();
            ResponseModel responseModel = new ResponseModel(OK.value(),
                    "All clients were deleted successfully.", null);
            return new ResponseEntity<>(responseModel, OK);
        } catch (Exception e) {
            LOGGER.error("Exception occurred while deleting mqtt properties for all clients, {}",
                    e.getMessage());
            ResponseModel responseModel = new ResponseModel(INTERNAL_SERVER_ERROR.value(),
                    "Error occurred while deleting all clients", null);
            return new ResponseEntity<>(responseModel, INTERNAL_SERVER_ERROR);
        }

    }

}
