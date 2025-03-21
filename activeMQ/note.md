https://www.javatodev.com/how-to-use-activemq-with-spring-boot-rest-api/
docker pull rmohr/activemq
docker run -p 61616:61616 -p 8161:8161 rmohr/activemq
config properties
    spring.activemq.broker-url=tcp://192.168.150.108:61616
    spring.activemq.user=admin
    spring.activemq.password=admin
    activemq.destination=store
configuration
    JmsListenerContainerFactory
create a pruducer and consumer( @JmsListener)
use controller to send message


