# __*DigitalTwin-Cloud*__

The DigitalTwin-Cloud is an umbrella project for APIs and clients 
communicating with cloud services to register, communicate & maintain the 
heath of all IoT Appliances.

Under the umbrella project, there are set of modules to help the process.

***

### __*CloudAPI*__

This module exposes set of APIs to manage IoT clients & its connectivity with MQTT Hub.
The APIs are:
* Register Client:

  __`POST /v1/cloud/client/{client-id}`__
  
* Fetch All Clients:

  __`GET /v1/cloud/clients`__
  
* Fetch Client by Client-Id:

  __`GET /v1/cloud/client/{client-id}`__
  
* Delete Client by Client-Id:

  __`DELETE /v1/cloud/client/{client-id}`__

* Delete all clients:

  __`DELETE /v1/cloud/clients`__

***
### __*CloudGateway*__

This module acts as API Gateway to authenticate/authorize & redirect all incoming requests.



