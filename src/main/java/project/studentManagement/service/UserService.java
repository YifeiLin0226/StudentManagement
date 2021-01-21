package project.studentManagement.service;

import project.studentManagement.entity.Block;
import project.studentManagement.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(String username);

    public void save(User user);

    public void deleteById(String username);
}
