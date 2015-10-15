package whiz;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConnection {
    @Bean
    public static BlockingConnection blockingConnection() throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost", 1883);

        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();
        return connection;
    }
}
