package businessgame.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BusinessGameBoard {

	@NotNull
	private int bankMoney;

	private List<BoardCell> cells;

}
