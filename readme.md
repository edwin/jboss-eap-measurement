# Measuring Response Speed using Standard SQL Connections, Connection Pooling, and Cached Response

## What is this
A simple way to measure API response speed from 3 different approaches. 

- API with Regular SQL Connections 
- API using ConnectionPool 
- API with cache response

## Direct Query 

### Testing Script
```
for ((i=1;i<=10;i++)); 
	do  
		curl "http://localhost:8080/selectWithDirectSQL" -w '%{time_total}' -o /dev/null -s >> output.txt; 
		echo "" >> output.txt; 
	done
```

### Result

| cycle   | Time      | 
|---------|-----------|
| 1       | 0.227550  |
| 2       | 0.239513  |
| 3       | 0.221113  |
| 4       | 0.222169  |
| 5       | 0.210407  |
| 6       | 0.222037  |
| 7       | 0.222241  |
| 8       | 0.229445  |
| 9       | 0.230644  |
| 10      | 0.230796  |
| Total   | 2.255915  |
| Average | 0.2255915 |


## Pooled Query 


### Testing Script
```
for ((i=1;i<=10;i++)); 
	do  
		curl "http://localhost:8080/selectWithPooledSQL" -w '%{time_total}' -o /dev/null -s >> output.txt; 
		echo "" >> output.txt; 
	done
```

### Result

| cycle   | Time      | 
|---------|-----------|
| 1       | 0.222648  |
| 2       | 0.223938  |
| 3       | 0.219115  |
| 4       | 0.216680  |
| 5       | 0.211065  |
| 6       | 0.217890  |
| 7       | 0.214914  |
| 8       | 0.210799  |
| 9       | 0.221677  |
| 10      | 0.220326  |
| Total   | 2.179052  |
| Average | 0.2179052 |


## Summary
| Method | Average  | Summary       |
|--------|----------|---------------|
| Direct Query       | 0.2255915 | Original Data |
| Pooled Query       | 0.2179052 |  ~4% Faster   |