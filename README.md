# Db Evaluator

this project was born out of the idea to get some useful java samples for the common "NoSql" databases
available on the market. it is all based around some trivial tasks that the database/driver combination under test has to execute sequentially.
it is by no means intended to show you the best java code :) - just some simple code to get someone started with a database/driver combination.

## this what the uml of the "simple blog" looks like

![blog and posts uml](http://marcel-sauer.de/dbevaluator/uml.png)

for all of the examples you'll need the datastore running unless it's an embedded one (like neo4j) which will be started during application runtime.

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

## what does it do?

running a specific db evaluation like "mongo-java-driver" executes the following steps in sequence and it's up to the implementation to decide what to do.
Check the "Sample Output" section to see some results.

### Non-Transactional
1. clear the database for the test
2. persist n-blogs with n-posts per blog
3. load all blogs by title and initialize all subsequent post they have (eager loading)
4. load all posts containing the tags specified and make sure the blogs they belong to are also loaded (eager loading, so that post.getBlog() != null)

### Transactional
the same steps described unter "Non-Transactional" will be executed. every step will be wrapped by a call to "startTransaction()" and "commit()" if this is 
supported by the driver. 

for all steps the time (in ms) will be measured and a summary will be displayed at the end.

## Sample Output

this is what a typical run of a db evaluation looks like

    -------------------------------------------------------  
     T E S T S  
    -------------------------------------------------------  
    Running de.marcelsauer.dbevaluator.mongo.javadriver.MongoDbEvaluatorTest
    2010-06-20 16:54:50,994 [DEBUG] TimedDbEvaluation:  running MongoDbEvaluation evaluation
    2010-06-20 16:54:51,692 [DEBUG] TimedDbEvaluation:  finished  MongoDbEvaluation
    ---------------------------------------------------------------------
    total number of blogs processed: 1
    total number of posts processed: 10 (1 * 10)
    total time taken (ms): 692
    action performed: clearing all blogs in 622 (ms)
    action performed: persisting in 21 (ms)
    action performed: loading by blog title in 45 (ms)
    action performed: loading by tags in 4 (ms)
    operation 'clearing all blogs transactional' not supported. reason: transactions are not supported by Mongo DB
    operation 'persisting transactional' not supported. reason: transactions are not supported by Mongo DB
    operation 'loading by blog title transactional' not supported. reason: transactions are not supported by Mongo DB
    operation 'loading by tags transactional' not supported. reason: transactions are not supported by Mongo DB
    ---------------------------------------------------------------------  

