# Command Line RPN Calculator
Implementation of a calulator using Reverse Polish Notation(RPN)


### How to run

```
        gradle clean build
        java -jar build\libs\calculator-0.0.1-SNAPSHOT.jar

```

### Implementation Details:
* Follows a hexagonal approach with separation of `domain`, `application`, `adapters`
* Calculator uses RPN for execution(Expressed as [RPNExpression](src/main/java/com/airwallex/calculator/domain/expression/rpn/RPNExpression.java))
* Calculator is using [RealNumber](src/main/java/com/airwallex/calculator/domain/realnumber/RealNumber.java) which is decoupled from Expression
* Operations on expressions are done by `Operators` derived from [Operator](src/main/java/com/airwallex/calculator/domain/expression/operator/Operator.java)
* Need to add more test coverage
  
