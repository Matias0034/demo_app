package com.example.demo.Infrastructure.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Application.UserCreator;
import com.example.demo.Domain.Model.User;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserCreator userCreator;

    private SessionRegistry sessionRegistry;

    @PostMapping("/save")
    public ResponseEntity<Void> saveDataUser(@RequestBody User user){

        System.out.println("dsjdsjjds");
        userCreator.create(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/session")
    public ResponseEntity<?> session(){
       
        org.springframework.security.core.userdetails.User use = null;
        List<Object> sessions = sessionRegistry.getAllPrincipals();
        String se = "";
        for(Object session : sessions){
            if(session instanceof org.springframework.security.core.userdetails.User){
                use = (org.springframework.security.core.userdetails.User) session;
            }
            List<SessionInformation> sessionInformation  = sessionRegistry.getAllSessions(session, false);
       
            for(SessionInformation seinf : sessionInformation){
                se = seinf.getSessionId();
            }
            
        }

        Map<String, Object> res = new HashMap<>();
        res.put("response", "hello world");
        res.put("se", se);
        res.put("sessionCore", use);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/csrf-token")
    public Map<String, String> csrf(CsrfToken token) {
        return Map.of(
            "headerName", token.getHeaderName(),
            "parameterName", token.getParameterName(),
            "token", token.getToken()
        );
    }
}
