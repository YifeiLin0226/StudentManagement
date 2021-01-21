package project.studentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.studentManagement.dao.BlockRepository;
import project.studentManagement.entity.Block;

import java.util.List;
import java.util.Optional;
@Service
public class BlockServiceImpl implements BlockService{

    @Autowired
    private BlockRepository blockRepository;

    @Override
    public List<Block> findAll() {
        return blockRepository.findAll();
    }

    @Override
    public Block findById(int theId) {
        Optional<Block> result = blockRepository.findById(theId);
        Block block = new Block();
        if(result.isPresent())
            block = result.get();
        return block;
    }

    @Override
    public void save(Block theBlock) {
        blockRepository.save(theBlock);
    }

    @Override
    public void deleteById(int theId) {
        blockRepository.deleteById(theId);
    }
}
