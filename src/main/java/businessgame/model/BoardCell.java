package businessgame.model;


import businessgame.enums.CellType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
public class BoardCell {

	@NotNull
	private CellType cellType;
	
	private Hotel hotel;

	public BoardCell(CellType cellType, Hotel hotel) {
		super();
		this.cellType = cellType;
		this.hotel = hotel;
	}

	public BoardCell(CellType cellType) {
		super();
		this.cellType = cellType;
	}
}
