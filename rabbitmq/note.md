In a case of messaging between applications written in Java, the JMS (Java Message Service) API is commonly used. For interoperability between different vendors and platforms, we won’t be able to use JMS clients and brokers. This is where AMQP comes in handy.
AMQP is a platform-neutral binary protocol standard, libraries can be written in different programming languages, and run on different environments.

AMQP entities – we create entities with the Message, Queue, Binding, and Exchange classes
Connection Management – we connect to our RabbitMQ broker by using a CachingConnectionFactory
Message Publishing – we use a RabbitTemplate to send messages
Message Consumption – we use a @RabbitListener to read messages from a queue

At a high level, fanout exchanges will broadcast the same message to all bound queues, while topic exchanges use a routing key for passing messages to a particular bound queue or queues.

https://www.baeldung.com/rabbitmq-spring-amqp


 JMS is a Java-specific API for messaging, making it an excellent choice for Java-based applications that need to communicate within a controlled ecosystem. It provides a standardized way to interact with message brokers and is widely used in enterprise Java applications.

AMQP, on the other hand, is a platform-neutral protocol designed for interoperability across different programming languages, platforms, and vendors. It is better suited for distributed systems where components are written in various languages or run on different platforms. AMQP's flexibility and vendor neutrality make it ideal for heterogeneous environments.