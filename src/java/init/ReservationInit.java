/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package init;

import dao.ReservationDAO;
import java.util.List;
import model.Reservation;
import model.SearchResponse;
/**
 *
 * @author thang
 */
public class ReservationInit {
    ReservationDAO reservationDao = new ReservationDAO();
    public SearchResponse<Reservation> getReservation(int pageNo, int pageSize, String nameOrId , int userId) {
        int offset = pageNo * pageSize;
        List<Reservation> users = reservationDao.getReservation(offset, pageSize, nameOrId, userId ,"created_date", "ASC");
        int totalElements = reservationDao.countReservation(nameOrId,userId);
        return new SearchResponse<>(totalElements, users, pageNo, pageSize);
    }
    
}
