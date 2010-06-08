*Db Evaluator*

this project was born out of the idea to get some useful java samples for the commong "NoSql" databases
available on the market. it doesn't want to show you the perfect java code. just some typical samples
that should get you up and running quickly.

it shows how to connect with different drivers to different data stores. for all of the examples you'll
need the datastore running unless it's an embedded one (like  neo4j) which will be started during app runtime.

this project consists of different maven2 modules that can be built seperately depending on the datastore you
want to test.

now all you need to do is follow the guides provided in the README.txt of the driver you want to test.
 
1. you'll need Maven2 to build the application
2. git clone http://github.com/niesfisch/dbevaluator.git dbevaluator 
3. now you have to choose which database/driver combination you want to test

there are different README.txt files for each driver.

mongo-java-driver: the mongo java driver for mongodb
couchdb4j: couchdb4j for couchdb
jcouchdb: jcouchdb for couchdb
jredis: jredis for redis
neo4j: neo4j for neo4j ;-)

