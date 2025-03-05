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

    public List<Feedback> getActiveServiceFeedback(int offset, int limit, String nameOrId, int serviceId, String sortBy, String sortDir) {
        List<Feedback> reservations = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT f.*, u.user_image "
                + "FROM feedback f "
                + "LEFT JOIN users u ON f.user_id = u.user_id "
                + "WHERE 1=1 "
                + "AND service_id = ? "
                + "AND status = 1 ");

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
                + "AND service_id = ? "
                + "AND status = 1 ");

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

    public int getUserLatestVote(int userId) {
        String sql = "SELECT rate_Star "
                + "FROM feedback "
                + "WHERE user_id = ? "
                + "AND rate_Star != 0 "
                + "ORDER BY created_date DESC, feedback_id DESC "
                + "LIMIT 1;";
        int rate = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int index = 1;
            preparedStatement.setInt(index++, userId);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                rate = rs.getInt("rate_Star");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return rate;
    }

    public int addFeedback(int userId, int serviceId, String content, String name, boolean gender,
            String email, String mobile, int rateStar) {
        String sql = "INSERT INTO feedback (user_id, service_id, content, name, gender, email, mobile, rate_Star, status, created_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1, CURDATE())";
        int generatedId = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int index = 1;
            preparedStatement.setInt(index++, userId);
            preparedStatement.setInt(index++, serviceId);
            preparedStatement.setString(index++, content);
            preparedStatement.setString(index++, name);
            preparedStatement.setBoolean(index++, gender);
            preparedStatement.setString(index++, email);
            preparedStatement.setString(index++, mobile);
            preparedStatement.setInt(index++, rateStar);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1); // Get the generated feedback_id
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return generatedId;
    }

    public List<Feedback> getAllServiceFeedback(int offset, int limit, String nameOrId, int serviceId, int status, int ratestar, String sortBy, String sortDir) {
        List<Feedback> reservations = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT f.*, u.user_image, s.service_title "
                + "FROM feedback f "
                + "LEFT JOIN users u ON f.user_id = u.user_id "
                + "LEFT JOIN service s ON f.service_id = s.service_id "
                + "WHERE 1=1 ");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (f.content LIKE ? OR f.feedback_id = ?)");
        }

        if (serviceId != -1) {
            query.append(" AND f.service_id = ? ");
        }

        if (status != -1) {
            query.append(" AND f.status = ? ");
        }

        if (ratestar != -1) {
            query.append(" AND f.rate_Star = ? ");
        }

        query.append(" ORDER BY ").append(sortBy).append(" ").append(sortDir.equalsIgnoreCase("ASC") ? "ASC" : "DESC");
        query.append(" LIMIT ? OFFSET ?");

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return reservations; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;

            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }

            if (serviceId != -1) {
                preparedStatement.setInt(index++, serviceId);
            }

            if (status != -1) {
                preparedStatement.setInt(index++, status);
            }

            if (ratestar != -1) {
                preparedStatement.setInt(index++, ratestar);
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
                s.setUserImage(rs.getString("u.user_image"));
                s.setServiceTitle(rs.getString("s.service_title"));
                reservations.add(s);
            }
        } catch (Exception e) {
        }
        return reservations;
    }

    public int countAllServiceFeedback(String nameOrId, int serviceId, int status, int ratestar) {
        int count = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) "
                + "FROM feedback f "
                + "LEFT JOIN users u ON f.user_id = u.user_id "
                + "LEFT JOIN service s ON f.service_id = s.service_id "
                + "WHERE 1=1 ");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (f.content LIKE ? OR f.feedback_id = ?)");
        }

        if (serviceId != -1) {
            query.append(" AND f.service_id = ?");
        }

        if (status != -1) {
            query.append(" AND f.status = ?");
        }

        if (ratestar != -1) {
            query.append(" AND f.rate_Star = ?");
        }

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return 0; // Return 0 if connection is unavailable
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;

            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }

            if (serviceId != -1) {
                preparedStatement.setInt(index++, serviceId);
            }

            if (status != -1) {
                preparedStatement.setInt(index++, status);
            }

            if (ratestar != -1) {
                preparedStatement.setInt(index++, ratestar);
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

    public boolean updateFeedbackStatus(int feedbackId, int status) {
        String query = "UPDATE feedback SET status = ? WHERE feedback_id = ?";

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return false; // Return false if the connection is unavailable
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, feedbackId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0; // Return true if at least one row was updated
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

}
