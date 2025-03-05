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
INSERT INTO users (user_fullname, user_gender, user_address, user_password, user_email, user_phone, role_id, user_status, user_image) VALUES
('Nguyen Van An', 1, '123 Le Loi, Hanoi', 'password123', 'an.nguyen@example.com', '0912345678', 1, 1, 'an_image.jpg'),
('Tran Thi Bich', 0, '45 Tran Phu, HCMC', 'pass456', 'bich.tran@example.com', '0987654321', 2, 1, 'bich_image.jpg'),
('Le Van Cuong', 1, '78 Nguyen Trai, Da Nang', 'cuong789', 'cuong.le@example.com', '0935123456', 3, 1, 'cuong_image.jpg'),
('Pham Thi Dung', 0, '12 Pham Ngoc Thach, Hanoi', 'dung101', 'dung.pham@example.com', '0909123456', 4, 1, 'dung_image.jpg'),
('Hoang Van Nam', 1, '56 Hoang Hoa Tham, HCMC', 'nam202', 'nam.hoang@example.com', '0978123456', 4, 1, 'nam_image.jpg');


-- Tạo bảng category và thêm dữ liệu
CREATE TABLE category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255),
    icon TEXT
);
INSERT INTO category (category_name, icon) VALUES
('Nutrition', 'nutrition_icon.png'),
('Health Check', 'health_icon.png'),
('Education', 'education_icon.png'),
('Vaccination', 'vaccine_icon.png'),
('Play Therapy', 'play_icon.png');
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
INSERT INTO blogs (user_id, title, bi, blog_created_date, category_id, detail, blog_image, view_able) VALUES
(1, 'Top 5 Healthy Foods for Kids', 'Nutrition tips', '2025-01-10', 1, 'Detailed guide on healthy eating for children.', 'food_image.jpg', 1),
(2, 'Why Regular Health Checks Matter', 'Health insights', '2025-01-15', 2, 'Importance of regular checkups for kids.', 'checkup_image.jpg', 1),
(3, 'Fun Learning Activities', 'Education fun', '2025-02-01', 3, 'Creative ways to teach kids.', 'learning_image.jpg', 1),
(4, 'Vaccination Schedule for Toddlers', 'Vaccine guide', '2025-02-10', 4, 'Complete vaccination timeline.', 'vaccine_image.jpg', 1),
(5, 'Benefits of Play Therapy', 'Therapy benefits', '2025-03-01', 5, 'How play helps child development.', 'play_image.jpg', 1);


