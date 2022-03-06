# Dezide Tasks

The Dezide Troubleshooter considers causes, probability, time and cost when making its
suggestion for the best corrective action that is suggested in the Troubleshooter. The cost of
something is considered a monetary value that is a function of time , money and optionally
other customer specific factors like risk and inconvenience.

Each customer system is configured with a mandatory global value for the relationship
between time and money (estimated cost of an hour). Each troubleshooting model may
specify another hourly cost, that should be used instead of the global factor.

There are two tasks in this assignment. The first task asks you to build a basic reusable cost
converter component (to be used in a larger system), and the second task asks you to
expand upon your solution for task one by extending your solution to handle the custom
costs, too.

The calculations are trivial, so it is important that you demonstrate the ability to design a
solid software architecture that shows your thoughts around good software development
craftsmanship.

Your code should reflect your ability to build modular, high-quality software that is designed
to be maintainable in the long run.

The solution must be implemented in Java. We expect that you spend no more than a
couple of days on building your solution. You can send the code to us in whatever way you
find easier (zip, github, etc.).

We are looking forward to hearing about your design choices and the reasoning behind
them!

## Task #1
Your first task is to implement a component/module that takes time, money and a model id
as mandatory input and returns the total calculated cost, rounded off to the nearest whole
number.

The Troubleshooter requires potentially hundreds of cost calculations for each
troubleshooting step that the user performs in the troubleshooting model and the
Troubleshooter should therefore be able to use your component to perform the calculations.

For demonstration purposes it must also be possible to run the component from the
command line. A sample Java cmd line could look like:

    java -jar costconverter.jar <time> <money> <model>

Where:
* *\<time>* is measured in minutes
* *\<money>* could be the cost of spare parts or other direct costs
* *\<model>* is an identifier of the model

The basic output must be the total cost calculated as:

        Total cost = time x timeFactor + money

Use the monetary values of one hour as indicated in the table below:
|  | **Monetary value of 1 hour** |
|----------|:----------:|
| **Global time factor** | 300 |
| **Local time factor "model1234"** | 500 |

The global time factor must be used if local factors for the specified model is not found.

**Example :** \
*input* : `java -jar costconverter.jar 0.5 275.50 model1234`
*output* : `280`

## Task #2
Task #1 describes the basic cost conversion function. In a more realistic scenario, the cost
conversion is comprised of multiple customer specific factors such as risk, inconvenience,
insult, inaccuracy and others. These customer-specific factors are used to adjust the total cost after the basic calculation has been performed.

The custom costs are configured as default adjustments, allowing the component to explicitly
use other values for calculating costs.

Your second task is to extend the cost calculator design to include support for the optional
customer-specific costs.

### The custom costs:
Each custom cost has a name that identifies the type of cost and a string value.

The names for this example are:
* Risk
* Inconvenience

Possible values (for both types) are:
* low
* medium
* high
* none

A sample Java cmd line could look like:
```
java -jar costconverter.jar <time> <money> <model> [<custom> ...]
```

Where:
* *\<time>* is measured in minutes
* *\<money>* could be the cost of spare parts or other direct costs
* *\<model>* is an identifier of the model
* *[\<custom> ...]* Zero or more custom cost values of the format *\<name>:\<value>*

The basic output must be the total cost calculated as:
```
    Total cost = customCostAdjustments( time x timeFactor + money )
```
For customer systems that use custom costs, each cost default value must be configured
globally. Additionally, specific models may override one or more of the custom cost defaults.

An example configuration is:
| | Risk | Inconvenience |
|- | - | - |
| Global factors | low | high |
| Local factors for “model1234” | high | medium |

The discrete values of custom cost values translate to the following adjustment factors:

| Value Relative adjustment of the final | cost |
| - | :-: |
| low | +10% |
| medium | +30% |
| high | +100% |
| none | 0% |

**Example:** \
*input* : `java -jar costconverter.jar 0.5 275.50 model1234 Inconvenience:none` \
*output* : `559`

The output here is adjusted with Risk: high (model default) and “no inconvenience” (command line override).