By default, when an endpoint expects a JSON document as input and a given controller method argument is annotated with @RequestBody, Spring will use Jackson databind features to map the incoming JSON document to a Java object. You don't need to use the Jackson's ObjectMapper directly, as Spring does it for you.

When you get some request in some data types like json/xml, the Spring MVC platform will try to deserialize this request attributes in some model object of your project.

But the platform itself don't provide a des-serialize implementation out of the box. So it will try to use some des-serializer provider in the classpath like jackson, jersey, gson, etc.

The JacksonConfiguration class is configured to handle the deserialization of LocalDateTime objects from JSON data sent by clients. It uses a specific date-time pattern ("yyyy-MM-dd HH:mm:ss") to correctly parse the date-time information provided by the client. Additionally, it is set to ignore unknown properties during deserialization to prevent errors when encountering unexpected fields in the JSON data.

read the aritcla for il8n
https://medium.com/@AlexanderObregon/how-spring-boot-configures-internationalization-i18n-6440bd597edc

elasticsearch
    works like mysql jpa
    Elasticsearch is a NoSQL database. 


retry

Spring Retry provides an ability to automatically re-invoke a failed operation. This is helpful where the errors may be transient (like a momentary network glitch).


https://www.baeldung.com/spring-retry

openfeign
