```sql
CREATE TRIGGER trigger_name
{BEFORE | AFTER} {INSERT | UPDATE | DELETE}
ON table_name
FOR EACH ROW
BEGIN
   -- trigger logic
END;
```

### 🔹 Why do we need `FOR EACH ROW`?

This is because SQL operations can affect **multiple rows at once**.

Example:
```sql
UPDATE users SET active = 0 WHERE last_login < '2023-01-01';
```

If 100 rows match, the trigger will run **100 times**, once per row.

Without `FOR EACH ROW`, SQL wouldn’t know which row’s data (`NEW` or `OLD`) to access in the trigger logic.

---

### Example: Log name change using trigger

```sql
CREATE TRIGGER before_user_update
BEFORE UPDATE ON users
FOR EACH ROW
BEGIN
    -- Access OLD.name and NEW.name for that specific row
    IF OLD.name != NEW.name THEN
        INSERT INTO audit_log(action)
        VALUES (CONCAT('Name changed from ', OLD.name, ' to ', NEW.name));
    END IF;
END;
```

So the trigger is event-driven — it fires automatically before any update to that table, you don't even need to use "trigger_name" anywhere. Its just a label for your convenience.
