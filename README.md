# README #

### Project Specification ###

Scenario:

We have a Product Entity with One to Many relationship with Image entity

Product also has a Many to One relationship with itself (Many Products to one Parent Product)

Build a Restful service using JAX-RS to perform CRUD operations on a Product resource using Image as a sub resource of Product.

Build API classes to perform these operations

a. Get all products excluding relationships (child products, images)
b. Get all products including specified relationships (child product and/or images)
c. Same as *a* using specific product identity
d. Same as *b* using specific product identity
e. Get set of child products for specific product
f. Get set of images for specific product

Build  JPA/Hibernate classes using annotations to persist these objects in the database

1. Maven must be used to build, run tests and start the application.

2. The tests must be started with the mvn test command.

3. The application must start with a Maven command: mvn exec:java, mvn jetty:run, mvn spring-boot:run, etc.

4. The application must have a stateless API and use a database to store data.

5. An embedded in-memory database should be used: either H2, HSQL, SQLite or Derby.

6. The database and tables creation should be done by Maven or by the application.

7. You must provide BitBucket username. A free BitBucket account can be created at [BitBucket](http://bitbucket.org). Once finished, you must give the user ac-recruitment read permission on your repository so that you can be evaluated.

8. You must provide a README.txt (plain text) or a README.md (Markdown) file at the root of your repository, explaining:

  * How to compile and run the application with an example for each call.

  * How to run the suite of automated tests.

  * Mention anything that was asked but not delivered and why, and any additional comments.

### How to compile and run the application ###

To compile the application use:

    mvn clean install

To run the application use:

    mvn spring-boot:run

### How to run the suite of automated tests ###

To run the suite of automated tests use:

    mvn test

### Notes ###

In the original specification items *c* and *d* relate to items *a* and *b* and not *1* and *2* as was previously.
