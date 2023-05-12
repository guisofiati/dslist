package github.guisofiati.dslist.services;

import github.guisofiati.dslist.dto.GameListDTO;
import github.guisofiati.dslist.entities.GameList;
import github.guisofiati.dslist.projections.GameMinProjection;
import github.guisofiati.dslist.repositories.GameListRepository;
import github.guisofiati.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> gameListsEntity = gameListRepository.findAll();
        return gameListsEntity.stream().map(GameListDTO::new).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {
        List<GameMinProjection> list = gameRepository.searchByList(listId);
        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

//      int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);

        for (int i = min; i < max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }
}
