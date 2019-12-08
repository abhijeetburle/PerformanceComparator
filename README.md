### Project to compare GSON, FasterXMLJackson, JsonPatchBuilder
Project creates same type of JSON using java MAP in GSON, FasterXMLJackson or JsonPatchBuilder and comapre the performance results

### Behavior
1. Populate the Map use com.google.gson.Gson, com.fasterxml.jackson to create JSON Object 
2. Uses JsonPatchBuilder to create Patch to create JSON Object
3. The test is done to iterate to create 10,100,1000,10000,100000 JSON's 

### How to run
1. Import the porject as Gradle Project in IDE
2. Run the JUNIT com.github.abhijeetburle.performancecomparatorMapToJsonComparison.MapToJsonComparison
3. Test Method convertMapToJson will print 
<LIB>,iterations,<iterations>,totalTime,<totalTime>ms,avg,<avgTime>ms


### Results
When done on my local the results were as below.
FasterXMLJackson was the best choice , GSON was ok and JsonPatchBuilder turned out to be the worst in time performance.
(need to check memory footprint yet)

#### RUN1

| LIB               | 10      | 100    | 1000   | 10000  | 100000 |
| ----------------- | ------- | ------ | ------ | ------ | ------ |
| FasterXMLJackson  | 1.4162  | 0.0651 | 0.0199 | 0.0059 | 0.0021 |
| GSON              | 2.4127  | 0.4237 | 0.0955 | 0.0377 | 0.0154 |
| JsonPatchBuilder  | 23.6145 | 4.1082 | 3.5171 | 3.2574 | 3.326  |


#### RUN2

            
| LIB               | 10      | 100    | 1000   | 10000  | 100000 |
| ----------------- | ------- | ------ | ------ | ------ | ------ |
| FasterXMLJackson  | 0.1849  | 0.0991 | 0.0289 | 0.0042 | 0.0029 |
| GSON              | 0.4374  | 0.4826 | 0.1025 | 0.0359 | 0.0178 |
| JsonPatchBuilder  | 9.5872  | 4.6038 | 3.4055 | 3.2477 | 3.3038 |


#### RUN3


| LIB               | 10      | 100    | 1000   | 10000  | 100000 |
| ----------------- | ------- | ------ | ------ | ------ | ------ |
| FasterXMLJackson  | 0.1928  | 0.0992 | 0.0351 | 0.0156 | 0.0026 |
| GSON              | 0.7179  | 0.6639 | 0.2078 | 0.0462 | 0.02   |
| JsonPatchBuilder  | 16.682  | 4.4414 | 3.2872 | 3.176 | 3.2388  |


#### RUN4


| LIB               | 10      | 100    | 1000   | 10000  | 100000 |
| ----------------- | ------- | ------ | ------ | ------ | ------ |
| FasterXMLJackson  | 0.2192  | 0.1532 | 0.0545 | 0.0101 | 0.0026 |
| GSON              | 0.6691  | 0.487  | 0.2381 | 0.0388 | 0.0212 |
| JsonPatchBuilder  | 23.8479 | 5.1054 | 4.1537 | 3.1983 | 3.1478 |

