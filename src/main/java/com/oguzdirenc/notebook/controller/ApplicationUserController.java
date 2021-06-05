package com.oguzdirenc.notebook.controller;
import com.oguzdirenc.notebook.domain.ApplicationUser;
import com.oguzdirenc.notebook.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationUserController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApplicationUser> saveUser(@Valid @RequestBody ApplicationUser applicationUser){
        return new ResponseEntity<>(applicationUserService.saveApplicationUser(applicationUser), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ApplicationUser>> getAllUsers(){
        return new ResponseEntity<>(applicationUserService.getAllUsers(),HttpStatus.OK);
    }

}
