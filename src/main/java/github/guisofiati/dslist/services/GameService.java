package github.guisofiati.dslist.services;

import github.guisofiati.dslist.dto.GameDTO;
import github.guisofiati.dslist.dto.GameMinDTO;
import github.guisofiati.dslist.entities.Game;
import github.guisofiati.dslist.projections.GameMinProjection;
import github.guisofiati.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> gamesEntity = gameRepository.findAll();
        return gamesEntity.stream().map(GameMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game gameEntity = gameRepository.findById(id).get();
        return new GameDTO(gameEntity);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) {
        List<GameMinProjection> gamesByListProjection = gameRepository.searchByList(listId);
        return gamesByListProjection.stream().map(GameMinDTO::new).toList();
    }
}
