#EC-Group11

#Team Members:
* Minming Qian	|	a1675754	|	a1675754@student.adelaide.edu.au 
* Yue Zhou	|	a1659152	| a1659152@student.adelaide.edu.au
* Feifei Xiong	|	a1655202	|	a1655202@student.adelaide.edu.au
* Yang Lu	|	a1655780	|	a1655780@student.adelaide.edu.au

#Project Introduction:
This project is used to build the model of the TSP problem with specific data and solve it with the genetic algorithm.

##Configuration file:

##Code Structure:
The whole project is designed under the framework developed by Minming Qian. This framework could be divided into four basic components, algorithm, component, configuration and utility. The followed parts will illustrate the function of each components.
###Algorithms
This component is used to generate a evolutionary algorithm with specific operators
###Component
It contains the operator, termination criteria and other fundamental information support the whole algorithm.
###Configuration
It contains the configuration reader, which used to read the related configuration from the YAML file
###Utility
The utility provides other key function which may leverage the whole algorithm

##Genetic Algorithm:
We implement the four crossover operators(OrderCrossover, CycleCrossover, PMXCrossover, EdgeRecombination) and four mutation operators (SwapMutation, InversionMutation, ScrambleMutation, InsertMutation). Beside of this we also implement the three selection approach (Fitness proportional Selection, Tournament Selection, Elitism Selection). We just pick one of them for each operators and combine them together to generate a new genetic algorithm. These series of genetic algorithms is apply on the all test data. The results and analysis for each algorithm is shown in the Excel document. 

##Roulette Structure:

##Termination Criteria Pool:

#How to run the code
* Step1: Build the Java runtime environment
* Step2: Set the parameters in the configuration file
* Step3: Boot the project with maven 

