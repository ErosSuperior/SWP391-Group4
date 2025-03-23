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
	created_date datetime DEFAULT current_timestamp,
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);
INSERT INTO users (user_fullname, user_gender, user_address, user_password, user_email, user_phone, role_id, user_status, user_image) VALUES
('Nguyen Van An', 1, '123 Le Loi, Hanoi', 'e26f7cd7501ceedc694e7ea7cb267041', 'an.nguyen@example.com', '0912345678', 1, 1, 'https://yt3.googleusercontent.com/c-Z7mIlntSpG6VyQ5ZqaPggqkZRhaySr-H5ZEazFN2iR1pP4eD1UGekwu0y--c4CSVhJJ1A4QT8=s900-c-k-c0x00ffffff-no-rj'),
('Tran Thi Bich', 0, '45 Tran Phu, HCMC', 'e26f7cd7501ceedc694e7ea7cb267041', 'bich.tran@example.com', '0987654321', 2, 1, 'https://avatar-ex-swe.nixcdn.com/singer/avatar/2024/10/11/x/T/I/D/1728642676654.png'),
('Le Van Cuong', 1, '78 Nguyen Trai, Da Nang', 'e26f7cd7501ceedc694e7ea7cb267041', 'cuong.le@example.com', '0935123456', 3, 1, 'https://static-images.vnncdn.net/vps_images_publish/000001/000003/2025/1/20/ngan-ngam-thay-ca-si-jack-j97-72911.jpg?width=0&s=OQaz1tZ-7uFLA8UTXffWFQ'),
('Pham Thi Dung', 0, '12 Pham Ngoc Thach, Hanoi', 'e26f7cd7501ceedc694e7ea7cb267041', 'dung.pham@example.com', '0909123456', 4, 1, 'https://file.hstatic.net/200000053174/article/z5205193519599_e0f8ece2760430e82bdfb035f1c41195_32b6407b54694c76b0402e85cc232180.jpg'),
('Hoang Van Nam', 1, '56 Hoang Hoa Tham, HCMC', 'e26f7cd7501ceedc694e7ea7cb267041', 'nam.hoang@example.com', '0978123456', 4, 1, 'https://gamek.mediacdn.vn/133514250583805952/2024/9/24/yasuo-skin-1-1727148942603165090057.jpg');


-- Tạo bảng category và thêm dữ liệu
CREATE TABLE category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255),
    icon TEXT
);
INSERT INTO category (category_name, icon) VALUES
('Nutrition', 'https://i.fbcd.co/products/resized/resized-750-500/2201-m05-i039-n065-mainpreview-676835ae7dc10d2ea587a4499817ab6f0b636e71ba020c43b9563b9ac5a3bf1f.jpg'),
('Health Check', 'https://arogyanidhihospital.com/wp-content/uploads/2022/11/image-1.jpg'),
('Education', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwuNwBCP_4zPThX2IVGut1_NUhxMFamnrhUA&s'),
('Vaccination', 'https://images.squarespace-cdn.com/content/v1/56f01377c2ea5144e9b7cbfb/1627547641100-651Y0U5ADHVQYQY0O7T7/Image+1.jpg'),
('Play Therapy', 'https://corekidstherapy.com.au/wp-content/uploads/2023/06/Play-Therapy-image-1-scaled.jpg');
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
(1, 'Top 5 Healthy Foods for Kids', 'Nutrition tips', '2025-01-10', 1, 'Detailed guide on healthy eating for children.', 'https://i.fbcd.co/products/resized/resized-750-500/2201-m05-i039-n065-mainpreview-676835ae7dc10d2ea587a4499817ab6f0b636e71ba020c43b9563b9ac5a3bf1f.jpg', 1),
(2, 'Why Regular Health Checks Matter', 'Health insights', '2025-01-15', 2, 'Importance of regular checkups for kids.', 'https://arogyanidhihospital.com/wp-content/uploads/2022/11/image-1.jpg', 1),
(3, 'Fun Learning Activities', 'Education fun', '2025-02-01', 3, 'Creative ways to teach kids.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwuNwBCP_4zPThX2IVGut1_NUhxMFamnrhUA&s', 1),
(4, 'Vaccination Schedule for Toddlers', 'Vaccine guide', '2025-02-10', 4, 'Complete vaccination timeline.', 'https://images.squarespace-cdn.com/content/v1/56f01377c2ea5144e9b7cbfb/1627547641100-651Y0U5ADHVQYQY0O7T7/Image+1.jpg', 1),
(5, 'Benefits of Play Therapy', 'Therapy benefits', '2025-03-01', 5, 'How play helps child development.', 'https://corekidstherapy.com.au/wp-content/uploads/2023/06/Play-Therapy-image-1-scaled.jpg', 1);


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
    expiredin datetime default null,
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
(1, 'https://i.fbcd.co/products/resized/resized-750-500/2201-m05-i039-n065-mainpreview-676835ae7dc10d2ea587a4499817ab6f0b636e71ba020c43b9563b9ac5a3bf1f.jpg', 0),
(2, 'https://arogyanidhihospital.com/wp-content/uploads/2022/11/image-1.jpg', 0),
(3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwuNwBCP_4zPThX2IVGut1_NUhxMFamnrhUA&s', 0),
(4, 'https://images.squarespace-cdn.com/content/v1/56f01377c2ea5144e9b7cbfb/1627547641100-651Y0U5ADHVQYQY0O7T7/Image+1.jpg', 0),
(5, 'https://corekidstherapy.com.au/wp-content/uploads/2023/06/Play-Therapy-image-1-scaled.jpg', 0);

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
('Healthy Kids Start Here', 1, 1, 1, 'Nutrition promo', 'https://i.fbcd.co/products/resized/resized-750-500/2201-m05-i039-n065-mainpreview-676835ae7dc10d2ea587a4499817ab6f0b636e71ba020c43b9563b9ac5a3bf1f.jpg'),
('Checkup Deals', 2, 1, 2, 'Health check discount', 'https://arogyanidhihospital.com/wp-content/uploads/2022/11/image-1.jpg'),
('Learn with Fun', 3, 1, 3, 'Workshop promo', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwuNwBCP_4zPThX2IVGut1_NUhxMFamnrhUA&s'),
('Vaccination Now', 4, 1, 4, 'Vaccine campaign', 'https://images.squarespace-cdn.com/content/v1/56f01377c2ea5144e9b7cbfb/1627547641100-651Y0U5ADHVQYQY0O7T7/Image+1.jpg'),
('Play and Grow', 5, 1, 5, 'Therapy promo', 'https://corekidstherapy.com.au/wp-content/uploads/2023/06/Play-Therapy-image-1-scaled.jpg');

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

CREATE TABLE specialization (
    specialization_id INT AUTO_INCREMENT PRIMARY KEY,
    userid INT,
    categoryid INT
);

insert into specialization (userid, categoryid) value
(3,1),(3,2),(3,3),(3,4),(3,5);
