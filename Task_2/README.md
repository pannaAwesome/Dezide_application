# Task #2
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