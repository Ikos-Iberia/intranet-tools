-- =====================================================
-- Database: Intranet Tools
-- =====================================================

CREATE DATABASE IF NOT EXISTS intranet_tools CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE intranet_tools;

-- =====================================================
-- Departments
-- =====================================================

CREATE TABLE departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    system BOOLEAN NOT NULL DEFAULT FALSE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB;

-- =====================================================
-- Roles
-- =====================================================

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    system BOOLEAN NOT NULL DEFAULT FALSE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB;

-- =====================================================
-- Users
-- =====================================================

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    department_id INT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    last_login TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_users_department FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE SET NULL
) ENGINE = InnoDB;

CREATE INDEX idx_users_department ON users (department_id);

CREATE INDEX idx_users_active ON users (active);

CREATE INDEX idx_users_department_active ON users (department_id, active);

-- =====================================================
-- User Roles
-- =====================================================

CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE INDEX idx_user_roles_role ON user_roles (role_id);


-- =====================================================
-- Tool Categories
-- =====================================================

CREATE TABLE tool_categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    system BOOLEAN NOT NULL DEFAULT FALSE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    display_order INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB;

CREATE INDEX idx_tool_categories_order ON tool_categories (display_order);

-- =====================================================
-- Tool Visibilities
-- =====================================================

CREATE TABLE tool_visibilities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB;

-- =====================================================
-- Tools
-- =====================================================

CREATE TABLE tools (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    route VARCHAR(255) NOT NULL UNIQUE,
    icon VARCHAR(255),
    version VARCHAR(30) NOT NULL DEFAULT '1.0.0',
    category_id INT NOT NULL,
    visibility_id INT NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    display_order INT NOT NULL DEFAULT 0,
    frontend_only BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_tools_category FOREIGN KEY (category_id) REFERENCES tool_categories (id) ON DELETE RESTRICT,
    CONSTRAINT fk_tools_visibility FOREIGN KEY (visibility_id) REFERENCES tool_visibilities (id) ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE INDEX idx_tools_category ON tools (category_id);

CREATE INDEX idx_tools_visibility ON tools (visibility_id);

CREATE INDEX idx_tools_enabled ON tools (enabled);

CREATE INDEX idx_tools_enabled_order ON tools (enabled, display_order);

-- =====================================================
-- Tool Permissions
-- =====================================================

CREATE TABLE tool_permissions (
    tool_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (tool_id, role_id),
    CONSTRAINT fk_tool_permissions_tool FOREIGN KEY (tool_id) REFERENCES tools (id) ON DELETE CASCADE,
    CONSTRAINT fk_tool_permissions_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE INDEX idx_tool_permissions_role ON tool_permissions (role_id);

CREATE INDEX idx_tool_permissions_tool ON tool_permissions (tool_id);

-- =====================================================
-- Tool Departments (Tools <-> Departments)
-- =====================================================

CREATE TABLE tool_departments (
    tool_id INT NOT NULL,
    department_id INT NOT NULL,
    PRIMARY KEY (tool_id, department_id),
    CONSTRAINT fk_tool_departments_tool FOREIGN KEY (tool_id) REFERENCES tools (id) ON DELETE CASCADE,
    CONSTRAINT fk_tool_departments_department FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE INDEX idx_tool_departments_department ON tool_departments (department_id);

CREATE INDEX idx_tool_departments_tool ON tool_departments (tool_id);

-- =====================================================
-- User Activity
-- =====================================================

CREATE TABLE user_activity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    tool_id INT NULL,
    action_code VARCHAR(100) NOT NULL,
    entity_type VARCHAR(100),
    entity_id INT,
    ip_address VARCHAR(45),
    user_agent TEXT,
    metadata JSON,
    success BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_activity_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_activity_tool FOREIGN KEY (tool_id) REFERENCES tools (id) ON DELETE SET NULL
) ENGINE = InnoDB;

CREATE INDEX idx_activity_user ON user_activity (user_id);

CREATE INDEX idx_activity_tool ON user_activity (tool_id);

CREATE INDEX idx_activity_entity ON user_activity (entity_type, entity_id);

CREATE INDEX idx_activity_created ON user_activity (created_at);

-- =====================================================
-- Seed Data
-- =====================================================

-- -------------------------
-- Departments
-- -------------------------

INSERT INTO
    departments (code, name, description, system)
VALUES (
        'IT',
        'Information Technology',
        'Default department',
        TRUE
    );

-- -------------------------
-- Roles
-- -------------------------

INSERT INTO
    roles (
        code,
        name,
        description,
        system
    )
VALUES (
        'ADMIN',
        'Administrator',
        'Full access to the platform',
        TRUE
    );

-- -------------------------
-- Tool Visibilities
-- -------------------------

INSERT INTO
    tool_visibilities (code, name, description)
VALUES (
        'GLOBAL',
        'Global',
        'Available for all authenticated users'
    ),
    (
        'DEPARTMENT',
        'Department Restricted',
        'Available only for selected departments'
    );

-- -------------------------
-- Administrator User
-- -------------------------

INSERT INTO
    users (
        first_name,
        last_name,
        username,
        email,
        password_hash,
        department_id,
        active
    )
SELECT 'System', 'Administrator', 'admin', 'admin@saniikos.com', '$2a$12$QhIIP48TIHrhbGVs9wIHlegHV1dJDzTRGHfwbVio8NNSfCSuHGLa2', d.id, TRUE
FROM departments d
WHERE
    d.code = 'IT';

-- -------------------------
-- Administrator Role
-- -------------------------

INSERT INTO
    user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
    JOIN roles r ON r.code = 'ADMIN'
WHERE
    u.username = 'admin';