package whiz;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.QoS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    @Autowired
    BlockingConnection connection;

    public void notify(String from, String to) throws Exception {
        String topic = String.format("user/%s/messages", to);

        connection.publish(topic, from.getBytes(), QoS.AT_LEAST_ONCE, false);
    }
}