-- Tạo bảng children và thêm dữ liệu
CREATE TABLE children (
    children_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    children_name VARCHAR(255),
    children_gender BOOLEAN,
    children_age INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
INSERT INTO children (user_id, children_name, children_gender, children_age) VALUES
(4, 'Pham Minh Anh', 0, 3),
(4, 'Pham Quang Huy', 1, 5),
(5, 'Hoang Thi Lan', 0, 2),
(5, 'Hoang Minh Duc', 1, 4),
(4, 'Pham Ngoc Mai', 0, 6);

-- Tạo bảng comments và thêm dữ liệu
CREATE TABLE comments (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    comment_content VARCHAR(500),
    comment_date DATE,
    comment_createByUser INT,
    FOREIGN KEY (comment_createByUser) REFERENCES users(user_id)
);
INSERT INTO comments (comment_content, comment_date, comment_createByUser) VALUES
('Great article on nutrition!', '2025-01-11', 4),
('Very helpful vaccination info.', '2025-02-11', 5),
('My kids love these activities!', '2025-02-02', 4),
('Thanks for the health tips.', '2025-01-16', 5),
('Play therapy really works!', '2025-03-02', 4);

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
INSERT INTO reservation (user_id, total_price, note, reservation_status, payment_status, created_date, receiver_address, receiver_number, receiver_email, receiver_name) VALUES
(4, 150.00, 'Checkup for Minh Anh', 1, 1, '2025-01-20', '12 Pham Ngoc Thach, Hanoi', '0909123456', 'dung.pham@example.com', 'Pham Thi Dung'),
(5, 200.00, 'Vaccination for Lan', 1, 0, '2025-02-05', '56 Hoang Hoa Tham, HCMC', '0978123456', 'nam.hoang@example.com', 'Hoang Van Nam'),
(4, 100.00, 'Play therapy for Huy', 0, 1, '2025-02-15', '12 Pham Ngoc Thach, Hanoi', '0909123456', 'dung.pham@example.com', 'Pham Thi Dung'),
(5, 300.00, 'Full health check for Duc', 1, 1, '2025-03-01', '56 Hoang Hoa Tham, HCMC', '0978123456', 'nam.hoang@example.com', 'Hoang Van Nam'),
(4, 120.00, 'Nutrition consultation', 0, 0, '2025-03-03', '12 Pham Ngoc Thach, Hanoi', '0909123456', 'dung.pham@example.com', 'Pham Thi Dung');


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
INSERT INTO service (service_title, service_bi, service_created_date, category_id, service_price, service_discount, service_detail, service_rateStar, service_vote) VALUES
('Nutrition Consultation', 'Diet plan for kids', '2025-01-01', 1, 50.00, 5.00, 'Personalized nutrition advice.', 4.5, 10),
('Health Checkup', 'Full body check', '2025-01-01', 2, 100.00, 10.00, 'Comprehensive health screening.', 4.8, 15),
('Learning Workshop', 'Fun education', '2025-01-01', 3, 80.00, 0.00, 'Interactive learning for kids.', 4.2, 8),
('Vaccination Package', 'Essential vaccines', '2025-01-01', 4, 150.00, 20.00, 'Complete vaccine set.', 4.9, 20),
('Play Therapy Session', 'Therapeutic play', '2025-01-01', 5, 70.00, 5.00, 'Play-based therapy for kids.', 4.7, 12);

-- Tạo bảng feedback và thêm dữ liệu
CREATE TABLE feedback (
    feedback_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    service_id INT,
    content TEXT,
    name VARCHAR(255),
    gender BOOLEAN,
    email VARCHAR(255),
    mobile VARCHAR(255),
    feedback_image TEXT,
    rate_Star INT,
    status INT,
    created_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (service_id) REFERENCES service(service_id)
);
INSERT INTO feedback (user_id, service_id, content, name, gender, email, mobile, feedback_image, rate_Star, status, created_date) VALUES
(4, 1, 'Very useful nutrition advice!', 'Pham Thi Dung', 0, 'dung.pham@example.com', '0909123456', 'feedback1.jpg', 5, 1, '2025-01-21'),
(5, 2, 'Great health check service.', 'Hoang Van Nam', 1, 'nam.hoang@example.com', '0978123456', 'feedback2.jpg', 4, 1, '2025-03-02'),
(4, 3, 'Kids loved the workshop!', 'Pham Thi Dung', 0, 'dung.pham@example.com', '0909123456', 'feedback3.jpg', 4, 1, '2025-02-16'),
(5, 4, 'Vaccines were quick and easy.', 'Hoang Van Nam', 1, 'nam.hoang@example.com', '0978123456', 'feedback4.jpg', 5, 1, '2025-02-06'),
(4, 5, 'Play therapy was amazing.', 'Pham Thi Dung', 0, 'dung.pham@example.com', '0909123456', 'feedback5.jpg', 5, 1, '2025-03-04');

-- Tạo bảng service_image và thêm dữ liệu
CREATE TABLE service_image (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    service_id INT,
    image_link TEXT,
    type INT,
    FOREIGN KEY (service_id) REFERENCES service(service_id)
);
INSERT INTO service_image (service_id, image_link, type) VALUES
(1, 'nutrition_image1.jpg', 1),
(2, 'checkup_image1.jpg', 1),
(3, 'workshop_image1.jpg', 1),
(4, 'vaccine_image1.jpg', 1),
(5, 'playtherapy_image1.jpg', 1);

-- Tạo bảng service_status và thêm dữ liệu
CREATE TABLE service_status (
    status_id INT AUTO_INCREMENT NOT NULL,
    service_id INT NOT NULL,
    service_status BOOLEAN,
    PRIMARY KEY (status_id, service_id),
    FOREIGN KEY (service_id) REFERENCES service(service_id)
);
INSERT INTO service_status (service_id, service_status) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1);

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
INSERT INTO slider (slider_title, category_id, slider_status, service_id, notes, image) VALUES
('Healthy Kids Start Here', 1, 1, 1, 'Nutrition promo', 'slider1.jpg'),
('Checkup Deals', 2, 1, 2, 'Health check discount', 'slider2.jpg'),
('Learn with Fun', 3, 1, 3, 'Workshop promo', 'slider3.jpg'),
('Vaccination Now', 4, 1, 4, 'Vaccine campaign', 'slider4.jpg'),
('Play and Grow', 5, 1, 5, 'Therapy promo', 'slider5.jpg');

