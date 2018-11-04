# flickrService
Service to collect and retrieve flickr images

If *flickr.initial.tags*is not empty, application retrieve some images from Flickr at startUp.

if *dev* profile is active the service retrieve less images than *prod* profile.


## Configuration
The files *application.properties* and *application-test.properties* manage some properties.

```
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=flickrImages
spring.profiles.active=dev 

storage.folder=/tmp/flickr/

flickr.apiKey=YourApiKey
flickr.secret=YourSecret

flickr.initial.tags=Lemur,Tiger
```
- choose mongodb and profile
- choose folder in fileSystem where save images using storage.folder
- flickr.apiKey and flickr.secret are needed to use FlickrAPI
- use "flickr.initial.tags" to set the initial (optional= import



## Run
This service is a Spring Boot App, run via maven:
```
mvn clean install spring-boot:run
```

## Run and Configuration via Maven
use properties above in mvn command:
i.e :
```
mvn clean install spring-boot:run -Dstorage.folder=/tmp/flickr/ -Dflickr.initial.tags=Lemur,Tiger
```
## Usage
whren the service is started some Rest methods are available. Use Swagger to check interfaces

i.e.: http://localhost:8080/swagger-ui.html

- http://localhost:8080/pingFlickr test the connection to Flickr
- http://localhost:8080/download retrieve images from Flickr using tags
- http://localhost:8080/search retrieve information in Local DB/Storage using tags(in AND or OT mode) and/or title
