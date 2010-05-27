this project was born out of the idea to get some useful java samples for the commong "NoSql" databases
available on the market...... to be continued..... still in development.

......... a short guide to get you started

1. install maven2
2. install git
3. install svn
4. git clone http://github.com/niesfisch/dbevaluator.git dbevaluator 

now you have to choose which database and driver to test

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
 