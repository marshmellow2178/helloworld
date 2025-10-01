package com.init330.helloworld.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User create(String name){
        User user = User.create(name);
        return userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User not found"));
    }

    public List<User> search(String name){
        if(name==null|| name.isEmpty()){
            return userRepository.findAll();
        }
        return userRepository.findByNameContaining(name);
    }

    @Transactional
    public void update(String name, Long id){
        User user = findById(id);
        user.update(name);
        userRepository.save(user);
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
