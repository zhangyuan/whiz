package whiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import whiz.entities.Message;
import whiz.repositories.MessageRepo;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/users")
public class UsersController {
    @Autowired
    MessageRepo messageRepo;

    @ResponseBody
    @RequestMapping(path = "{username}/messages", method = RequestMethod.GET)
    public List<Message> getReceivedMessages(@PathVariable("username") String username) {
        return messageRepo.findByToUser(username, 5);
    }
}
