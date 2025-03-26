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

    public SearchResponse<Reservation> getReservation(int pageNo, int pageSize, String nameOrId, int userId, int day, int month, int year, String sortBy, String sortDir) {
        int offset = pageNo * pageSize;
        List<Reservation> users = reservationDao.getReservation(offset, pageSize, nameOrId, userId, day, month, year, sortBy, sortDir);
        int totalElements = reservationDao.countReservation(nameOrId, userId, day, month, year);
        return new SearchResponse<>(totalElements, users, pageNo, pageSize);
    }

    // Hàm mới: Lấy danh sách Reservation với phân trang và tìm kiếm theo reservation_id
    public SearchResponse<Reservation> getAllReservation(int pageNo, int pageSize, String reservationId, int status, int paymentStatus) throws Exception {
        int offset = pageNo * pageSize;

        // Nếu reservationId không rỗng, tìm kiếm theo reservation_id
        String search = (reservationId != null && !reservationId.trim().isEmpty()) ? reservationId : null;

        // Lấy danh sách Reservation từ DAO
        List<Reservation> reservations = reservationDao.getAllReservations(offset, pageSize, search, status, paymentStatus);

        // Đếm tổng số Reservation
        int totalElements = reservationDao.countAllReservations(search, status, paymentStatus);

        // Trả về đối tượng SearchResponse chứa thông tin phân trang và danh sách
        return new SearchResponse<>(totalElements, reservations, pageNo, pageSize);
    }

    // Hàm mới: Lấy chi tiết một Reservation theo reservation_id
    public Reservation getReservationById(int reservationId) {
        return reservationDao.getReservationById(reservationId);
    }
    
     public SearchResponse<Reservation> getReservationDetailOnStaff(int pageNo, int pageSize, String nameOrId, int staff_id, String sortBy, String sortDir) {
        int offset = pageNo * pageSize;
        List<Reservation> users = reservationDao.getReservationDetailofStaff(offset, pageSize, nameOrId, staff_id, sortBy, sortDir);
        int totalElements = reservationDao.countReservationDetailofStaff(nameOrId, staff_id);
        return new SearchResponse<>(totalElements, users, pageNo, pageSize);
    }
}
