# Lego Backend Homework

## Preface

We respect your time and don't want you to spend more than 4 hours on this test.
There is an ordered list of tasks provided for you to complete. 
This list is intentionally open-ended - meaning it is not necessary for you to complete all the tasks.
Completing the whole list within the time limit is not doable for most candidates
as this requires a lot of experience with the specific technology used. 
If you are not able to complete the whole list, this does not mean you failed the test! Please apply anyway.

**Please do not upload your solution to a public Git repository. Instead, send a zip file back to us.**

## How to Start the Lego Homework Application

The application requires Java 17 and Maven 3.6.0+.

1. Run/Debug class `LegoApplication` in the IDE of your choice

or

1. Run `mvn clean package` to build your application
2. Start the application with `java -jar target/lego-1.0-SNAPSHOT.jar server`
   
To check that your application is running, fetch url [http://localhost:8080](http://localhost:8080)

## General Implementation Notes

Your task will be to build a simple LEGO Sets API using Spring Boot as (Application) Server Framework. We have bootstrapped the basics for you.
The LEGO Dataset is already provided in a SQLite Database file (see `bricks.db`). 

**Tests are not required** - we only care about the production code for this task but the code should be easy to test if we would want to do that.

**Additional dependencies not recommended** - please use the provided dependencies and roughly stick to the predefined scaffold.

## Getting started

* Run LegoApplication
* Check the example resource provided at [http://localhost:8080/sets](http://localhost:8080/sets) (see `SetsResource.java`)
* Get familiar with the DB Schema (`bricks.db` and the [documentation](https://www.kaggle.com/rtatman/lego-database?select=sets.csv)) and take a look at how it is accessed (see `LegoDAO.java`) 

## Tasks

**A** Extend the `/sets` endpoint so that it allows to search for `setName`, `themeName` and `year` by query parameters.
Make sure that these filter parameters can be combined freely, i.e. `/sets` is a valid request as well as `/sets?year=1995`,
`/sets?themeName=Airport&setName=Helicopter` or `sets?setName=Helicopter&year=1995`. Return the first 50 matching entries
ordered by `set_num`. Return the following fields for each entry in the JSON response body:
* Set Number
* (Set) Name
* Year
* Theme ID
* Theme Name
* Number of Parts
Hint: JDBI offers both a Fluent API (used in the code we provided) and a Declarative API. You are free to use any of them.

**B** Add additional endpoints and/or change existing ones allowing us to manage a collection of favorite sets.
* Add set to favorites
* Remove set from favorites
* Retrieve stored favorites

You need to change the database schema to store the favorites.
Please create a `migration.sql` file containing a SQL statement that we can execute manually or even better, make sure the application itself changes the schema when it is started.

**C** Extend the `/sets` endpoint with a pagination mechanism allowing to retrieve all the matches in chunks.

**D** Extend the `/sets` endpoint to include a list `setCountByYear` that shows the total number of entries by year.
The result must be according to the specified filter parameters. Example response payload:
```
{
    ...,
    "setCountByYear": [
        {
            "key": "1949",
            "count": 5
        },
        {
            "key": "1950",
            "count": 6
        },
        ....
    ]
}
```

**E** Define the `year` parameter of the `/sets` endpoint as an `Integer`. When a non-integer argument is used
as in `/sets?year=x`, make sure `HTTP 400` is returned and the following response body is present:
`{"status":400,"message":"Parameter 'year' was supplied with invalid value 'x'"}`.
You will need to use a Spring mechanism to achieve this.


## Resources

* Lego Dataset: <https://www.kaggle.com/rtatman/lego-database>
* Spring Boot: <https://docs.spring.io/spring-boot/docs/3.0.0/reference/htmlsingle/> (Documentation)
* JDBI: <http://jdbi.org/>
* SQLite: <https://sqlite.org/>
    * if your IDE does not support direct access you can either use the command line tools or something like <https://sqlitebrowser.org/>
    
    
## Solution:

New Endpoints:

```
http://localhost:8080/favorites

```

To list, add and delete favorites

Request Body example:

```
{
    "setId": "10218-1"
} 
```


Modified Endpoints:

```
http://localhost:8080/sets?year=2011&limit=2&offset=0
```

To list sets

Sample Response:

```
{
    "sets": [
        {
            "num": "0756682762-1",
            "name": "Ninjago Brickmaster",
            "year": 2011,
            "themeId": 497,
            "themeName": "Books",
            "numParts": 154
        },
        {
            "num": "10217-1",
            "name": "Diagon Alley",
            "year": 2011,
            "themeId": 246,
            "themeName": "Harry Potter",
            "numParts": 2031
        }
    ],
    "setCountByYear": [
        {
            "key": "2011",
            "count": 2
        }
    ]
}
```


Added a Makefile in order to automate the dockerization behavior

And a devops directory containing the Dockerfile

Thanks