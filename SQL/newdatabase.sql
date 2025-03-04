-- Tạo database và sử dụng database
CREATE DATABASE Test123;
USE Test123;

-- Tạo bảng role và thêm dữ liệu
CREATE TABLE role (
    role_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    role_name VARCHAR(255) DEFAULT NULL
);
INSERT INTO role (role_name) VALUES ('Admin'), ('Manager'), ('Staff'), ('Custommer');

-- Tạo bảng users và thêm dữ liệu
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    user_fullname VARCHAR(255) DEFAULT NULL,
    user_gender BOOLEAN DEFAULT NULL,
    user_address VARCHAR(255) DEFAULT NULL,
    user_password VARCHAR(255) DEFAULT NULL,
    user_email VARCHAR(255) DEFAULT NULL,
    user_phone VARCHAR(255) DEFAULT NULL,
    role_id INT DEFAULT NULL,
    user_status BOOLEAN DEFAULT NULL,
    user_image TEXT DEFAULT NULL,
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);
INSERT INTO users (user_fullname, user_gender, user_address, user_password, user_email, user_phone, role_id, user_status) 
VALUES ('John Doe', 1, '123 Main St', 'password123', 'john.doe@example.com', '1234567890', 1, 1),
       ('Jane Smith', 0, '456 Oak St', 'password456', 'jane.smith@example.com', '0987654321', 2, 1),
       ('Alice Johnson', 0, '789 Pine St', 'password789', 'alice.johnson@example.com', '1122334455', 3, 1);

