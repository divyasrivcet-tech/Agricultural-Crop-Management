-- Preserve the application's record_id column.
-- Add recorded_date only when it is missing.
ALTER TABLE public.yield_records ADD COLUMN IF NOT EXISTS recorded_date DATE DEFAULT CURRENT_DATE;