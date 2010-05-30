this project was born out of the idea to get some useful java samples for the commong "NoSql" databases
available on the market. it doesn't want to show you the perfect java code. just some typical samples
that should get you up and running quickly.

it shows how to connect with different drivers to different data stores. for all of the examples you'll
need the datastore running unless it's an embedded one (like  neo4j) which will be started during app runtime.

this project consists of different maven2 modules that can be built seperately depending on which datastore you
want to test.

now all you need to do is follow the guides further down that get you started with the datastore/driver combination
you want to test :)
 
1. you'll need Maven2 to build the application
2. git clone http://github.com/niesfisch/dbevaluator.git dbevaluator 
3. now you have to choose which database/driver combination you want to test

open the xxx_README.txt to continue
e.g. Mongo_java_driver_README.txt to test the Mongo Java Driver with the running MongoDB instance. 

mongodb (implemented)
**********************************
mongo-java-driver
-----------------
1. download mongo db
2. start the mongo db server
3. cd dbevaluator
4. mvn clean test -Pmongodb

couchdb (NOT implemented yet)
**********************************
couchdb4j
---------
1. svn checkout http://couchdb4j.googlecode.com/svn/trunk/ couchdb4j-read-only
2. cd couchdb4j-read-only
3. mvn clean install -DskipTests
4. download couchdb server
5. start the couchdb server
6. cd dbevaluator
7. mvn clean test -Pcouchdb4j

jcouchdb
--------
1. download couchdb server
2. start the couchdb server
3. cd dbevaluator
4. mvn clean test -Pjcouchdb

redis (NOT implemented yet)
**********************************
jredis
------
1. download redis server
2. start redis server
3. git clone http://github.com/alphazero/jredis.git jredis-read-only
4. cd jredis-read-only
5. mvn clean install -DskipTests
6. cd dbevaluator
7. mvn clean test -Pjredis
 
neo4j (NOT implemented yet)
**********************************
... describe embedded stuff.....

1. start embedded neo4j server
3. cd dbevaluator
4. mvn clean test -Pneo4j
 
