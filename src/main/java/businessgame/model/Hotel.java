package businessgame.model;


import businessgame.enums.HotelType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
public class Hotel {

	@NotNull
	private int hotelId;
	@NotNull
	private String hotelName;
	@NotNull
	private Player hotelOwner;
	@NotNull
	private HotelType hotelType;

	public Hotel(int hotelId, String hotelName, HotelType hotelType) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.hotelType = hotelType;
	}

}