-- Tạo bảng slider_services và thêm dữ liệu
CREATE TABLE slider_services (
    slider_id INT NOT NULL,
    service_id INT NOT NULL,
    PRIMARY KEY (slider_id, service_id),
    FOREIGN KEY (slider_id) REFERENCES slider(slider_id),
    FOREIGN KEY (service_id) REFERENCES service(service_id)
);
INSERT INTO slider_services (slider_id, service_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);
-- Tạo bảng categoryMedicine và thêm dữ liệu
CREATE TABLE categoryMedicine (
    cateMedicine_id INT AUTO_INCREMENT PRIMARY KEY,
    cateMedicine_name VARCHAR(255)
);
INSERT INTO categoryMedicine (cateMedicine_name) VALUES
('Vitamins'),
('Antibiotics'),
('Pain Relief'),
('Vaccines'),
('Supplements');

-- Tạo bảng medicine_unit và thêm dữ liệu
CREATE TABLE medicine_unit (
    unit_id INT AUTO_INCREMENT PRIMARY KEY,
    unit_name VARCHAR(255)
);
INSERT INTO medicine_unit (unit_name) VALUES
('Tablet'),
('Syrup'),
('Injection'),
('Capsule'),
('Drop');

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
INSERT INTO medicine (medicine_name, medicine_image, medicine_price, medicine_unit, medicine_quantity, medicine_detail, medicine_cateId) VALUES
('Vitamin C', 'vitc_image.jpg', 10.00, 1, 100, 'Boosts immunity.', 1),
('Amoxicillin', 'amox_image.jpg', 15.00, 2, 50, 'Treats infections.', 2),
('Paracetamol', 'para_image.jpg', 5.00, 2, 200, 'Pain relief for kids.', 3),
('Flu Vaccine', 'flu_image.jpg', 20.00, 3, 30, 'Prevents flu.', 4),
('Calcium Syrup', 'calcium_image.jpg', 12.00, 2, 80, 'Bone health support.', 5);
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
INSERT INTO reservation_detail (reservation_id, service_id, price, quantity, category_id, staff_id, begin_time, slot, children_id) VALUES
(1, 2, 100.00, 1, 2, 3, '2025-01-21', 1, 1),
(2, 4, 150.00, 1, 4, 3, '2025-02-06', 2, 3),
(3, 5, 70.00, 1, 5, 3, '2025-02-16', 3, 2),
(4, 2, 100.00, 1, 2, 3, '2025-03-02', 1, 4),
(5, 1, 50.00, 1, 1, 3, '2025-03-04', 2, 1);
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
INSERT INTO reservation_medical (reservation_detail_id, diagnosis, created_date, staff_id) VALUES
(1, 'Healthy, no issues', '2025-01-21', 3),
(2, 'Vaccinated successfully', '2025-02-06', 3),
(3, 'Improved behavior', '2025-02-16', 3),
(4, 'Minor cold detected', '2025-03-02', 3),
(5, 'Good nutrition status', '2025-03-04', 3);
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
INSERT INTO prescription_details (medical_id, medicine_id, quantity, price) VALUES
(1, 1, 10, 10.00),
(2, 4, 1, 20.00),
(3, 5, 5, 12.00),
(4, 3, 15, 5.00),
(5, 1, 20, 10.00);
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