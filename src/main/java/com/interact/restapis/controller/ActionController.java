package com.interact.restapis.controller;


import com.interact.restapis.model.Action;
import com.interact.restapis.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActionController {

    @Autowired
    private ActionService actionService;

    //Get all actions
    @GetMapping("/actions")
    public List<Action> getAllActions() {
        return actionService.getAllActions();
    }

    @GetMapping("/actions/{id}")
    public Action getAction(@PathVariable Long id){
        return actionService.getAction(id);
    }

    @PostMapping("/actions")
    public ResponseEntity<Action> addAction(@RequestBody Action action){
        Action action1 = actionService.addAction(action);
        if(action1 == null)
            return new ResponseEntity<>(action, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(action, HttpStatus.OK);
    }

    @PutMapping("/actions/{id}")
    public ResponseEntity<Action> updateAction(@RequestBody Action action, @PathVariable(value = "id") Long id){
        if(actionService.updateAction(action, id) == null)
            return new ResponseEntity<>(action, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(action, HttpStatus.OK);
    }

    @DeleteMapping("/actions/{id}")
    public ResponseEntity<Action> deleteAction(@PathVariable Long id){
        if(!actionService.deleteAction(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
