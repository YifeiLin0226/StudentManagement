package project.studentManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.studentManagement.entity.Block;

public interface BlockRepository extends JpaRepository<Block,Integer> {
}
