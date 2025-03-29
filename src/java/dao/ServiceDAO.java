package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Service;
import context.DBContext;

/**
 *
 * @author thang
 */
public class ServiceDAO extends DBContext {

    private Connection connection;

    public ServiceDAO() {
        try {
            this.connection = getConnection(); // Initialize the connection
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null; // Set to null if connection fails
        }
    }

    public List<Service> getActiveService(int offset, int limit, String nameOrId, int categoryId, String sortBy, String sortDir, int minPrice, int maxPrice) {
        List<Service> services = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT s.*, si.image_link AS serviceImage, ss.service_status AS serviceStatus "
                + "FROM service s "
                + "LEFT JOIN service_image si ON s.service_id = si.service_id "
                + "LEFT JOIN service_status ss ON s.service_id = ss.service_id "
                + "LEFT JOIN category c ON c.category_id = s.category_id "
                + "WHERE 1=1 "
                + " AND ss.service_status = 1"
                + " AND si.type = 0"
                + " AND c.status = 1");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (s.service_title LIKE ? OR s.service_id = ?)");
        }

        if (categoryId != -1) {
            query.append(" AND (s.category_id = ?)");
        }

        if (minPrice > -1 && maxPrice > -1) {
            query.append(" AND s.service_price BETWEEN ? AND ?");
        }
        query.append(" ORDER BY ").append(sortBy).append(" ").append(sortDir.equalsIgnoreCase("ASC") ? "ASC" : "DESC");
        query.append(" LIMIT ? OFFSET ?");

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return services; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }

            if (categoryId != -1) {
                preparedStatement.setInt(index++, categoryId);
            }

            if (minPrice > -1 && maxPrice > -1) {
                preparedStatement.setInt(index++, minPrice);
                preparedStatement.setInt(index++, maxPrice);
            }
            preparedStatement.setInt(index++, limit);
            preparedStatement.setInt(index++, offset);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.setServiceId(rs.getInt("service_id"));
                s.setServiceTitle(rs.getString("service_title"));
                s.setServiceBi(rs.getString("service_bi"));
                s.setCategoryId(rs.getInt("category_id"));
                s.setServicePrice(rs.getDouble("service_price"));
                s.setServiceDiscount(rs.getDouble("service_discount"));
                s.setServiceDetail(rs.getString("service_detail"));
                s.setServiceRateStar(rs.getDouble("service_rateStar"));
                s.setServiceVote(rs.getInt("service_vote"));
                s.setServiceImage(rs.getString("serviceImage"));
                services.add(s);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return services;
    }

    public int countActiveService(String nameOrId, int categoryId, int minPrice, int maxPrice) {
        int count = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) "
                + "FROM service s "
                + "LEFT JOIN service_image si ON s.service_id = si.service_id "
                + "LEFT JOIN service_status ss ON s.service_id = ss.service_id "
                + "LEFT JOIN category c ON c.category_id = s.category_id "
                + "WHERE 1=1 "
                + " AND ss.service_status = 1"
                + " AND si.type = 0"
                + " AND c.status = 1");
        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (s.service_title LIKE ? OR s.service_id = ?)");
        }

        if (categoryId != -1) {
            query.append(" AND (s.category_id = ?)");
        }

        if (minPrice > -1 && maxPrice > -1) {
            query.append(" AND s.service_price BETWEEN ? AND ?");
        }

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return 0; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }
            if (categoryId != -1) {
                preparedStatement.setInt(index++, categoryId);
            }
            if (minPrice > -1 && maxPrice > -1) {
                preparedStatement.setInt(index++, minPrice);
                preparedStatement.setInt(index++, maxPrice);
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

    public List<Service> getActiveCategory() {
        List<Service> services = new ArrayList<>();
        String query = "SELECT * FROM category WHERE 1=1 AND status = 1 ORDER BY category_id DESC";

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return services; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.setCategoryTitle(rs.getString("category_name"));
                s.setCategoryId(rs.getInt("category_id"));
                s.setCategoryIcon(rs.getString("icon"));
                services.add(s);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return services;
    }

    public List<String> getAllServiceImages(int serviceId) {
        List<String> imageLinks = new ArrayList<>();
        String query = "SELECT image_link FROM service_image WHERE service_id = ?";

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return imageLinks; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, serviceId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    imageLinks.add(rs.getString("image_link"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageLinks;
    }

    public List<Service> getAllServicebyCategory(int categoryId) {
        List<Service> services = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT s.*, si.image_link AS serviceImage, ss.service_status AS serviceStatus "
                + "FROM service s "
                + "LEFT JOIN service_image si ON s.service_id = si.service_id "
                + "LEFT JOIN service_status ss ON s.service_id = ss.service_id "
                + "WHERE 1=1 "
                + " AND ss.service_status = 1"
                + " AND si.type = 0"
                + " AND category_id = ?");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {

            preparedStatement.setInt(1, categoryId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Service s = new Service();
                    s.setServiceId(rs.getInt("service_id"));
                    s.setServiceTitle(rs.getString("service_title"));
                    s.setServiceBi(rs.getString("service_bi"));
                    s.setCategoryId(rs.getInt("category_id"));
                    s.setServicePrice(rs.getDouble("service_price"));
                    s.setServiceDiscount(rs.getDouble("service_discount"));
                    s.setServiceDetail(rs.getString("service_detail"));
                    s.setServiceRateStar(rs.getDouble("service_rateStar"));
                    s.setServiceVote(rs.getInt("service_vote"));
                    s.setServiceImage(rs.getString("serviceImage"));
                    services.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    public Service getServicebyId(int serviceId) {
        Service s = new Service();
        StringBuilder query = new StringBuilder("SELECT s.*, si.image_link AS serviceImage, ss.service_status AS serviceStatus "
                + "FROM service s "
                + "LEFT JOIN service_image si ON s.service_id = si.service_id "
                + "LEFT JOIN service_status ss ON s.service_id = ss.service_id "
                + "WHERE 1=1 "
                + "AND si.type = 0");
        if (serviceId != -1) {
            query.append(" AND (s.service_id = ?)");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;

            if (serviceId != -1) {
                preparedStatement.setInt(index++, serviceId);
            }

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) { // Move cursor to the first row
                s.setServiceId(rs.getInt("service_id"));
                s.setServiceTitle(rs.getString("service_title"));
                s.setServiceBi(rs.getString("service_bi"));
                s.setCategoryId(rs.getInt("category_id"));
                s.setServicePrice(rs.getDouble("service_price"));
                s.setServiceDiscount(rs.getDouble("service_discount"));
                s.setServiceDetail(rs.getString("service_detail"));
                s.setServiceRateStar(rs.getDouble("service_rateStar"));
                s.setServiceVote(rs.getInt("service_vote"));
                s.setServiceImage(rs.getString("serviceImage"));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return s;
    }

    public int findServiceCategory(int serviceId) {
        String query = "SELECT category_id FROM service WHERE service_id = ?";
        int categoryId = -1; // Default value if not found

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, serviceId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) { // Use if instead of while since it's a single result
                    categoryId = rs.getInt("category_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }

    public List<Service> getAllBestServiceInfo() {
        List<Service> services = new ArrayList<>();
        String query = "SELECT s.*, si.image_link AS serviceImage, ss.service_status AS serviceStatus "
                + "FROM service s "
                + "LEFT JOIN service_image si ON s.service_id = si.service_id "
                + "LEFT JOIN service_status ss ON s.service_id = ss.service_id "
                + "WHERE 1=1 "
                + "AND ss.service_status = 1 "
                + " AND si.type = 0";
        if (connection == null) {
            System.err.println("Database connection is not available.");
            return services; // Return empty list if connection failed
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.setServiceId(rs.getInt("service_id"));
                s.setServiceTitle(rs.getString("service_title"));
                s.setServiceBi(rs.getString("service_bi"));
                s.setCategoryId(rs.getInt("category_id"));
                s.setServicePrice(rs.getDouble("service_price"));
                s.setServiceDiscount(rs.getDouble("service_discount"));
                s.setServiceDetail(rs.getString("service_detail"));
                s.setServiceRateStar(rs.getDouble("service_rateStar"));
                s.setServiceVote(rs.getInt("service_vote"));
                s.setServiceImage(rs.getString("serviceImage"));
                services.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    public int[] findBestNumber(List<Service> t) {
        // Sort the services by score (ServiceVote * ServiceRateStar) / 5 in descending order
        t.sort((a, b) -> {
            double scoreA = (double) (a.getServiceVote() * a.getServiceRateStar()) / 5;
            double scoreB = (double) (b.getServiceVote() * b.getServiceRateStar()) / 5;
            return Double.compare(scoreB, scoreA); // Sort descending
        });

        // Take the top 4 service IDs
        int topSize = Math.min(4, t.size()); // Handle case where < 4 services exist
        int[] bestIds = new int[topSize];

        for (int i = 0; i < topSize; i++) {
            bestIds[i] = t.get(i).getServiceId();
        }

        return bestIds;
    }

    public List<Service> findBestService() {
        // Step 1: Get all services with status = 1
        List<Service> allServices = getAllBestServiceInfo();

        // Step 2: Find the top 4 service IDs based on rating formula
        int[] bestServiceIds = findBestNumber(allServices);

        // Step 3: Retrieve details of the best services from allServices
        List<Service> bestServices = new ArrayList<>();
        for (Service service : allServices) {
            for (int id : bestServiceIds) {
                if (service.getServiceId() == id) {
                    bestServices.add(service);
                    break; // Move to the next service
                }
            }
        }

        return bestServices;
    }

    public boolean addService(Service service) {
        String query = "INSERT INTO Service (service_title, service_bi, service_created_date, category_id, "
                + "service_price, service_discount, service_detail) "
                + "VALUES (?, ?, CURDATE(), ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, service.getServiceTitle());
            preparedStatement.setString(2, service.getServiceBi());
            preparedStatement.setInt(3, service.getCategoryId());
            preparedStatement.setDouble(4, service.getServicePrice());
            preparedStatement.setDouble(5, service.getServiceDiscount());
            preparedStatement.setString(6, service.getServiceDetail());
//            preparedStatement.setInt(7, service.getServiceStatus());
//            preparedStatement.setString(7, service.getServiceImage());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        }
       catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //manager start form here
    public List<Service> getAllService(int offset, int limit, String nameOrId, int categoryId, int status, String sortBy, String sortDir) {
        List<Service> services = new ArrayList<>();
        if (connection == null) {
            System.err.println("Database connection is not available.");
            return services; // return empty database if the connection is not available
        }

        // Check sortBy value for avoiding SQL injection
        List<String> validSortColumns = List.of("service_id", "service_title", "service_price", "service_vote", "service_rateStar");
        if (!validSortColumns.contains(sortBy)) {
            sortBy = "service_id"; // Default value if input is invalid
        }

        // Check sortDir
        String orderDirection = sortDir.equalsIgnoreCase("ASC") ? "ASC" : "DESC";

        StringBuilder query = new StringBuilder("SELECT s.*, si.image_link AS serviceImage, ss.service_status AS serviceStatus "
                + "FROM service s "
                + "LEFT JOIN service_image si ON s.service_id = si.service_id "
                + "LEFT JOIN service_status ss ON s.service_id = ss.service_id "
                + "WHERE 1=1 "
                + " AND si.type = 0");

        if (nameOrId != null && !nameOrId.trim().isEmpty()) {
            query.append(" AND (s.service_title LIKE ? OR s.service_id = ?) ");
        }

        if (categoryId != -1) {
            query.append(" AND s.category_id = ? ");
        }

        if (status != -1) {
            query.append(" AND ss.service_status = ? ");
        }

        query.append(" ORDER BY ").append(sortBy).append(" ").append(orderDirection);
        query.append(" LIMIT ? OFFSET ?");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            if (nameOrId != null && !nameOrId.trim().isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }
            if (categoryId != -1) {
                preparedStatement.setInt(index++, categoryId);
            }
            if (status != -1) {
                preparedStatement.setInt(index++, status);
            }
            preparedStatement.setInt(index++, limit);
            preparedStatement.setInt(index++, offset);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.setServiceId(rs.getInt("service_id"));
                s.setServiceTitle(rs.getString("service_title"));
                s.setServiceBi(rs.getString("service_bi"));
                s.setCategoryId(rs.getInt("category_id"));
                s.setServicePrice(rs.getDouble("service_price"));
                s.setServiceDiscount(rs.getDouble("service_discount"));
                s.setServiceDetail(rs.getString("service_detail"));
                s.setServiceRateStar(rs.getDouble("service_rateStar"));
                s.setServiceVote(rs.getInt("service_vote"));
                s.setServiceImage(rs.getString("serviceImage"));
                services.add(s);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return services;
    }

    public List<Service> getTopRatedServices() {
        List<Service> services = new ArrayList<>();

        String query = "SELECT s.*, "
                + "       (SELECT si.image_link FROM service_image si WHERE si.service_id = s.service_id LIMIT 1) AS serviceImage "
                + "FROM service s "
                + "ORDER BY s.service_rateStar DESC "
                + "LIMIT 3";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setServiceId(rs.getInt("service_id"));
                service.setServiceTitle(rs.getString("service_title"));
                service.setServiceBi(rs.getString("service_bi"));
                service.setServiceCreatedDate(rs.getDate("service_created_date"));
                service.setServicePrice(rs.getDouble("service_price"));
                service.setServiceDiscount(rs.getDouble("service_discount"));
                service.setServiceDetail(rs.getString("service_detail"));
                service.setServiceRateStar(rs.getDouble("service_rateStar"));
                service.setServiceVote(rs.getInt("service_vote"));
                service.setServiceImage(rs.getString("serviceImage"));

                services.add(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }

    public int countService(String nameOrId, int categoryId) {
        int count = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) "
                + "FROM service s "
                + "LEFT JOIN service_image si ON s.service_id = si.service_id "
                + "LEFT JOIN service_status ss ON s.service_id = ss.service_id "
                + "LEFT JOIN category c ON s.category_id = c.category_id "
                + "WHERE 1=1 ");
        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (s.service_title LIKE ? OR s.service_id = ?)");
        }

        if (categoryId != -1) {
            query.append(" AND (s.category_id = ?)");
        }

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return 0; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }
            if (categoryId != -1) {
                preparedStatement.setInt(index++, categoryId);
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

    // Cập nhật trạng thái dịch vụ trong bảng service_status
//    public void updateStatus(int serviceId, int status) {
//        String sql = "UPDATE service_status SET service_status = ? WHERE service_id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setBoolean(1, status == 1); // Chuyển đổi int thành boolean
//            stmt.setInt(2, serviceId);
//            int rowsAffected = stmt.executeUpdate();
//            if (rowsAffected == 0) {
//                // Nếu không có bản ghi nào được cập nhật, có thể cần thêm bản ghi mới
//                insertStatus(serviceId, status);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error updating service status: " + e.getMessage());
//        }
//    }
//    // Phương thức hỗ trợ để thêm trạng thái nếu chưa tồn tại
//    private void insertStatus(int serviceId, int status) throws SQLException {
//        String sql = "INSERT INTO service_status (service_id, service_status) VALUES (?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, serviceId);
//            stmt.setBoolean(2, status == 1);
//            stmt.executeUpdate();
//        }
//    }
    public void insertServiceStatus(int serviceId, int status) throws SQLException {
        String queryStatus = "INSERT INTO service_status (service_id, service_status) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(queryStatus)) {
            ps.setInt(1, serviceId);
            ps.setInt(2, status);
            ps.executeUpdate();
        }
    }

    public void insertServiceImage(int serviceId, String imageLink) throws SQLException {
        String queryImage = "INSERT INTO service_image (service_id, image_link, type) VALUES (?, ?, 0)"; // Assuming type 0 is for thumbnail
        try (PreparedStatement ps = connection.prepareStatement(queryImage)) {
            ps.setInt(1, serviceId);
            ps.setString(2, imageLink);
            ps.executeUpdate();
        }
    }

    // Cập nhật thông tin dịch vụ trong bảng service và các bảng liên quan
    public boolean updateService(Service service) {
        boolean success = false;
        String updateServiceSql = "UPDATE service SET service_title = ?, service_bi = ?, category_id = ?, service_price = ?, service_discount = ?, service_detail = ? WHERE service_id = ?";
        String updateImageSql = "UPDATE service_image SET image_link = ? WHERE service_id = ? AND type = 0";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(updateServiceSql)) {
                ps.setString(1, service.getServiceTitle());
                ps.setString(2, service.getServiceBi());
                ps.setInt(3, service.getCategoryId());
                ps.setDouble(4, service.getServicePrice());
                ps.setDouble(5, service.getServiceDiscount());
                ps.setString(6, service.getServiceDetail());
                ps.setInt(7, service.getServiceId());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
            }

            if (service.getServiceImage() != null && !service.getServiceImage().isEmpty()) {
                try (PreparedStatement ps = connection.prepareStatement(updateImageSql)) {
                    ps.setString(1, service.getServiceImage());
                    ps.setInt(2, service.getServiceId());
                    ps.executeUpdate();
                }
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public Service getServiceById(int serviceId) {
        Service service = new Service();
        String query = "SELECT s.*, ss.service_status AS serviceStatus, si.image_link AS serviceImage "
                + "FROM service s "
                + "LEFT JOIN service_status ss ON s.service_id = ss.service_id "
                + "LEFT JOIN service_image si ON s.service_id = si.service_id AND si.type = 0 "
                + "WHERE s.service_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, serviceId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    service.setServiceId(rs.getInt("service_id"));
                    service.setServiceTitle(rs.getString("service_title"));
                    service.setServiceBi(rs.getString("service_bi"));
                    service.setServiceCreatedDate(rs.getDate("service_created_date"));
                    service.setCategoryId(rs.getInt("category_id"));
                    service.setServicePrice(rs.getDouble("service_price"));
                    service.setServiceDiscount(rs.getDouble("service_discount"));
                    service.setServiceDetail(rs.getString("service_detail"));
                    service.setServiceRateStar(rs.getDouble("service_rateStar"));
                    service.setServiceVote(rs.getInt("service_vote"));
                    service.setServiceStatus(rs.getInt("serviceStatus"));
                    service.setServiceImage(rs.getString("serviceImage"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service;
    }

    public Service getServiceVotebyId(int service_id) {
        Service service = null;
        String query = "SELECT service_rateStar, service_vote FROM service WHERE service_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, service_id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                service = new Service();
                service.setServiceId(service_id);
                service.setServiceRateStar(rs.getDouble("service_rateStar"));
                service.setServiceVote(rs.getInt("service_vote"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }

    public boolean updateServiceVoteAndRate(int serviceId, int serviceVote, double serviceRateStar) {
        String sql = "UPDATE service SET service_vote = ?, service_rateStar = ? WHERE service_id = ?";
        boolean isUpdated = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, serviceVote);
            preparedStatement.setDouble(2, serviceRateStar);
            preparedStatement.setInt(3, serviceId);

            int rowsAffected = preparedStatement.executeUpdate();
            isUpdated = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public boolean updateServiceStatus(int serviceId, int newStatus) {
        boolean success = false;
        String queue = "UPDATE service_status SET service_status = ? WHERE service_id = ?";

        try (
                PreparedStatement ps = connection.prepareStatement(queue)) {
            ps.setInt(1, newStatus);
            ps.setInt(2, serviceId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public List<Service> getService(int offset, int limit, String nameOrId, int categoryId, int status, String sortBy, String sortDir) {
        List<Service> services = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT s.*, si.image_link AS serviceImage, ss.service_status AS serviceStatus, c.category_name AS categoryName "
                + "FROM service s "
                + "LEFT JOIN service_image si ON s.service_id = si.service_id "
                + "LEFT JOIN service_status ss ON s.service_id = ss.service_id "
                + "LEFT JOIN category c ON s.category_id = c.category_id "
                + "WHERE 1=1 "
                + "AND si.type = 0 ");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (s.service_title LIKE ? OR s.service_id = ?)");
        }

        if (categoryId != -1) {
            query.append(" AND (s.category_id = ?)");
        }

        if (status != -1) {
            query.append(" AND (ss.service_status = ?)");
        }

        query.append(" ORDER BY ").append(sortBy).append(" ").append(sortDir.equalsIgnoreCase("ASC") ? "ASC" : "DESC");
        query.append(" LIMIT ? OFFSET ?");

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return services; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }

            if (categoryId != -1) {
                preparedStatement.setInt(index++, categoryId);
            }

            if (status != -1) {
                preparedStatement.setInt(index++, status);
            }

            preparedStatement.setInt(index++, limit);
            preparedStatement.setInt(index++, offset);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.setServiceId(rs.getInt("service_id"));
                s.setServiceTitle(rs.getString("service_title"));
                s.setServiceBi(rs.getString("service_bi"));
                s.setCategoryId(rs.getInt("category_id"));
                s.setServicePrice(rs.getDouble("service_price"));
                s.setServiceDiscount(rs.getDouble("service_discount"));
                s.setServiceDetail(rs.getString("service_detail"));
                s.setServiceRateStar(rs.getDouble("service_rateStar"));
                s.setServiceVote(rs.getInt("service_vote"));
                s.setServiceImage(rs.getString("serviceImage"));
                s.setCategoryTitle(rs.getString("categoryName"));
                s.setServiceStatus(rs.getInt("serviceStatus"));
                services.add(s);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return services;
    }

    public int getLatestService() {
        int id = -1;
        String sql = "SELECT service_id FROM service ORDER BY service_created_date DESC LIMIT 1";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("service_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

}
