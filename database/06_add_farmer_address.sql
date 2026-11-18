-- Add full address without deleting existing farmer records.
ALTER TABLE farmers
ADD COLUMN IF NOT EXISTS address VARCHAR(255);
