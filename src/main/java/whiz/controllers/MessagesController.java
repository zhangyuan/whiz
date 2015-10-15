package whiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import whiz.NotificationService;
import whiz.entities.Message;
import whiz.repositories.MessageRepo;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    MessageRepo messageRepo;

    @Autowired
    NotificationService notificationService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Message> index() {
        return messageRepo.findAll(5);
    }

    @ResponseBody
    @RequestMapping(path="{from}/{to}/{content}", method = RequestMethod.GET)
    public Message create(@PathVariable String from, @PathVariable String to, @PathVariable String content) throws Exception {
        Message message = new Message();
        message.setFrom(from);
        message.setTo(to);
        message.setContent(content);

        messageRepo.save(message);
        notificationService.notify(from, to);

        return message;
    }

}
