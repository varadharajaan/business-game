package businessgame.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Player {

	@NotNull
	private String playerName;
	@NotNull
	private int amount;
	@NotNull
	private int playerId;
	@NotNull
	private int currentCellLocation;

	private List<Hotel> hotels;

	public Player(String playerName, int playerId, int amount, int currentCellLocation, List<Hotel> hotels) {
		super();
		this.playerName = playerName;
		this.amount = amount;
		this.currentCellLocation = currentCellLocation;
		this.hotels = hotels;
		this.playerId = playerId;
	}
}
