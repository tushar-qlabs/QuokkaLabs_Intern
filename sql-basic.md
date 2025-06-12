**SQL Concepts Practiced So Far**

1. **Basic SELECT Queries**
    - Selecting specific columns (`SELECT name, salary FROM employees WHERE department = 'IT';`)
    - Using `WHERE` clause to filter results
    - Sorting results using `ORDER BY`
2. **Filtering Data**
    - Using `AND` & `OR` conditions in `WHERE` (`WHERE status='Shipped' AND amount > 100;`)
    - Understanding `=` vs `==` (SQL uses `=` for comparisons)
3. **Sorting Data**
    - `ORDER BY column_name ASC/DESC` (`ORDER BY price DESC;`)
4. **Aggregation Functions**
    - `SUM()`, `COUNT()`, `AVG()`, `MIN()`, `MAX()`
    - Using `GROUP BY` to group data (`GROUP BY category;`)
    - Filtering grouped results using `HAVING`
5. **JOINs (Combining Tables)**
    - `INNER JOIN`: Matches only matching records in both tables
    - `LEFT JOIN`: Includes all from the left table + matching from the right table
    - Using `COALESCE()` to handle NULL values (`COALESCE(product, 'no_product');`)
        - **Breakdown:** COAL-ESC-E (Think of it as escaping NULL values with a fallback)
6. **GROUP BY Rule (Important)**
    - When using `GROUP BY`, any column in `SELECT` must be.
        - Either in `GROUP BY`
        - OR inside an aggregate function (`SUM()`, `COUNT()`, etc.)
    - This ensures that SQL knows how to group and summarize data properly.
    - Fixing errors by adding columns to `GROUP BY` or using `ANY_VALUE()`
    - So, in simple.... If a column is not used inside an aggregate function (SUM(), COUNT(), etc.), then it must be listed in the GROUP BY clause.
7. **Handling NULL Values**
    - `COALESCE(column, default_value)` to replace NULLs
8. **Sorting Aggregated Data**
    - `ORDER BY total_amount DESC` after aggregation (`SUM()`, `COUNT()`, etc.)
9. **WHERE vs HAVING**
    - `WHERE` is used to filter **before** `GROUP BY` and aggregation.
    - `HAVING` is used to filter **after** `GROUP BY` and aggregation.
    - Example:
        
        ```sql
        SELECT category, SUM(amount) AS total_sales
        FROM sales
        WHERE amount > 500  -- Filters raw data before grouping
        GROUP BY category;
        
        ```
        
        ```sql
        SELECT category, SUM(amount) AS total_sales
        FROM sales
        GROUP BY category
        HAVING SUM(amount) > 5000;  -- Filters aggregated results based on grouped data
        
        ```
        
    - **Shortcut to Remember:**
        - **`WHERE` → Pehle filter, fir `GROUP BY`**
        - **`HAVING` → Pehle `GROUP BY`, fir aggregated results pe filter**
