package businessgame.utils;

import businessgame.enums.CellType;
import businessgame.enums.HotelType;
import businessgame.exception.FundsNotAvailableException;
import businessgame.model.BoardCell;
import businessgame.model.BusinessGameBoard;
import businessgame.model.Hotel;
import businessgame.model.Player;
import businessgame.service.BusinessGameService;
import businessgame.service.impl.BusinessGameServiceImpl;
import lombok.experimental.UtilityClass;

import java.util.List;

import static businessgame.enums.CellType.J;
import static businessgame.enums.HotelType.GOLD;
import static businessgame.enums.HotelType.PLATINUM;
import static java.lang.String.format;

@UtilityClass
public class BusinessGameUtils {


    BusinessGameService businessGameService = BusinessGameServiceImpl.getInstance();

    public int getAssetAmount(List<Hotel> hotels) {
        int amount = 0;
        if (!hotels.isEmpty()) {
            for (Hotel hotel : hotels) {
                amount = amount + hotel.getHotelType().getValue();

            }
        }
        return amount;
    }

    public  void processCellOperation(Player player, BusinessGameBoard businessGameBoard, BoardCell targetCell) {

        switch (targetCell.getCellType()) {
            case J:
                player.setAmount(player.getAmount() - J.getValue());
                businessGameService.depositMoneyToBank(businessGameBoard, J.getValue());
                break;
            case H:
                Hotel hotel = targetCell.getHotel();
                Player hotelOwner = hotel.getHotelOwner();
                HotelType hotelType = hotel.getHotelType();
                if (null != hotelOwner) {
                    if (player.getPlayerId() == hotel.getHotelOwner().getPlayerId()) {
                        upgradeHotel(player, businessGameBoard, hotel, hotelType);
                    } else {
                        payRent(player, hotelOwner, hotelType);
                    }
                } else {
                    buyHotel(player, businessGameBoard, hotel, hotelType);
                }
                break;
            case L:
                businessGameService.withdrawMoneyFromBank(businessGameBoard, J.getValue());
                player.setAmount(player.getAmount() + CellType.L.getValue());
                break;
            case E:
                break;
            default:
                break;
        }

    }

    private  void buyHotel(Player player, BusinessGameBoard businessGameBoard, Hotel hotel, HotelType hotelType) {
        if (player.getAmount() >= hotelType.getValue()) {
            businessGameService.depositMoneyToBank(businessGameBoard, hotelType.getValue());
            player.setAmount(player.getAmount() - hotelType.getValue());
            hotel.setHotelOwner(player);
            player.getHotels().add(hotel);
        } else {
            throw new FundsNotAvailableException("You don't have enough money to buy this hotel");
        }
    }

    private  void payRent(Player player, Player hotelOwner, HotelType hotelType) {
        if (player.getAmount() >= hotelType.getRent()) {
            hotelOwner.setAmount(hotelOwner.getAmount() + hotelType.getRent());
            player.setAmount(player.getAmount() - hotelType.getRent());
        } else {
            throw new FundsNotAvailableException("You don't have enough money to pay rent");
        }
    }

    private  void upgradeHotel(Player player, BusinessGameBoard businessGameBoard, Hotel hotel, HotelType hotelType) {
        if (PLATINUM != hotel.getHotelType()) {
            HotelType upgradeToType = getNextUpgrade(hotel.getHotelType());
            int deltaToPay = upgradeToType.getValue() - hotel.getHotelType().getValue();
            player.setAmount(player.getAmount() - deltaToPay);
            businessGameService.depositMoneyToBank(businessGameBoard, deltaToPay);
            player.getHotels().remove(hotel);
            player.getHotels().add(Hotel.builder().hotelName("aGoldHotel").hotelOwner(player).hotelType(upgradeToType).build());
        } else {
            System.out.println(format("%s can't be upgraded",PLATINUM));
        }
    }

    private  HotelType getNextUpgrade(HotelType hotelType) {
        switch (hotelType) {
            case SILVER:
                return GOLD;
            case GOLD:
                return PLATINUM;
            default:
                break;
        }
        return null;
    }
}
