# Db Evaluator

this project was born out of the idea to get some useful java samples for the commong "NoSql" databases
available on the market. it doesn't want to show you the perfect java code. just some typical samples
that should get you up and running quickly.

it shows how to connect with different drivers to different data stores. for all of the examples you'll
need the datastore running unless it's an embedded one (like  neo4j) which will be started during app runtime.

## get up and running
 
    1. you'll need Maven2 to build the application -> <http://maven.apache.org/download.html>
    2. you'll need git -> <http://git-scm.com/>
    3. git clone http://github.com/niesfisch/dbevaluator.git dbevaluator
    4. cd dbevaluator 
    5. mvn clean install -f core/pom.xml -> builds the core which is needed by all drivers
    6. choose which driver you want to test by changing into the driver subfolder 
    7. read the README.md to get started

## drivers already implemented:

* mongo-java-driver: the mongo java driver for mongodb

## drivers NOT implemented yet:

* couchdb4j
* jcouchdb
* jredis
* neo4j
