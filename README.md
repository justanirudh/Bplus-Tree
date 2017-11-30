# B+ Tree
An implementation of an in-memory B+ Tree. Supports the following ops:
1. Initialize(m): create a new order m B+Tree 
2. Insert (key, value)  
3. Search (key) : returns all values associated with the key 
4. Search (key1,key2): returns (all key value pairs) such that key1 <= key <= key2.  

Setup:
1. go to src
2. Run 'make'
3. For an input file, do: java treesearch file_name
The input format should be like below:

12 

Insert(3.55,Value1) 

Insert(4.01,Value10) 

Insert(39.56,Value2) 

Insert(-3.95,Value23) 

Insert(-3.91,Value54609) 

Insert(3.55,Value67) 

Insert(0.02,Value98) 

Search(3.55)  

Search(-3.91,30.96) 

Insert(3.26,Value56089)

Insert(121.56,Value1234) 

Insert(-109.23,Value43234) 

Search(3.71)

where 12 is the order of the B+ Tree

output format will be like below:

Value 67, Value1 

(-3.91,Value54609), (0.02,Value98), (3.55,Value1), (3.55,Value67), (4.01, Value10)  

Null
