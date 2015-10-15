package whiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import whiz.entities.Message;
import whiz.repositories.MessageRepo;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-servlet.xml")
@WebAppConfiguration
public class MessageRepoTest {
    @Autowired
    MessageRepo repo;

    @Test
    public void should_create_message(){
        Message message = new Message();
        message.setFrom("foo");
        message.setTo("bar");
        message.setContent("echo");

        repo.save(message);

        List<Message> all = repo.findAll(50);
        assertEquals(1, all.size());
    }


    @Test
    public void should_find_messages_by_receiver(){
        Message message = new Message();
        message.setFrom("foo");
        message.setTo("bar");
        message.setContent("echo");

        repo.save(message);

        List<Message> all = repo.findByToUser("bar", 50);
        assertEquals(1, all.size());
    }
}
