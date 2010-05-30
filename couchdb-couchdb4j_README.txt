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

