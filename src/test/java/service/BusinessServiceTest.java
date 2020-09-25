package service;

import businessgame.exception.FundingMoneyException;
import businessgame.model.BoardCell;
import businessgame.model.BusinessGameBoard;
import businessgame.model.Player;
import businessgame.service.BusinessGameService;
import businessgame.service.impl.BusinessGameServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @Author Varadharajan on 21/09/20 10:29
 * @Projectname businessgame
 */

public class BusinessServiceTest {

    BusinessGameService businessGameService;


    @Before
    public void init() {
        businessGameService = BusinessGameServiceImpl.getInstance();
    }

    @Test
    public void initilizeBoard() {
        final BusinessGameBoard businessGameBoard = businessGameService.initalizeBoard(1000);
        assertThat(businessGameBoard.getBankMoney()).isEqualTo(1000);
        assertThat(businessGameBoard.getCells()).hasSize(10);
    }

    @Test
    public void initalizeWithNoMoneyGivesException() {
        assertThatExceptionOfType(FundingMoneyException.class).isThrownBy(() -> businessGameService.initalizeBoard(0))
                .withMessageContaining("money cannot be empty at start");
    }

    @Test
    public void initalizePlayers() {
        final List<Player> players = businessGameService.playersInitalize(3);
        assertThat(players.size()).isEqualTo(3);
        assertThat(players.get(0)).isEqualTo(new Player("player-1", 1, 1000, 0, new ArrayList<>()));
        assertThat(players.get(1)).isEqualTo(new Player("player-2", 2, 1000, 0, new ArrayList<>()));
        assertThat(players.get(2)).isEqualTo(new Player("player-3", 3, 1000, 0, new ArrayList<>()));
    }

    @Test
    public void initlizePlayerWithExceedCount() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> businessGameService.playersInitalize(5))
                .withMessageContaining("player count cannot be more than 4");
    }

    @Test
    public void depositMoneyToBank() {
        final BusinessGameBoard businessGameBoard = businessGameService.initalizeBoard(1000);
        businessGameService.depositMoneyToBank(businessGameBoard,1000);
        assertThat(businessGameBoard.getBankMoney()).isEqualTo(2000);
    }

    @Test
    public void withdrawMoneyFromBank() {
        final BusinessGameBoard businessGameBoard = businessGameService.initalizeBoard(1000);
        businessGameService.withdrawMoneyFromBank(businessGameBoard,1000);
        assertThat(businessGameBoard.getBankMoney()).isEqualTo(0);
    }

}

