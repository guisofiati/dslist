package github.guisofiati.dslist.services;

import github.guisofiati.dslist.dto.GameMinDTO;
import github.guisofiati.dslist.entities.Game;
import github.guisofiati.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll() {
        List<Game> gamesEntity = gameRepository.findAll();
        return gamesEntity.stream().map(game -> new GameMinDTO(game)).toList();
    }
}
