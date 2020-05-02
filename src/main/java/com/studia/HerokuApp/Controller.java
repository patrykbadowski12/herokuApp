package com.studia.HerokuApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/index")
    public String hello(Model model){
        return "index";
    }

    @GetMapping(value = "/user")
    public String user(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String username = "";
        String avatar = "";
        HashMap<String, String> objects = new HashMap<>();
        if (((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().equalsIgnoreCase("github")){
            username = (String) token.getPrincipal().getAttributes().get("login");
            avatar = (String) token.getPrincipal().getAttributes().get("avatar_url");
        }
        if (((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().equalsIgnoreCase("google")){
            username = (String) token.getPrincipal().getAttributes().get("email");
            avatar = (String) token.getPrincipal().getAttributes().get("picture");
        }

        User user = userRepository.findByName(username);
        if (user == null){
            user = new User(username, avatar, LocalDateTime.now(), LocalDateTime.now(), 1);
        } else {
            user.setCounter(user.getCounter()+1);
            user.setLastVisit(LocalDateTime.now());
        }
        userRepository.save(user);

        objects.put("username" , username);
        objects.put("avatar", avatar);
        model.addAllAttributes(objects);
        return "user";
    }
}
