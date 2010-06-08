# Db Evaluator

this project was born out of the idea to get some useful java samples for the common "NoSql" databases
available on the market. it doesn't want to show you the perfect java code. just some typical samples
that should get you up and running quickly.

it shows how to connect with different drivers to different data stores. for all of the examples you'll
need the datastore running unless it's an embedded one (like  neo4j) which will be started during app runtime.

## get up and running
 
you'll need Maven2 to build the application -> <http://maven.apache.org/download.html>

you'll need git to checkout the project -> <http://git-scm.com/>

    $ git clone http://github.com/niesfisch/dbevaluator.git dbevaluator
    $ cd dbevaluator 
    $ mvn clean install -f core/pom.xml

the last step builds the core which is needed by all drivers

choose which driver you want to test by changing into the driver subfolder 

read the README.md to get started

## drivers already implemented:

* mongo-java-driver: the mongo java driver for mongodb -> <http://github.com/niesfisch/dbevaluator/tree/master/mongo-java-driver/>

## drivers NOT implemented yet:

* couchdb4j
* jcouchdb
* jredis
* neo4j

## sample output

this is what a typical run of a db evaluation looks like

    -------------------------------------------------------  
     T E S T S  
    -------------------------------------------------------  
    Running de.marcelsauer.dbevaluator.mongo.javadriver.MongoDbEvaluatorTest  
    2010-06-08 20:05:48,942 [DEBUG] TimedDbEvaluation:  running MongoDbEvaluation evaluation  
    2010-06-08 20:05:49,097 [DEBUG] TimedDbEvaluation:  finished  MongoDbEvaluation in 3 (ms)  
    ---------------------------------------------------------------------  
    total number of blogs processed: 1  
    total number of posts processed: 10 (1 * 10)  
    total time taken (ms): 149  
    action performed: clearing all blogs in 131 (ms)  
    action performed: persisting in 6 (ms)  
    action performed: loading by blog title in 9 (ms)  
    action performed: loading by tags in 3 (ms)  
    ---------------------------------------------------------------------  

