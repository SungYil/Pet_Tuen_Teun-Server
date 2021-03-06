package DB;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.Reservation;

@Repository("ReservationDAO")
public class ReservationDAO extends DAO {
	public ReservationDAO() {
		super();
	}
	
	private final String tableName = "reservation";
	
	public List<Reservation> load() {
		List<Reservation> list = null;
		String sql = "SELECT * FROM " + tableName;
		try {
			rs = execute(sql);
			list = new ArrayList<Reservation>();
			while (rs.next()) {
				Reservation reservation = new Reservation(
						rs.getString("id"),
						rs.getString("hospitalMemberID"),
						rs.getString("customerID"),
						rs.getString("reservationType"),
						rs.getString("reservationTime"),
						(byte) rs.getInt("isExecuted"));
				list.add(reservation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public byte save(List<Reservation> reservations) {
		List<String> sqls = new ArrayList<String>();
		sqls.add("DELETE FROM " + tableName);
		for (Reservation reservation : reservations) {
			sqls.add("INSERT INTO " + tableName + " VALUES ("
					+ reservation.getId() + "','"
					+ reservation.getHospitalID() + "','"
					+ reservation.getCustomerID() + "','"
					+ reservation.getReservationType() + "','"
					+ reservation.getReservationDate() + ")");
		}
		execute(sqls);
		close();
		return 1;
	}
}
