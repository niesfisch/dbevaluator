# Mongo Java Driver for MongoDB

make sure you've followed the step under *get up and running* (<http://github.com/niesfisch/dbevaluator>)

download mongo db -> <http://www.mongodb.org/display/DOCS/Downloads>

start the mongo db server -> <http://www.mongodb.org/display/DOCS/Quickstart>


    $ cd mongo-java-driver

    $ mvn clean install -DargLine="-Dblogs=1 -Dposts=10 -DprintCapturedInfo=false" 

### Configuration

#### change the connection settings

    mongo-java-driver/src/test/resources/db.properties

#### change how many blogs/posts will be created

    -Dblogs=x -> number of blogs to created

    -Dposts=x -> number of posts per blog to be created

    -DprintCapturedInfo=true|false -> print information that was captured while the test was running

### more info

http://www.mongodb.org/display/DOCS/Java+Language+Center

### Troubleshooting

#### Out of Memory Errors

this can happen if you try to create _a lot_ of posts/blogs. try to increase the "Xmx" parameter, e.g.:

    $ mvn clean install -DargLine="-Dblogs=100 -Dposts=10000 -DprintCapturedInfo=false -Xmx2048m"
