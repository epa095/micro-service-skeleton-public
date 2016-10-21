# Vimond Micro-service skeleton

## Synopsis

The purpose of this project is to provide a simple skeleton to create a Dropwizard micro-service talking to the Vimond platform.

## Architecture

The project comes in two parts:

- vimond-micro-service-skeleton-api : A library containing the data structures used to convert Json to Java Object.
- vimond-micro-service-skeleton-service : The Dropwizard that contains all the APIs, and the logic. It also references to the vimond-micro-service-skeleton-api library


## Current status

### External APIs

Right now there is a sample API to complete

```
  URL_PATH:PORT/sample/search
```

The purpose of this API is to give you a template to create other APIs that suit the need of you case.


### Internal APIS :

API consulted by the micro-service, right now the micro-service acts as a client and let you retrieve the Assets and Categories easily.

Note that you can query the micro-services to get all the assets using:

```
  URL_PATH:PORT/transfer/asset  // Retrieves all the assets.
  URL_PATH:PORT/transfer/category // Retrieves all the categories.
```

You can find more details about the usage of these APIs in vimond-micro-service-skeleton-service/src/main/java/com/vimond/service/client.

For an example how to instanciate a client, please see : vimond-micro-service-skeleton-service/src/main/java/com/vimond/VimondMicroServiceApp.


### Default values

Right now the default URL is "localhost" and the default port is "8080".

## Configuration

You can find the configuration file at vimond-micro-service-skeleton-service/src/main/ressources/config.yml

## Build

The build and execution can be done with gradle.

To build the code:

```
./gradlew build
```

To run the service:

```
./gradlew run
```


## Tests

TO DO.