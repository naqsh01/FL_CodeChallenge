# FL Code Challenge

Code challenge including bonus (sorted in alaphbetical order).

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents** 

- [FL Code Challenge](#fl-code-challenge)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installing](#installing)
    - [How to Run Locally](#how-to-run-locally)
  - [Assumptions](#assumptions)
  - [Examples](#examples)
  - [Authors](#authors)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
- Java 8 
- JAVA_HOME environment variable should be set to a JDK

### Installing

A step by step series of examples that tell you have to get a development env running

**Clone repository locally**

```
> git clone https://github.com/naqsh01/FL_CodeChallenge.git
```

### How to Run Locally
In root project directory:
```
> java -jar FL_CodeChallenge.jar
```
![image](https://cloud.githubusercontent.com/assets/3868736/23201546/0faa1b42-f8a8-11e6-81fd-9844b604c8e5.png)

## Assumptions
- Designed to be dynamic. It will convert the input to a json and then process it. 
- Better way to handle this would be to make a JSON Schema (Pojo) 
- Keys can't have spaces
- No embeded { or } 
- Can handle up to 3+ levels 
- No automation tests but started to create junit and mocking out data

## Examples
Example: (id,created(date),employee(id(c,a,b),firstname,employeeType(id), lastname),location(Region)) 
![image](https://cloud.githubusercontent.com/assets/3868736/23201713/dd708854-f8a8-11e6-8b36-3140bd875f02.png)


Example: (id,created(date),employee(id (a(b(c))),firstname,employeeType(id), lastname),location(region(isWest, isEast)))
![image](https://cloud.githubusercontent.com/assets/3868736/23201741/f971d440-f8a8-11e6-81d4-9a8608ea60f5.png)


## Authors

* **Shozab Naqvi** - *Initial work* - [Github](https://github.com/naqsh01)



