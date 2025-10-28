CREATE TABLE appointment (
    id BIGSERIAL PRIMARY KEY,
    date_of_attendance TIMESTAMP NOT NULL,
    diagnostic_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    created_at TIMESTAMP ,
    updated_at TIMESTAMP ,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_appointment_diagnostic
        FOREIGN KEY (diagnostic_id)
        REFERENCES diagnostic (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_appointment_patient
        FOREIGN KEY (patient_id)
        REFERENCES patient (id)
        ON DELETE CASCADE
);
