# Task #1
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
*input* : `java -jar costconverter.jar 0.5 275.50 model1234` \
*output* : `280`