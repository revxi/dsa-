SELECT 
price, year, product_name
FROM Sales
LEFT JOIN Product on
Sales.product_id = Product.product_id