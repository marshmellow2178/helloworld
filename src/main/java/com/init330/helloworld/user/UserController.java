package com.init330.helloworld.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<?> createUser(
            @RequestBody UserRequest request) {
        User user = userService.create(request.getName());
        UserResponse response = new UserResponse(user.getId(), user.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        UserResponse response = new UserResponse(id, userService.findById(id).getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getUserList(
            @RequestParam(required = false) String keyword
    ){
        List<User> userList = userService.search(keyword);
        List<UserResponse> responseList = new ArrayList<>();
        for(User user : userList){
            UserResponse response = new UserResponse(user.getId(), user.getName());
            responseList.add(response);
        }
        return ResponseEntity.ok(responseList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @RequestBody UserRequest request,
            @PathVariable Long id
    ){
        userService.update(request.getName(), id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable Long id
    ){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
