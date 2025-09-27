CREATE TABLE user (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    gender VARCHAR(10),
    address VARCHAR(255),
    date VARCHAR(20),
    school VARCHAR(255),
    tenthmark VARCHAR(30),
    yoctenth VARCHAR(30),
    twelthmark VARCHAR(30),
    yocplustwo VARCHAR(30)
);

CREATE TABLE work_exp_att (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    RefID INT,
    companyname VARCHAR(255),
    duration VARCHAR(255),
    jobrole VARCHAR(255),
    skillsgained VARCHAR(255),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(ID)
);
