CREATE DATABASE ChildrenCare;
USE ChildrenCare;

-- Bảng role
CREATE TABLE role (
                      role_id INT AUTO_INCREMENT PRIMARY KEY,
                      role_name VARCHAR(255)
);

-- Bảng user
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       user_fullname VARCHAR(255),
                       user_gender BOOLEAN,
                       user_address VARCHAR(255),
                       user_password VARCHAR(255),
                       user_email VARCHAR(255),
                       user_phone VARCHAR(255),
                       role_id INT,
                       user_status BOOLEAN,
                       user_image VARCHAR(255),
                       FOREIGN KEY (role_id) REFERENCES role(role_id)
);

-- Bảng category
CREATE TABLE category (
                          category_id INT AUTO_INCREMENT PRIMARY KEY,
                          category_name VARCHAR(255),
                          icon VARCHAR(255)
);

-- Bảng blogs
CREATE TABLE blogs (
                       blog_id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT,
                       title VARCHAR(255),
                       bi VARCHAR(255),
                       blog_created_date DATE,
                       category_id INT,
                       detail TEXT,
                       blog_image VARCHAR(255),
                       view_able BOOLEAN,
                       FOREIGN KEY (user_id) REFERENCES users(user_id),
                       FOREIGN KEY (category_id) REFERENCES category(category_id)
);

-- Bảng children
CREATE TABLE children (
                          children_id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT NOT NULL,
                          children_name VARCHAR(255),
                          children_gender BOOLEAN,
                          children_age INT NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Bảng Comment
CREATE TABLE comments (
                          comment_id INT AUTO_INCREMENT PRIMARY KEY,
                          comment_content VARCHAR(500),
                          comment_date DATE,
                          comment_createByUser INT,
                          FOREIGN KEY (comment_createByUser) REFERENCES users(user_id)
);

-- Bảng reservation
CREATE TABLE reservation (
                             reservation_id INT AUTO_INCREMENT PRIMARY KEY,
                             user_id INT,
                             total_price DECIMAL(10, 2),
                             note TEXT,
                             reservation_status INT,
                             created_date DATE,
                             FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Bảng feedback
CREATE TABLE feedback (
                          feedback_id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT,
                          reservation_id INT,
                          content TEXT,
                          name VARCHAR(255),
                          gender BOOLEAN,
                          email VARCHAR(255),
                          mobile VARCHAR(255),
                          feedback_image VARCHAR(255),
                          rate_Star INT,
                          status BOOLEAN,
                          FOREIGN KEY (user_id) REFERENCES users(user_id),
                          FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id)
);

-- Bảng service
CREATE TABLE service (
                         service_id INT AUTO_INCREMENT PRIMARY KEY,
                         service_title VARCHAR(255),
                         service_bi VARCHAR(255),
                         service_created_date DATE,
                         category_id INT,
                         service_price DECIMAL(10, 2),
                         service_discount DECIMAL(5, 2),
                         service_detail TEXT,
                         service_rateStar INT,
                         service_vote INT,
                         FOREIGN KEY (category_id) REFERENCES category(category_id)
);

-- Bảng service_image
CREATE TABLE service_image (
                               image_id INT AUTO_INCREMENT PRIMARY KEY,
                               service_id INT,
                               image_link VARCHAR(255),
                               type INT,
                               FOREIGN KEY (service_id) REFERENCES service(service_id)
);

-- Bảng service_status
CREATE TABLE service_status (
                                status_id INT NOT NULL,
                                service_id INT NOT NULL,
                                service_status BOOLEAN,
                                PRIMARY KEY (status_id, service_id),
                                FOREIGN KEY (service_id) REFERENCES service(service_id)
);

-- Bảng slider
CREATE TABLE slider (
                        slider_id INT AUTO_INCREMENT PRIMARY KEY,
                        slider_title VARCHAR(255),
                        category_id INT,
                        slider_status BOOLEAN,
                        service_id INT,
                        notes VARCHAR(255),
                        image text,
                        FOREIGN KEY (category_id) REFERENCES category(category_id),
                        FOREIGN KEY (service_id) REFERENCES service(service_id)
);

-- Bảng slider_services
CREATE TABLE slider_services (
                                 slider_id INT NOT NULL,
                                 service_id INT NOT NULL,
                                 PRIMARY KEY (slider_id, service_id),
                                 FOREIGN KEY (slider_id) REFERENCES slider(slider_id),
                                 FOREIGN KEY (service_id) REFERENCES service(service_id)
);

-- Bảng categoryMedicine
CREATE TABLE categoryMedicine (
                                  cateMedicine_id INT AUTO_INCREMENT PRIMARY KEY,
                                  cateMedicine_name VARCHAR(255)
);

-- Bảng medicine_unit
CREATE TABLE medicine_unit (
                               unit_id INT AUTO_INCREMENT PRIMARY KEY,
                               unit_name VARCHAR(255)
);

-- Bảng medicine
CREATE TABLE medicine (
                          medicine_id INT AUTO_INCREMENT PRIMARY KEY,
                          medicine_name VARCHAR(255),
                          medicine_image VARCHAR(255),
                          medicine_price DECIMAL(10, 2),
                          medicine_unit INT,
                          medicine_quantity INT,
                          medicine_detail TEXT,
                          medicine_cateId INT,
                          FOREIGN KEY (medicine_cateId) REFERENCES categoryMedicine(cateMedicine_id),
                          FOREIGN KEY (medicine_unit) REFERENCES medicine_unit(unit_id)
);

-- Bảng reservation_detail
CREATE TABLE reservation_detail (
                                    reservation_detail_id INT AUTO_INCREMENT PRIMARY KEY,
                                    reservation_id INT,
                                    service_id INT,
                                    price DECIMAL(10, 2),
                                    quantity INT,
                                    num_of_person INT,
                                    category_id INT,
                                    doctor_id INT,
                                    nurse_id INT,
                                    begin_time DATE,
                                    slot INT,
                                    children_id INT,
                                    FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id),
                                    FOREIGN KEY (service_id) REFERENCES service(service_id),
                                    FOREIGN KEY (category_id) REFERENCES category(category_id),
                                    FOREIGN KEY (doctor_id) REFERENCES users(user_id),
                                    FOREIGN KEY (nurse_id) REFERENCES users(user_id),
                                    FOREIGN KEY (children_id) REFERENCES children(children_id)
);

-- Bảng reservation_medical
CREATE TABLE reservation_medical (
                                     medical_id INT AUTO_INCREMENT PRIMARY KEY,
                                     reservation_detail_id INT,
                                     diagnosis TEXT,
                                     created_date DATE,
                                     doctor_id INT,
                                     FOREIGN KEY (reservation_detail_id) REFERENCES reservation_detail(reservation_detail_id),
                                     FOREIGN KEY (doctor_id) REFERENCES users(user_id)
);

-- Bảng prescription_details
CREATE TABLE prescription_details (
                                      prescription_details_id INT AUTO_INCREMENT PRIMARY KEY,
                                      medical_id INT,
                                      medicine_id INT,
                                      quantity INT,
                                      price DECIMAL(10, 2),
                                      FOREIGN KEY (medical_id) REFERENCES reservation_medical(medical_id),
                                      FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id)
);
