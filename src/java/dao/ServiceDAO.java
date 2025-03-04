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

    private Connection connectionection;

    public ServiceDAO() {
        try {
            this.connectionection = getConnection(); // Initialize the connectionection
        } catch (Exception e) {
            e.printStackTrace();
            this.connectionection = null; // Set to null if connectionection fails
        }
    }

    public List<Service> getActiveService(int offset, int limit, String nameOrId, int categoryId, String sortBy, String sortDir, int minPrice, int maxPrice) {
        List<Service> services = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT s.*, si.image_link AS serviceImage, ss.service_status AS serviceStatus "
                + "FROM service s "
                + "LEFT JOIN service_image si ON s.service_id = si.service_id "
                + "LEFT JOIN service_status ss ON s.service_id = ss.service_id "
                + "WHERE 1=1 "
                + " AND ss.service_status = 1"
                + " AND si.type = 0");

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

        if (connectionection == null) {
            System.err.println("Database connectionection is not available.");
            return services; // Return empty list if connectionection failed
        }

        try (PreparedStatement preparedStatement = connectionection.prepareStatement(query.toString())) {
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
                + "WHERE 1=1 "
                + " AND ss.service_status = 1"
                + " AND si.type = 0");
        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (s.service_title LIKE ? OR s.service_id = ?)");
        }

        if (categoryId != -1) {
            query.append(" AND (s.category_id = ?)");
        }

        if (minPrice > -1 && maxPrice > -1) {
            query.append(" AND s.service_price BETWEEN ? AND ?");
        }

        if (connectionection == null) {
            System.err.println("Database connectionection is not available.");
            return 0; // Return empty list if connectionection failed
        }

        try (PreparedStatement preparedStatement = connectionection.prepareStatement(query.toString())) {
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
        String query = "SELECT * FROM category WHERE 1=1 ORDER BY category_id DESC";

        if (connectionection == null) {
            System.err.println("Database connectionection is not available.");
            return services; // Return empty list if connectionection failed
        }

        try (PreparedStatement preparedStatement = connectionection.prepareStatement(query)) {

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

        if (connectionection == null) {
            System.err.println("Database connectionection is not available.");
            return imageLinks; // Return empty list if connectionection failed
        }

        try (PreparedStatement preparedStatement = connectionection.prepareStatement(query)) {
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

        try (PreparedStatement preparedStatement = connectionection.prepareStatement(query.toString())) {

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
        try (PreparedStatement preparedStatement = connectionection.prepareStatement(query.toString())) {
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

        try (PreparedStatement preparedStatement = connectionection.prepareStatement(query)) {

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
        if (connectionection == null) {
            System.err.println("Database connectionection is not available.");
            return services; // Return empty list if connectionection failed
        }
        try (PreparedStatement preparedStatement = connectionection.prepareStatement(query)) {
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

    //manager start form here
    public List<Service> getAllService(int offset, int limit, String nameOrId, int categoryId, int status, String sortBy, String sortDir) {
        List<Service> services = new ArrayList<>();
        if (connectionection == null) {
            System.err.println("Database connectionection is not available.");
            return services; // return empty database if the connectionection is not available
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

        try (PreparedStatement preparedStatement = connectionection.prepareStatement(query.toString())) {
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

        try (PreparedStatement preparedStatement = connectionection.prepareStatement(query)) {
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

    public int countAllServices(String nameOrId, int categoryId, int status) {
        int total = 0;
        if (connectionection == null) {
            return total;
        }

        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM service s LEFT JOIN service_status ss ON s.service_id = ss.service_id WHERE 1=1 ");

        if (nameOrId != null && !nameOrId.trim().isEmpty()) {
            query.append(" AND (s.service_title LIKE ? OR s.service_id = ?) ");
        }
        if (categoryId != -1) {
            query.append(" AND s.category_id = ? ");
        }
        if (status != -1) {
            query.append(" AND ss.service_status = ? ");
        }

        try (PreparedStatement preparedStatement = connectionection.prepareStatement(query.toString())) {
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

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    // Cập nhật trạng thái dịch vụ trong bảng service_status
    public void updateStatus(int serviceId, int status) {
        String sql = "UPDATE service_status SET service_status = ? WHERE service_id = ?";
        try (PreparedStatement stmt = connectionection.prepareStatement(sql)) {
            stmt.setBoolean(1, status == 1); // Chuyển đổi int thành boolean
            stmt.setInt(2, serviceId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                // Nếu không có bản ghi nào được cập nhật, có thể cần thêm bản ghi mới
                insertStatus(serviceId, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating service status: " + e.getMessage());
        }
    }

    // Phương thức hỗ trợ để thêm trạng thái nếu chưa tồn tại
    private void insertStatus(int serviceId, int status) throws SQLException {
        String sql = "INSERT INTO service_status (service_id, service_status) VALUES (?, ?)";
        try (PreparedStatement stmt = connectionection.prepareStatement(sql)) {
            stmt.setInt(1, serviceId);
            stmt.setBoolean(2, status == 1);
            stmt.executeUpdate();
        }
    }

    // Thêm một dịch vụ mới vào bảng service và các bảng liên quan
    public void addService(Service service) {
        Connection connection = null;
        PreparedStatement stmtService = null;
        PreparedStatement stmtStatus = null;
        ResultSet generatedKeys = null;

        try {
            connectionection.setAutoCommit(false); // Bắt đầu transaction

            // Thêm vào bảng service
            String sqlService = "INSERT INTO service (service_title, service_bi, service_created_date, category_id, "
                    + "service_price, service_discount, service_detail, service_rateStar, service_vote) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmtService = connectionection.prepareStatement(sqlService, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtService.setString(1, service.getServiceTitle());
            stmtService.setString(2, service.getServiceBi());
            stmtService.setInt(3, service.getCategoryId());
            stmtService.setDouble(4, service.getServicePrice());
            stmtService.setDouble(5, service.getServiceDiscount());
            stmtService.setString(6, service.getServiceDetail());
            stmtService.setDouble(7, service.getServiceRateStar());
            stmtService.setInt(8, service.getServiceVote());
            stmtService.executeUpdate();

            // Lấy service_id vừa được tạo
            generatedKeys = stmtService.getGeneratedKeys();
            if (generatedKeys.next()) {
                int serviceId = generatedKeys.getInt(1);
                service.setServiceId(serviceId); // Cập nhật serviceId cho đối tượng Service

                // Thêm trạng thái mặc định vào bảng service_status
                String sqlStatus = "INSERT INTO service_status (service_id, service_status) VALUES (?, ?)";
                stmtStatus = connection.prepareStatement(sqlStatus);
                stmtStatus.setInt(1, serviceId);
                stmtStatus.setBoolean(2, service.getServiceStatus() == 1);
                stmtStatus.executeUpdate();

                // Nếu có image, thêm vào bảng service_image
                if (service.getServiceImage() != null && !service.getServiceImage().isEmpty()) {
                    String sqlImage = "INSERT INTO service_image (service_id, image_link, type) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtImage = connection.prepareStatement(sqlImage)) {
                        stmtImage.setInt(1, serviceId);
                        stmtImage.setString(2, service.getServiceImage());
                        stmtImage.setInt(3, 0); // Giả định type = 0 nếu không được chỉ định
                        stmtImage.executeUpdate();
                    }
                }
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            throw new RuntimeException("Error adding service: " + e.getMessage());
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (stmtService != null) {
                    stmtService.close();
                }
                if (stmtStatus != null) {
                    stmtStatus.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Cập nhật thông tin dịch vụ trong bảng service và các bảng liên quan
    public void updateService(Service service) {
        Connection connection = null;
        PreparedStatement stmtService = null;
        PreparedStatement stmtImage = null;

        try {;
            connection.setAutoCommit(false); // Bắt đầu transaction

            // Cập nhật bảng service
            String sqlService = "UPDATE service SET service_title = ?, service_bi = ?, service_created_date = ?, "
                    + "category_id = ?, service_price = ?, service_discount = ?, service_detail = ?, "
                    + "service_rateStar = ?, service_vote = ? WHERE service_id = ?";
            stmtService = connection.prepareStatement(sqlService);
            stmtService.setString(1, service.getServiceTitle());
            stmtService.setString(2, service.getServiceBi());
            stmtService.setInt(3, service.getCategoryId());
            stmtService.setDouble(4, service.getServicePrice());
            stmtService.setDouble(5, service.getServiceDiscount());
            stmtService.setString(6, service.getServiceDetail());
            stmtService.setDouble(7, service.getServiceRateStar());
            stmtService.setInt(8, service.getServiceVote());
            stmtService.setInt(9, service.getServiceId());
            stmtService.executeUpdate();

            // Cập nhật bảng service_image nếu có image
            if (service.getServiceImage() != null && !service.getServiceImage().isEmpty()) {
                // Kiểm tra xem đã có image cho service này chưa
                String checkImageSql = "SELECT image_id FROM service_image WHERE service_id = ?";
                try (PreparedStatement checkStmt = connection.prepareStatement(checkImageSql)) {
                    checkStmt.setInt(1, service.getServiceId());
                    ResultSet rs = checkStmt.executeQuery();
                    if (rs.next()) {
                        // Nếu đã có, cập nhật
                        String updateImageSql = "UPDATE service_image SET image_link = ? WHERE service_id = ?";
                        stmtImage = connection.prepareStatement(updateImageSql);
                        stmtImage.setString(1, service.getServiceImage());
                        stmtImage.setInt(2, service.getServiceId());
                        stmtImage.executeUpdate();
                    } else {
                        // Nếu chưa có, thêm mới
                        String insertImageSql = "INSERT INTO service_image (service_id, image_link, type) VALUES (?, ?, ?)";
                        stmtImage = connection.prepareStatement(insertImageSql);
                        stmtImage.setInt(1, service.getServiceId());
                        stmtImage.setString(2, service.getServiceImage());
                        stmtImage.setInt(3, 0); // Giả định type = 0
                        stmtImage.executeUpdate();
                    }
                }
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            throw new RuntimeException("Error updating service: " + e.getMessage());
        } finally {
            try {
                if (stmtService != null) {
                    stmtService.close();
                }
                if (stmtImage != null) {
                    stmtImage.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Service getServiceById(int serviceId) throws Exception {
        try (Connection conn = getConnection()) {
            String sql = "SELECT s.* FROM service s WHERE s.service_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, serviceId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Service service = new Service();
                    service.setServiceId(rs.getInt("service_id"));
                    service.setServiceTitle(rs.getString("service_title"));
                    service.setServiceBi(rs.getString("service_bi"));
                    service.setServiceCreatedDate(rs.getDate("service_created_date"));
                    service.setCategoryTitle(rs.getString("category_title"));
                    service.setServicePrice(rs.getDouble("service_price"));
                    service.setServiceDiscount(rs.getDouble("service_discount"));
                    service.setServiceDetail(rs.getString("service_detail"));
                    service.setServiceRateStar(rs.getDouble("service_rateStar"));
                    service.setServiceVote(rs.getInt("service_vote"));

                    String statusSql = "SELECT service_status FROM service_status WHERE service_id = ?";
                    try (PreparedStatement statusStmt = conn.prepareStatement(statusSql)) {
                        statusStmt.setInt(1, serviceId);
                        ResultSet statusRs = statusStmt.executeQuery();
                        if (statusRs.next()) {
                            service.setServiceStatus(statusRs.getBoolean("service_status") ? 1 : 0);
                        }
                    }

                    String imageSql = "SELECT image_link FROM service_image WHERE service_id = ? LIMIT 1";
                    try (PreparedStatement imageStmt = conn.prepareStatement(imageSql)) {
                        imageStmt.setInt(1, serviceId);
                        ResultSet imageRs = imageStmt.executeQuery();
                        if (imageRs.next()) {
                            service.setServiceImage(imageRs.getString("image_link"));
                        }
                    }

                    return service;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving service: " + e.getMessage());
        }
        return null;
    }
}
