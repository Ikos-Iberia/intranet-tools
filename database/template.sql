-- =====================================================
-- Database: Saniikos Dashboard (Refactored)
-- =====================================================

CREATE DATABASE IF NOT EXISTS saniikos_dashboard
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE saniikos_dashboard;

-- =========================
-- 1. Roles
-- =========================
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);

-- =========================
-- 2. Users
-- =========================
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department VARCHAR(50),
    role_id INT,
    password_hash VARCHAR(255) NOT NULL,
    deleted_at TIMESTAMP NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_users_role
        FOREIGN KEY (role_id) REFERENCES roles(id)
        ON DELETE SET NULL
);

CREATE INDEX idx_users_role ON users(role_id);

-- =========================
-- 3. App Visibility (catalog)
-- =========================
CREATE TABLE app_visibilities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- =========================
-- 4. Apps
-- =========================
CREATE TABLE apps (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    section VARCHAR(50) NOT NULL,
    description TEXT,
    version VARCHAR(20) NOT NULL,
    visibility_id INT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_apps_visibility
        FOREIGN KEY (visibility_id) REFERENCES app_visibilities(id)
        ON DELETE SET NULL
);

CREATE INDEX idx_apps_visibility ON apps(visibility_id);

-- =========================
-- 5. Request Priorities (catalog)
-- =========================
CREATE TABLE request_priorities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- =========================
-- 6. Request Statuses (catalog)
-- =========================
CREATE TABLE request_statuses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- =========================
-- 7. Requests (Help Desk)
-- =========================
CREATE TABLE requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    assigned_to INT NULL,
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    priority_id INT,
    status_id INT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_requests_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_requests_assigned
        FOREIGN KEY (assigned_to) REFERENCES users(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_requests_priority
        FOREIGN KEY (priority_id) REFERENCES request_priorities(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_requests_status
        FOREIGN KEY (status_id) REFERENCES request_statuses(id)
        ON DELETE SET NULL
);

CREATE INDEX idx_requests_user ON requests(user_id);
CREATE INDEX idx_requests_assigned ON requests(assigned_to);
CREATE INDEX idx_requests_priority ON requests(priority_id);
CREATE INDEX idx_requests_status ON requests(status_id);

-- =========================
-- 8. User Activity (structured)
-- =========================
CREATE TABLE user_activity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    action_type VARCHAR(50) NOT NULL,
    entity_type VARCHAR(50),
    entity_id INT,
    metadata JSON,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_activity_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_activity_user ON user_activity(user_id);
CREATE INDEX idx_activity_entity ON user_activity(entity_type, entity_id);

-- =========================
-- 9. Request Status History (audit)
-- =========================
CREATE TABLE request_status_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    request_id INT NOT NULL,
    status_id INT,
    changed_by INT,
    changed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (request_id) REFERENCES requests(id) ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES request_statuses(id) ON DELETE SET NULL,
    FOREIGN KEY (changed_by) REFERENCES users(id) ON DELETE SET NULL
);

CREATE INDEX idx_history_request ON request_status_history(request_id);

-- =====================================================
-- Seed data (initial catalogs)
-- =====================================================

INSERT INTO app_visibilities (name) VALUES
('Public Utility'),
('Internal'),
('Restricted');

INSERT INTO request_priorities (name) VALUES
('Low'),
('Normal'),
('High'),
('Urgent');

INSERT INTO request_statuses (name) VALUES
('Pending'),
('In Progress'),
('Closed');