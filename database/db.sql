-- TABLE role
CREATE TABLE roles(
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(20) NOT NULL
);
INSERT INTO roles (role_name) VALUES
        ('ADMIN'),
        ('USER'),
        ('SCHOOL'),
        ('TEACHER'),
        ('STUDENT');
-- DATABASE school
CREATE TABLE schools (
    school_id INT AUTO_INCREMENT PRIMARY KEY,
    school_name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20),
    email VARCHAR(255),
    website VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME
) AUTO_INCREMENT = 1000000;
-- DATABASE USER
CREATE TABLE users(
   user_id INT AUTO_INCREMENT PRIMARY KEY,
   user_name VARCHAR(20),
   phone_number VARCHAR(20),
   email VARCHAR(255) NOT NULL,
   password VARCHAR(100) NOT NULL,
   is_active TINYINT(1) DEFAULT 1,
   created_at DATETIME,
   updated_at DATETIME
) AUTO_INCREMENT = 1000000;
CREATE TABLE user_schools (
    user_id INT,
    school_id INT,
    PRIMARY KEY (user_id, school_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (school_id) REFERENCES schools(school_id)
);
CREATE TABLE user_roles (
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
) ;
CREATE TABLE tokens(
    token_id int PRIMARY KEY AUTO_INCREMENT,
    token varchar(255) UNIQUE NOT NULL,
    token_type varchar(50) NOT NULL,
    expiration_date DATETIME,
    refresh_token varchar(255) UNIQUE NOT NULL,
    refresh_token_date DATETIME,
    revoked tinyint(1) NOT NULL,
    expired tinyint(1) NOT NULL,
    user_id int NOT NULL,
    user_agent VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
)
CREATE TABLE profiles(
    profile_id INT PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL DEFAULT "",
    last_name varchar(20) NOT NULL DEFAULT "",
    gender varchar(10) DEFAULT "male",
    address VARCHAR(50) NOT NULL,
    date_of_birth DATETIME,
    avatar TEXT,
    banner TEXT,
    FOREIGN KEY (profile_id) REFERENCES users(user_id)
);
-- DATABASE WALLET
CREATE TABLE wallet_transactions(
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    transaction_type ENUM('transfer', 'receive', 'deposit', 'withdraw') NOT NULL,
    description TEXT,
    transaction_date DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE wallet_balance(
    user_id INT PRIMARY KEY,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0,
    last_updated DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
--DATABASE HOME PAGE
CREATE TABLE posts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    content TEXT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
) AUTO_INCREMENT = 1000000;
CREATE TABLE post_attachments(
    attachment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    attachment_type VARCHAR(20),
    attachment_url TEXT,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (post_id) REFERENCES posts(post_id)
) AUTO_INCREMENT = 1000000;
CREATE TABLE likes (
    like_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    post_id INT,
    created_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (post_id) REFERENCES posts(post_id)
);
CREATE TABLE comments (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    user_id INT,
    content TEXT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (post_id) REFERENCES posts(post_id)
) AUTO_INCREMENT = 1000000;
CREATE TABLE comment_attachments(
    attachment_id INT AUTO_INCREMENT PRIMARY KEY,
    comment_id INT,
    attachment_type VARCHAR(20),
    attachment_url TEXT,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (comment_id) REFERENCES comments(comment_id)
) AUTO_INCREMENT = 1000000;
CREATE TABLE reply_comments(
    reply_comment_id INT AUTO_INCREMENT PRIMARY KEY,
    comment_id INT,
    user_id INT,
    content TEXT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (comment_id) REFERENCES comments(comment_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
) AUTO_INCREMENT = 1000000;
CREATE TABLE reply_comment_attachments(
    attachment_id INT AUTO_INCREMENT PRIMARY KEY,
    reply_comment_id INT,
    attachment_type VARCHAR(20),
    attachment_url TEXT,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (reply_comment_id) REFERENCES reply_comments(reply_comment_id)
);

-- DATABASE CHAT
CREATE TABLE chat_rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_name VARCHAR(255) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
) AUTO_INCREMENT = 1000000;
CREATE TABLE room_members (
    room_id INT,
    user_id INT,
    PRIMARY KEY (room_id, user_id),
    FOREIGN KEY (room_id) REFERENCES chat_rooms(room_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE messages (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    room_id INT,
    user_id INT,
    content TEXT NOT NULL,
    created_at DATETIME,
    FOREIGN KEY (room_id) REFERENCES chat_rooms(room_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE message_attachments (
    attachment_id INT AUTO_INCREMENT PRIMARY KEY,
    message_id INT,
    attachment_type ENUM('image', 'video', 'file') NOT NULL,
    attachment_url TEXT NOT NULL,
    created_at DATETIME,
    FOREIGN KEY (message_id) REFERENCES messages(message_id)
);
-- DATABASE STUDY PAGE
CREATE TABLE subjects (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_name VARCHAR(50) NOT NULL
) AUTO_INCREMENT = 1000000;
CREATE TABLE questions (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_id INT,
    user_id INT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
) AUTO_INCREMENT = 1000000;
CREATE TABLE question_attachments (
    attachment_id INT AUTO_INCREMENT PRIMARY KEY,
    file_type ENUM('image', 'video') NOT NULL,
    file_url VARCHAR(255) NOT NULL,
    question_id INT,
    FOREIGN KEY (question_id) REFERENCES questions(question_id)
);
CREATE TABLE answers (
    answer_id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT,
    user_id INT,
    content TEXT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (question_id) REFERENCES questions(question_id)
) AUTO_INCREMENT = 1000000;
CREATE TABLE answers_attachments (
    attachment_id INT AUTO_INCREMENT PRIMARY KEY,
    file_type ENUM('image', 'video') NOT NULL,
    file_url VARCHAR(255) NOT NULL,
    answer_id INT,
    FOREIGN KEY (answer_id) REFERENCES answers(answer_id)
);
-- TABLE ONLINE COURSES
CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    price FLOAT,
    uploader_id INT,
    uploader_type ENUM('school', 'user') NOT NULL,
    title VARCHAR(255) NOT NULL,
    banner TEXT,
    description TEXT,
    video_url VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (uploader_id) REFERENCES schools(school_id) ON DELETE CASCADE,
    FOREIGN KEY (uploader_id) REFERENCES users(user_id) ON DELETE CASCADE
);
-- TABLE FILE
CREATE TABLE documents (
    document_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_id INT,
    poster_id INT,
    title VARCHAR(255) NOT NULL,
    banner TEXT,
    description TEXT,
    file_url VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (poster_id) REFERENCES users(user_id),
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
);

-- DATABASE JOB
CREATE TABLE jobs (
    job_id INT AUTO_INCREMENT PRIMARY KEY,
    poster_id INT,
    title VARCHAR(255) NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    description TEXT,
    location VARCHAR(100),
    salary DECIMAL(10, 2),
    posted_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (poster_id) REFERENCES users(user_id)
);

CREATE TABLE job_applications (
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    job_id INT,
    cover_letter TEXT,
    resume_url VARCHAR(255),
    applied_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (job_id) REFERENCES jobs(job_id)
);



