package project.studentManagement.service;

import project.studentManagement.entity.Block;


import java.util.List;

public interface BlockService {
    public List<Block> findAll();

    public Block findById(int theId);

    public void save(Block theBlock);

    public void deleteById(int theId);
}
