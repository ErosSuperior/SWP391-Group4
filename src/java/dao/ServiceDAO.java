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

    public List<Service> getActiveService(int offset, int limit, String nameOrId, int categoryId, String sortBy, String sortDir) {
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

    public int countActiveService(String nameOrId, int categoryId) {
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

    public List<Service> getActiveCategory() {
        List<Service> services = new ArrayList<>();
        String query = "SELECT * FROM category WHERE 1=1 ORDER BY category_id DESC";

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
                + "WHERE 1=1 ");

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
    
    public int countAllServices(String nameOrId, int categoryId, int status) {
        int total = 0;
        if (connection == null) {
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

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }
}
