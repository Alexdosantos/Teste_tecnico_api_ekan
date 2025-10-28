CREATE TABLE patient (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(255) ,
    address VARCHAR(255) ,
    cpf VARCHAR(255) UNIQUE ,
    birth_date VARCHAR(255) ,
    gender VARCHAR(255) ,
    blood_type VARCHAR(255) ,
    complement VARCHAR(255) ,
    city VARCHAR(255) ,
    state VARCHAR(255) ,
    zip_code VARCHAR(255) ,
    country VARCHAR(255) ,
    created_at TIMESTAMP ,
    updated_at TIMESTAMP ,
    deleted_at TIMESTAMP
);
