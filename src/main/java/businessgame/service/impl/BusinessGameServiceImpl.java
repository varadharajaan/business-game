package businessgame.service.impl;


import businessgame.exception.FundingMoneyException;
import businessgame.model.BoardCell;
import businessgame.model.BusinessGameBoard;
import businessgame.model.Hotel;
import businessgame.model.Player;
import businessgame.service.BusinessGameService;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static businessgame.enums.CellType.*;
import static businessgame.enums.HotelType.SILVER;

public class BusinessGameServiceImpl implements BusinessGameService {

	private  static BusinessGameServiceImpl businessGameService;
	private BusinessGameServiceImpl() {

	}

	public static BusinessGameServiceImpl getInstance() {
		if(null == businessGameService) {
			synchronized (BusinessGameServiceImpl.class) {
				if(null == businessGameService){
					businessGameService = new BusinessGameServiceImpl();
				}
			}
		}
		return businessGameService;
	}


	@Override
	public BusinessGameBoard initalizeBoard(final int initialMoney) {
		if(initialMoney <= 0) {
			throw  new FundingMoneyException("money cannot be empty at start" );
		}
		final BusinessGameBoard businessGameBoard = new BusinessGameBoard();
		businessGameBoard.setBankMoney(initialMoney);

		List<BoardCell> cells = new ArrayList<>();
		cells.add(new BoardCell(J));
		cells.add(new BoardCell(H, Hotel.builder().hotelId(1).hotelName("aHotel").hotelType(SILVER).build()));
		cells.add(new BoardCell(L));
		cells.add(new BoardCell(H, Hotel.builder().hotelId(2).hotelName("bHotel").hotelType(SILVER).build()));
		cells.add(new BoardCell(E));
		cells.add(new BoardCell(L));
		cells.add(new BoardCell(H, Hotel.builder().hotelId(3).hotelName("CHotel").hotelType(SILVER).build()));
		cells.add(new BoardCell(L));
		cells.add(new BoardCell(H, Hotel.builder().hotelId(4).hotelName("bHotel").hotelType(SILVER).build()));
		cells.add(new BoardCell(J));
		businessGameBoard.setCells(cells);
		return businessGameBoard;
	}

	@Override
	public List<Player> playersInitalize(final int numberOfPlayer) {
		if(numberOfPlayer > 4) {
			throw new IllegalArgumentException("player count cannot be more than 4");
		}
		final List<Player> players = new ArrayList<>();
		players.add(new Player("player-1", 1, 1000, 0, new ArrayList<>()));
		players.add(new Player("player-2", 2, 1000, 0, new ArrayList<>()));
		players.add(new Player("player-3", 3, 1000, 0, new ArrayList<>()));
		return players;
	}

	@Override
	public int playDice(int turn, int playerId) {

		Map<String, Integer> map = new HashMap<>();

		map.put("1_1", 2);
		map.put("1_2", 2);
		map.put("1_3", 1);

		map.put("2_1", 4);
		map.put("2_2", 4);
		map.put("2_3", 2);

		/*map.put("3_1", 4);
		map.put("3_2", 4);
		map.put("3_3", 2);

		map.put("4_1", 2);
		map.put("4_2", 2);
		map.put("4_3", 1);

		map.put("5_1", 4);
		map.put("5_2", 4);
		map.put("5_3", 2);

		map.put("6_1", 4);
		map.put("6_2", 4);
		map.put("6_3", 2);

		map.put("7_1", 2);/*map.put("3_1", 4);
		map.put("3_2", 4);
		map.put("3_3", 2);

		map.put("4_1", 2);
		map.put("4_2", 2);
		map.put("4_3", 1);

		map.put("5_1", 4);
		map.put("5_2", 4);
		map.put("5_3", 2);

		map.put("6_1", 4);
		map.put("6_2", 4);
		map.put("6_3", 2);

		map.put("7_1", 2);
		map.put("7_2", 2);
		map.put("7_3", 1);

		map.put("8_1", 0);
		map.put("8_2", 0);
		map.put("8_3", 0);

		map.put("9_1", 0);
		map.put("9_2", 0);
		map.put("9_3", 0);

		map.put("10_1", 0);
		map.put("10_2", 0);
		map.put("10_3", 0);
*/
		map.put("7_2", 2);
		map.put("7_3", 1);

		map.put("8_1", 0);
		map.put("8_2", 0);
		map.put("8_3", 0);

		map.put("9_1", 0);
		map.put("9_2", 0);
		map.put("9_3", 0);

		map.put("10_1", 0);
		map.put("10_2", 0);
		map.put("10_3", 0);

		String str = "" + turn + "_" + playerId;
		return map.get(str);
	}

	@Override
	public void depositMoneyToBank(BusinessGameBoard businessGameBoard, int amountToDeposit) {
		businessGameBoard.setBankMoney(businessGameBoard.getBankMoney() + amountToDeposit);

	}

	@Override
	public void withdrawMoneyFromBank(BusinessGameBoard businessGameBoard, int amountToWithdraw) {
		businessGameBoard.setBankMoney(businessGameBoard.getBankMoney() - amountToWithdraw);
	}
}
