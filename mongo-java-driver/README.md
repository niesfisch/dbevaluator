# Mongo Java Driver for MongoDB

make sure you've followed the step under *get up and running* (<http://github.com/niesfisch/dbevaluator>)

1. download mongo db -> <http://www.mongodb.org/display/DOCS/Downloads>
2. start the mongo db server -> <http://www.mongodb.org/display/DOCS/Quickstart>
3. cd mongo-java-driver
4. mvn clean install -DargLine="-Dblogs=1 -Dposts=10 -DprintCapturedInfo=false" 

### Configuration
    change /mongo-java-driver/src/test/resources/db.properties if you want to tweak the connection settings and run 4.) again

    -Dblogs=x -> number of blogs to create

    -Dposts=x -> number of posts per blog to be created

    -DprintCapturedInfo=true|false -> print information that was captured while the test was running


### more info

http://www.mongodb.org/display/DOCS/Java+Language+Center
