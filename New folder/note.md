Elastic is basically a search engine, which is used for faster retrieval of any kind of data like products we are searching on any e-commerce website all those are powered by elastic search.

Application Search
Log Management
Monitoring
Full-text Search

https://ip:9200 

version: '3'
services:
  elasticsearch:
    image: elasticsearch:7.8.1
    ports:
      - 9200:9200
    environment:
      discovery.type: 'single-node'
      xpack.security.enabled: 'true'
      ELASTIC_PASSWORD: '<your_password>'
      ES_JAVA_OPTS: '-Xmx2g -Xms2g'
  kibana:
    image: kibana:7.8.1
    volumes:
      - ./kibana.yml:/usr/share/kibana/config/kibana.yml
    ports:
      - 5601:5601


without password
  elasticsearch:
    container_name: elasticsearch
    image: docker.elastic.co/elasticsearch/elasticsearch:8.6.2
    environment:
      - xpack.security.enabled=false
      - "discovery.type=single-node"
    networks:
      - esnet
    ports:
      - 9200:9200

application file
spring.data.elasticsearch.cluster-name=elasticsearch
spring.data.elasticsearch.cluster-nodes=localhost:9200
spring.data.elasticsearch.repositories.enabled=true

https://medium.com/shoutloudz/what-is-elastic-search-how-to-use-it-with-spring-boot-36c6bb32e22f
configuration
    @Configuration
    @EnableElasticsearchRepositories(basePackages
            = "repository package")
    @ComponentScan(basePackages = { "root backag" })
public class ElasticsearchClientConfig extends
        AbstractElasticsearchConfiguration {
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration =
                ClientConfiguration
                        .builder()
                        .connectedTo("ip:9200")
                        .build();

        return RestClients.create(clientConfiguration).rest();
    }
}

model
    @Document(indexName = "tabel name")
or  @Document(indexName = "tablename", shards = 1, replicas = 0, refreshInterval = "-1")


repository
p   ublic interface tableRepo extends ElasticsearchRepository<tableanem, Long> 

use as jpa


Kibana: Kibana is the user interface of the ELK stack that enables users to visualize the Elasticsearch documents and provides insights into them.