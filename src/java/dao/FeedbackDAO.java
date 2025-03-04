package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Feedback;

public class FeedbackDAO extends DBContext {

    private Connection connection;

    public FeedbackDAO() {
        try {
            this.connection = getConnection(); // Initialize the connection
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null; // Set to null if connection fails
        }
    }

    public List<Feedback> getServiceFeedback(int offset, int limit, String nameOrId, int serviceId, String sortBy, String sortDir) {
        List<Feedback> reservations = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT f.*, u.user_image "
                + "FROM feedback f "
                + "LEFT JOIN users u ON f.user_id = u.user_id "
                + "WHERE 1=1 "
                + "AND service_id = ? ");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (content LIKE ? OR feedback_id = ?)");
        }

        query.append(" ORDER BY ").append(sortBy).append(" ").append(sortDir.equalsIgnoreCase("ASC") ? "ASC" : "DESC");
        query.append(" LIMIT ? OFFSET ?");

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return reservations; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;

            preparedStatement.setInt(index++, serviceId);

            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }

            preparedStatement.setInt(index++, limit);
            preparedStatement.setInt(index++, offset);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Feedback s = new Feedback();
                s.setFeedbackId(rs.getInt("feedback_id"));
                s.setCreatedDate(rs.getDate("created_date"));
                s.setStatus(rs.getInt("status"));
                s.setServiceId(rs.getInt("service_id"));
                s.setContent(rs.getString("content"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getBoolean("gender"));
                s.setEmail(rs.getString("email"));
                s.setMobile(rs.getString("mobile"));
                s.setRateStar(rs.getInt("rate_Star"));
                s.setUserId(rs.getInt("user_id"));
                s.setFeedbackImage(rs.getString("feedback_image"));
                s.setUserImage(rs.getString("user_image"));
                reservations.add(s);
            }
        } catch (Exception e) {
        }
        return reservations;
    }

    public int countServiceFeedback(String nameOrId, int serviceId) {
        int count = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) "
                + "FROM feedback f "
                + "LEFT JOIN users u ON f.user_id = u.user_id "
                + "WHERE 1=1 "
                + "AND service_id = ? ");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (content LIKE ? OR feedback_id = ?)");
        }

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return 0; // Return 0 if connection is unavailable
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            preparedStatement.setInt(index++, serviceId);

            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return count;
    }

}
