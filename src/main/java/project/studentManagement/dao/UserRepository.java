package project.studentManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.studentManagement.entity.User;

public interface UserRepository extends JpaRepository<User,String> {
}
