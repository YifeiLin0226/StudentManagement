package project.studentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.studentManagement.dao.UserRepository;
import project.studentManagement.entity.Block;
import project.studentManagement.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String username) {
        Optional<User> result = userRepository.findById(username);
        User theUser = new User();
        if(result.isPresent()){
            theUser = result.get();
        }
        return theUser;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public void deleteById(String username) {
        userRepository.deleteById(username);

    }
}
