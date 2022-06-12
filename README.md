# cognera-test
 cognera-test

mvn clean install

You can refer Cognera.postman_collection.json to see the result.

curl --location --request POST 'http://localhost:8081/test/v1/count-words' 

curl --location --request POST 'http://localhost:8081/test/v1/document-similarity'

curl --location --request POST 'http://localhost:8081/test/v1/find-nodes?node=C&maxHops=2'    