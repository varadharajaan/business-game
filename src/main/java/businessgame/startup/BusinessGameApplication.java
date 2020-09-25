package businessgame.startup;



import businessgame.model.BoardCell;
import businessgame.model.BusinessGameBoard;

import businessgame.model.Player;
import businessgame.service.BusinessGameService;
import businessgame.service.impl.BusinessGameServiceImpl;
import businessgame.utils.BusinessGameUtils;

import java.util.List;

import static java.lang.String.format;


public class BusinessGameApplication {

	public static void main(String[] args) {

		BusinessGameService businessGameService = BusinessGameServiceImpl.getInstance();
		BusinessGameBoard businessGameBoard = businessGameService.initalizeBoard(5000);
		List<BoardCell> cells = businessGameBoard.getCells();
		List<Player> players = businessGameService.playersInitalize(3);

		for (int turn = 1; turn <= 4; turn++) {
			for (Player player : players) {
				int currentLocation = player.getCurrentCellLocation();
				int diceFacingNumber = businessGameService.playDice(turn, player.getPlayerId());
				//System.out.println(player.getPlayerName() + " is at " + currentLocation + " diceFacingNumber : " + diceFacingNumber);
				int index = currentLocation + diceFacingNumber;

				if (index > 10) {
					index = index - 10;
				}

				player.setCurrentCellLocation(index);
				BoardCell targetCell = cells.get(index-1);
				BusinessGameUtils.processCellOperation(player, businessGameBoard, targetCell);
			}

		}

		players.forEach(it -> {
			int assetAmount = BusinessGameUtils.getAssetAmount(it.getHotels());
			System.out.println(it.getPlayerName() + " has total money " + it.getAmount()
					+ " and asset of amount " + assetAmount);
		});

		System.out.println(format("Balance at Bank %s", businessGameBoard.getBankMoney()));
	}

}
