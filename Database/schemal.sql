-- =========================================================
CREATE TYPE gender_enum AS ENUM ('MALE', 'FEMALE');

CREATE TYPE member_occupation_enum AS ENUM (
    'JUNIOR',
    'SENIOR',
    'SECRETARY',
    'TREASURER',
    'VICE_PRESIDENT',
    'PRESIDENT'
);

CREATE TABLE collectivities (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    location VARCHAR(150) NOT NULL,
    federation_approval BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE members (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    gender gender_enum NOT NULL,
    address TEXT NOT NULL,
    profession VARCHAR(150),
    phone_number VARCHAR(30),
    email VARCHAR(150),

    occupation member_occupation_enum NOT NULL,

    collectivity_id BIGINT REFERENCES collectivities(id) ON DELETE SET NULL,

    registration_fee_paid BOOLEAN NOT NULL DEFAULT FALSE,
    membership_dues_paid BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE member_referees (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    member_id BIGINT NOT NULL
        REFERENCES members(id) ON DELETE CASCADE,

    referee_id BIGINT NOT NULL
        REFERENCES members(id) ON DELETE RESTRICT,

    CONSTRAINT uq_member_referee UNIQUE (member_id, referee_id),
    CONSTRAINT chk_not_self_referee CHECK (member_id <> referee_id)
);

CREATE TABLE collectivity_structures (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    collectivity_id BIGINT NOT NULL UNIQUE
        REFERENCES collectivities(id) ON DELETE CASCADE,

    president_member_id BIGINT NOT NULL
        REFERENCES members(id) ON DELETE RESTRICT,

    vice_president_member_id BIGINT NOT NULL
        REFERENCES members(id) ON DELETE RESTRICT,

    treasurer_member_id BIGINT NOT NULL
        REFERENCES members(id) ON DELETE RESTRICT,

    secretary_member_id BIGINT NOT NULL
        REFERENCES members(id) ON DELETE RESTRICT,

    CONSTRAINT chk_distinct_roles CHECK (
        president_member_id <> vice_president_member_id AND
        president_member_id <> treasurer_member_id AND
        president_member_id <> secretary_member_id AND
        vice_president_member_id <> treasurer_member_id AND
        vice_president_member_id <> secretary_member_id AND
        treasurer_member_id <> secretary_member_id
    )
);

CREATE INDEX idx_members_collectivity_id
ON members(collectivity_id);

CREATE INDEX idx_member_referees_member_id
ON member_referees(member_id);

CREATE INDEX idx_member_referees_referee_id
ON member_referees(referee_id);

CREATE TABLE financial_account (
    id VARCHAR(50) PRIMARY KEY,
    collectivity_id INTEGER NOT NULL,
    account_type VARCHAR(30) NOT NULL,
    holder_name VARCHAR(100),
    mobile_banking_service VARCHAR(30),
    mobile_number VARCHAR(20),
    bank_name VARCHAR(50),
    bank_code INTEGER,
    bank_branch_code INTEGER,
    bank_account_number INTEGER,
    bank_account_key INTEGER,
    FOREIGN KEY (collectivity_id) REFERENCES collectivities(id)
);

CREATE TABLE financial_account_transaction (
    id VARCHAR(50) PRIMARY KEY,
    financial_account_id VARCHAR(50) NOT NULL,
    amount NUMERIC(12,2) NOT NULL,
    transaction_type VARCHAR(10) NOT NULL,
    creation_date DATE NOT NULL,
    FOREIGN KEY (financial_account_id) REFERENCES financial_account(id)
);