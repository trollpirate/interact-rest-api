package com.interact.restapis.service;

import com.interact.restapis.model.Action;
import com.interact.restapis.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    //Get all actions
    public List<Action> getAllActions(){
        return actionRepository.findAll();
    }

    //Get one action
    public Action getAction(Long id) {
        return actionRepository.findById(id).orElse(null);
    }

    //Add an action
    public Action addAction(Action action){
        return actionRepository.save(action);
    }

    //Update an action
    public Action updateAction(Action action, Long id){
        Optional<Action> actionOptional = actionRepository.findById(id);
        if(!actionOptional.isPresent())
            return null;
        return actionRepository.save(action);
    }

    //Delete an action
    public boolean deleteAction(Long id){
        Optional<Action> actionOptional = actionRepository.findById(id);
        if(!actionOptional.isPresent()) {
            return false;
        }
        actionRepository.deleteById(id);
        return true;
    }

}
