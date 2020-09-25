package utils;

import businessgame.model.BusinessGameBoard;
import businessgame.model.Player;
import businessgame.service.BusinessGameService;
import businessgame.service.impl.BusinessGameServiceImpl;
import businessgame.utils.BusinessGameUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BusinessGameUtilsTest {

    BusinessGameService businessGameService;

    @Before
    public void init() {
        businessGameService = BusinessGameServiceImpl.getInstance();
    }

    @Test
    public void processOperationForPlayer() {
        final BusinessGameBoard businessGameBoard = businessGameService.initalizeBoard(1000);
        final List<Player> players = businessGameService.playersInitalize(3);
        BusinessGameUtils.processCellOperation(players.get(0),businessGameBoard,businessGameBoard.getCells().get(0));
    }
}
