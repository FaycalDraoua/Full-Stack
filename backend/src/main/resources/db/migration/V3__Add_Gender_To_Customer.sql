ALTER TABLE customer
ADD COLUMN gender VARCHAR(10) NOT NULL
CHECK (gender IN ('Male', 'Female'));
