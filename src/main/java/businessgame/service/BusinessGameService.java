package businessgame.service;


import businessgame.model.BusinessGameBoard;
import businessgame.model.Player;

import java.util.List;

public interface BusinessGameService {

	 BusinessGameBoard initalizeBoard(final int initialMoney);

	 List<Player> playersInitalize(final int numberOfPlayer);

	 int playDice(final int turn, final int playerId);

	 void depositMoneyToBank(final  BusinessGameBoard businessGameBoard, final int amountToDeposit);

	 void withdrawMoneyFromBank(final BusinessGameBoard businessGameBoard, final int amountToWithdraw);

}
