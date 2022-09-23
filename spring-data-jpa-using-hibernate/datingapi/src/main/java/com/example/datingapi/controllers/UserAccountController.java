package com.example.datingapi.controllers;

import com.example.datingapi.entities.Interest;
import com.example.datingapi.entities.UserAccount;
import com.example.datingapi.repos.InterestRepository;
import com.example.datingapi.repos.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAccountController {

    @Autowired
    private UserAccountRepository userRepo;
    
    @Autowired
    private InterestRepository interestRepo;

    @PostMapping("/users/register-user")
    public UserAccount registerUser(@RequestBody UserAccount userAccount) {
        return userRepo.save(userAccount);
    }

    @PostMapping("/interests/update")
    public Interest updateInterest(@RequestBody Interest interest) {

        UserAccount userAccount = userRepo.findById(interest.getUserAccountId()).get();
        interest.setUserAccount(userAccount);
        return interestRepo.save(interest);
    }

    @GetMapping("/users/get/all")
    public List<UserAccount> getUsers() {
        return userRepo.findAll();
    }

    @DeleteMapping("/users/delete/{interestId}")
    public void deleteInterest(@PathVariable("interestId") int id) {
        interestRepo.deleteById(id);
    }

    @GetMapping("/users/matches/{userId}")
    public List<UserAccount> findMatches(@PathVariable("userId") int id) {

        UserAccount userAccount = userRepo.findById(id).get();
        return userRepo.findMatches(userAccount.getAge(), userAccount.getCity(), userAccount.getCountry(), id);
    }
}