-- Tạo bảng category và thêm dữ liệu
CREATE TABLE category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255),
    icon TEXT
);
INSERT INTO category (category_name, icon) VALUES ('Pediatrics', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSl4SxpMqqFgtouMCduw_zK6BJzBbQF-kFkvA&s'),
                                                   ('General Medicine', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSl4SxpMqqFgtouMCduw_zK6BJzBbQF-kFkvA&s');

-- Tạo bảng blogs và thêm dữ liệu
CREATE TABLE blogs (
    blog_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    title VARCHAR(255),
    bi VARCHAR(255),
    blog_created_date DATE,
    category_id INT,
    detail TEXT,
    blog_image TEXT,
    view_able BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);
INSERT INTO blogs (user_id, title, bi, blog_created_date, category_id, detail, blog_image, view_able) 
VALUES (1, 'Childrens Health Tips', 'Health tips for kids', '2025-02-15', 1, 'This blog is about keeping children healthy.', 'https://cdn2.momjunction.com/wp-content/uploads/2022/10/Consuming-enough-fruits-and-vegetables-health-tips-for-children.jpg.webp',1);

-- Tạo bảng children và thêm dữ liệu
CREATE TABLE children (
    children_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    children_name VARCHAR(255),
    children_gender BOOLEAN,
    children_age INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
INSERT INTO children (user_id, children_name, children_gender, children_age) 
VALUES (1, 'Tommy', 1, 5),
       (2, 'Emma', 0, 3);

-- Tạo bảng comments và thêm dữ liệu
CREATE TABLE comments (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    comment_content VARCHAR(500),
    comment_date DATE,
    comment_createByUser INT,
    FOREIGN KEY (comment_createByUser) REFERENCES users(user_id)
);
INSERT INTO comments (comment_content, comment_date, comment_createByUser) 
VALUES ('Great blog!', '2025-02-16', 3);

-- Tạo bảng reservation và thêm dữ liệu
CREATE TABLE reservation (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    total_price DECIMAL(10, 2),
    note TEXT,
    reservation_status INT,
    payment_status INT,
    created_date DATE,
    receiver_address VARCHAR(500),
    receiver_number varchar(100),
    receiver_email varchar(100),
    receiver_name varchar(100),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
INSERT INTO reservation (user_id, total_price, note, reservation_status, payment_status, created_date, receiver_address, receiver_number, receiver_email, receiver_name) 
VALUES (1, 100.50, 'Initial reservation for consultation', 1, 1, '2025-02-17', 'St12 NYC', '0973256951', 'abc@gmail.com', 'nguyendat');

-- Tạo bảng feedback và thêm dữ liệu
CREATE TABLE feedback (
    feedback_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    reservation_id INT,
    content TEXT,
    name VARCHAR(255),
    gender BOOLEAN,
    email VARCHAR(255),
    mobile VARCHAR(255),
    feedback_image TEXT,
    rate_Star INT,
    status BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id)
);
INSERT INTO feedback (user_id, reservation_id, content, name, gender, email, mobile, feedback_image, rate_Star, status) 
VALUES (1, 1, 'Great service, will book again!', 'John Doe', 1, 'john.doe@example.com', '1234567890', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSl4SxpMqqFgtouMCduw_zK6BJzBbQF-kFkvA&s', 5, 1);

-- Tạo bảng service và thêm dữ liệu
CREATE TABLE service (
    service_id INT AUTO_INCREMENT PRIMARY KEY,
    service_title VARCHAR(255),
    service_bi VARCHAR(255),
    service_created_date DATE,
    category_id INT,
    service_price DECIMAL(10, 2),
    service_discount DECIMAL(5, 2),
    service_detail TEXT,
    service_rateStar Double,
    service_vote INT,
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);
INSERT INTO service (service_title, service_bi, service_created_date, category_id, service_price, service_discount, service_detail, service_rateStar, service_vote) 
VALUES ('Pediatric Consultation', 'Consultation for children', '2025-02-17', 1, 50.00, 10.00, 'Consultation for children with pediatricians', 5, 100);


-- Tạo bảng service_image và thêm dữ liệu
CREATE TABLE service_image (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    service_id INT,
    image_link TEXT,
    type INT,
    FOREIGN KEY (service_id) REFERENCES service(service_id)
);
INSERT INTO service_image (service_id, image_link, type) 
VALUES (1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSl4SxpMqqFgtouMCduw_zK6BJzBbQF-kFkvA&s', 0);

-- Tạo bảng service_status và thêm dữ liệu
CREATE TABLE service_status (
    status_id INT AUTO_INCREMENT NOT NULL,
    service_id INT NOT NULL,
    service_status BOOLEAN,
    PRIMARY KEY (status_id, service_id),
    FOREIGN KEY (service_id) REFERENCES service(service_id)
);
INSERT INTO service_status (service_id, service_status) 
VALUES (1, 1);

-- Tạo bảng slider và thêm dữ liệu
CREATE TABLE slider (
    slider_id INT AUTO_INCREMENT PRIMARY KEY,
    slider_title VARCHAR(255),
    category_id INT,
    slider_status BOOLEAN,
    service_id INT,
    notes VARCHAR(255),
    image TEXT,
    FOREIGN KEY (category_id) REFERENCES category(category_id),
    FOREIGN KEY (service_id) REFERENCES service(service_id)
);
INSERT INTO slider (slider_title, category_id, slider_status, service_id, notes, image) 
VALUES ('Pediatric Services', 1, 1, 1, 'Featured pediatric services', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSl4SxpMqqFgtouMCduw_zK6BJzBbQF-kFkvA&s');

-- Tạo bảng slider_services và thêm dữ liệu
CREATE TABLE slider_services (
    slider_id INT NOT NULL,
    service_id INT NOT NULL,
    PRIMARY KEY (slider_id, service_id),
    FOREIGN KEY (slider_id) REFERENCES slider(slider_id),
    FOREIGN KEY (service_id) REFERENCES service(service_id)
);
INSERT INTO slider_services (slider_id, service_id) 
VALUES (1, 1);

-- Tạo bảng categoryMedicine và thêm dữ liệu
CREATE TABLE categoryMedicine (
    cateMedicine_id INT AUTO_INCREMENT PRIMARY KEY,
    cateMedicine_name VARCHAR(255)
);
INSERT INTO categoryMedicine (cateMedicine_name) 
VALUES ('Antibiotics'), ('Pain Relievers');

-- Tạo bảng medicine_unit và thêm dữ liệu
CREATE TABLE medicine_unit (
    unit_id INT AUTO_INCREMENT PRIMARY KEY,
    unit_name VARCHAR(255)
);
INSERT INTO medicine_unit (unit_name) 
VALUES ('Bottle'), ('Tablet');

-- Tạo bảng medicine và thêm dữ liệu
CREATE TABLE medicine (
    medicine_id INT AUTO_INCREMENT PRIMARY KEY,
    medicine_name VARCHAR(255),
    medicine_image Text,
    medicine_price DECIMAL(10, 2),
    medicine_unit INT,
    medicine_quantity INT,
    medicine_detail TEXT,
    medicine_cateId INT,
    FOREIGN KEY (medicine_cateId) REFERENCES categoryMedicine(cateMedicine_id),
    FOREIGN KEY (medicine_unit) REFERENCES medicine_unit(unit_id)
);
INSERT INTO medicine (medicine_name, medicine_image, medicine_price, medicine_unit, medicine_quantity, medicine_detail, medicine_cateId) 
VALUES ('Paracetamol', 'https://www.mediplantex.com/upload/product/thumbs/8594648bd43d66f8f602e77c7cccf242.jpg', 10.50, 2, 100, 'Pain reliever and fever reducer', 1),
       ('Amoxicillin', 'https://media.loveitopcdn.com/37811/thumb/upload/images/thuoc-khang-sinh-amoxicillin-500mg.png', 15.75, 1, 200, 'Antibiotic used to treat infections', 2);

-- Tạo bảng reservation_detail và thêm dữ liệu
CREATE TABLE reservation_detail (
    reservation_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_id INT,
    service_id INT,
    price DECIMAL(10, 2),
    quantity INT,
    category_id INT,
    staff_id INT,
    begin_time DATE,
    slot INT,
    children_id INT,
    FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id),
    FOREIGN KEY (service_id) REFERENCES service(service_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id),
    FOREIGN KEY (staff_id) REFERENCES users(user_id),
    FOREIGN KEY (children_id) REFERENCES children(children_id)
);
INSERT INTO reservation_detail (reservation_id, service_id, price, quantity, category_id,staff_id, begin_time, slot, children_id) 
VALUES (1, 1, 50.00, 1, 1, 2, '2025-02-17', 1, 1);

-- Tạo bảng reservation_medical và thêm dữ liệu
CREATE TABLE reservation_medical (
    medical_id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_detail_id INT,
    diagnosis TEXT,
    created_date DATE,
    staff_id INT,
    FOREIGN KEY (reservation_detail_id) REFERENCES reservation_detail(reservation_detail_id),
    FOREIGN KEY (staff_id) REFERENCES users(user_id)
);
INSERT INTO reservation_medical (reservation_detail_id, diagnosis, created_date, staff_id) 
VALUES (1, 'Fever and sore throat', '2025-02-17', 2);

-- Tạo bảng prescription_details và thêm dữ liệu
CREATE TABLE prescription_details (
    prescription_details_id INT AUTO_INCREMENT PRIMARY KEY,
    medical_id INT,
    medicine_id INT,
    quantity INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (medical_id) REFERENCES reservation_medical(medical_id),
    FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id)
);
INSERT INTO prescription_details (medical_id, medicine_id, quantity, price) 
VALUES (1, 1, 1, 10.50);

-- Tạo bảng setting và thêm dữ liệu
CREATE TABLE setting (
    setting_id INT AUTO_INCREMENT PRIMARY KEY,
 setting_name VARCHAR(100) NOT NULL,
    setting_type VARCHAR(50) NOT NULL,
    setting_description TEXT,
    setting_value VARCHAR(100) NOT NULL,
    setting_status ENUM('Active', 'Inactive') NOT NULL
);

INSERT INTO setting (setting_type, setting_name, setting_value, setting_description, setting_status) VALUES
('General', 'Enable Notifications', 'ON', 'Turn system notifications on or off', 'Active'),
('UI', 'Default Theme', 'Light', 'Default theme of the user interface', 'Active'),
('Security', 'Two-factor Auth', 'Enabled', 'Enable or disable two-factor authentication', 'Active'),
('System', 'Auto Backup', 'OFF', 'Automatically back up data', 'Inactive'),
('Account', 'Allow Guest Login', 'No', 'Allow guest users to log in without an account', 'Active'),
('Email', 'Email Expiration Time', '30 days', 'Expiration period for temporary emails', 'Active'),
('General', 'Language', 'English', 'Default system language', 'Active');