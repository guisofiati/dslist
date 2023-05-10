package github.guisofiati.dslist.services;

import github.guisofiati.dslist.dto.GameListDTO;
import github.guisofiati.dslist.entities.GameList;
import github.guisofiati.dslist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> gameListsEntity = gameListRepository.findAll();
        return gameListsEntity.stream().map(GameListDTO::new).toList();
    }
}
