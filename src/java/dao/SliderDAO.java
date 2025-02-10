package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Slider;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SliderDAO extends DBContext {

    private Connection connection;

    public SliderDAO() {
        try {
            this.connection = getConnection(); // Initialize the connection
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null; // Set to null if connection fails
        }
    }

    public Slider findById(int id) {
        String sql = "select * from slider where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int sliderId = rs.getInt("slider_id");
                String title = rs.getString("slider_title");
                boolean status = rs.getBoolean("slider_status");
                String note = rs.getString("notes");
                String image = rs.getString("image");
                int categoryId = rs.getInt("category_id");
                int serviceId = rs.getInt("service_id");

                Slider slider = new Slider(sliderId, title, status, note, image, categoryId, serviceId);
                return slider;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;

    }

    public List<Slider> findAll(int page, int limit) {
        List<Slider> sliders = new ArrayList<>();
        String sql = "select * from slider limit ? offset ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, (page - 1) * limit);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int sliderId = rs.getInt("slider_id");
                String title = rs.getString("slider_title");
                boolean status = rs.getBoolean("slider_status");
                String note = rs.getString("notes");
                String image = rs.getString("image");
                int categoryId = rs.getInt("category_id");
                int serviceId = rs.getInt("service_id");

                Slider slider = new Slider(sliderId, title, status, note, image, categoryId, serviceId);
                sliders.add(slider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sliders;
    }

    public int count() {
        String sql = "select count(*) from slider";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
