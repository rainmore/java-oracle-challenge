# Oracle Challenge

## Setup
Please ensure JDK 17 is installed.

## How to run the code?

**Method 1** is to use Gradle `run` task as followings. 

Please ensure all CSVs are quoted by single quotes or double quotes.

```base
./gradlew run --args '2343225,2345,us_east,RedTeam,ProjectApple,3445s
1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s
1233456,2345,us_west,BlueTeam,ProjectDate,2221s
3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s'
```

**Method 2** is to use the jar

```base
./gradlew build

java -jar build/libs/oracle-challenge-1.0-SNAPSHOT.jar '2343225,2345,us_east,RedTeam,ProjectApple,3445s
1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s
1233456,2345,us_west,BlueTeam,ProjectDate,2221s
3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s'
```

The expected output for above command is 

```
The number of unique customerId for contractId 2345 is 3
The number of unique customerId for contractId 2346 is 2

The number of unique customerId for geozone "us_east" is 1
The number of unique customerId for geozone "us_west" is 2
The number of unique customerId for geozone "eu_west" is 2

The average buildduration for geozone "us_east" is 3445s
The average buildduration for geozone "us_west" is 2216s
The average buildduration for geozone "eu_west" is 4222s

The list of unique customerId for geozone "us_east" is (2343225)
The list of unique customerId for geozone "us_west" is (1223456, 1233456)
The list of unique customerId for geozone "eu_west" is (3244132, 3244332)
```

## Codebase Structure

### Production Code Structure
The production codebase is implemented in the following 3 categories.

* Domains
  * The domains define the data structure of CSV data.
  * The implementation is defined in `au.com.rainmore.domains` package.
* Services
  * The services define the business logic for the domains.
  * The implementation is defined in `au.com.rainmore.services` package.
  * The class name is prefixed with the domain class name and is suffixed with `Service`.
* The Main-Class 
  * The entry point of the application is defined in `au.com.rainmore.Main` class. 
  * It calls the services logic, handles the exceptions and exists the current program.


### Unit Test Code Structure
The unit test codebase is implemented in the following 2 categories.

* Utilities
  * The utilities methods to help for the testing.
  * The implementation is defined in `au.com.rainmore.utils` package.
  * The class name is suffix with `Utils`.
* Features
  * The re-usable test data(features) to avoid code duplication.
  * The implementation is defined in `au.com.rainmore.utils.features` package.
  * The class name is suffix with `Features`.
* Unit Tests -- The unit test cases for each implementation in the production code.
