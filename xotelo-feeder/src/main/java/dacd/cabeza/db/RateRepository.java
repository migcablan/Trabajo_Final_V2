package dacd.cabeza.db;

import dacd.cabeza.model.HotelRate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RateRepository {

	private static final String INSERT_SQL = """
        INSERT INTO hotel_rates 
        (provider_code, provider_name, rate, tax, currency, check_in, check_out, rooms_requested, adults, children)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";

	private static final String SELECT_ALL_SQL = "SELECT * FROM hotel_rates";

	public void saveRates(List<HotelRate> rates) throws SQLException {
		if (rates == null || rates.isEmpty()) return;

		try (Connection conn = DriverManager.getConnection(DatabaseManager.DB_URL);
			 PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

			for (HotelRate rate : rates) {
				pstmt.setString(1, rate.getProviderCode());
				pstmt.setString(2, rate.getProviderName());
				pstmt.setDouble(3, rate.getRate());
				pstmt.setDouble(4, rate.getTax());
				pstmt.setString(5, rate.getCurrency());
				pstmt.setString(6, rate.getCheckIn());
				pstmt.setString(7, rate.getCheckOut());
				pstmt.setInt(8, rate.getRoomsRequested());
				pstmt.setInt(9, rate.getAdults());
				pstmt.setInt(10, rate.getChildren());
				pstmt.addBatch();
			}

			pstmt.executeBatch();
		}
	}

	public List<HotelRate> getAllRates() throws SQLException {
		List<HotelRate> rates = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DatabaseManager.DB_URL);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {

			while (rs.next()) {
				HotelRate rate = new HotelRate();
				rate.setProviderCode(rs.getString("provider_code"));
				rate.setProviderName(rs.getString("provider_name"));
				rate.setRate(rs.getDouble("rate"));
				rate.setTax(rs.getDouble("tax"));
				rate.setCurrency(rs.getString("currency"));
				rate.setCheckIn(rs.getString("check_in"));
				rate.setCheckOut(rs.getString("check_out"));
				rate.setRoomsRequested(rs.getInt("rooms_requested"));
				rate.setAdults(rs.getInt("adults"));
				rate.setChildren(rs.getInt("children"));

				rates.add(rate);
			}
		}

		return rates;
	}
}
