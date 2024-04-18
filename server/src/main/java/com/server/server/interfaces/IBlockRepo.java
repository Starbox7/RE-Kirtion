package com.server.server.interfaces;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.BlockModel;
import java.util.List;

public interface IBlockRepo extends JpaRepository<BlockModel, String> {
  Optional<BlockModel> findBlockByUuid(String uuid);
  
  List<BlockModel> findAllBlockByPageUuid(String pageUuid);
}
