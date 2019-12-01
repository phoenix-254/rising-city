# rising-city

## Problem description

<p>
Wayne enterprises is developing a new city. They are constructing many buildings and plan to use software to keep track of all the buildings under construction. 

*A building record has the following fields:*
- buildingNum: A unique integer identifier for each building.
- executedTime: total number of days spent so far on this building.
- totalTime: total number of days needed to complete the construction of the building.

In order to complete the given task, you must use a ```MinHeap``` and a ```RedBlackTree(RBT)```. You must write your own code for the MinHeap and RBT. Also, you may assume that the number of active buildings will not exceed **2000**.

A MinHeap should be used to store (buildingNum, executedTime, totalTime) triplets ordered by executedTime. You will need a suitable mechanism to handle duplicate executedTimes in your MinHeap. An RBT should be used store (buildingNum, executedTime, totalTime) triplets ordered by buildingNum. You are required to maintain pointers between corresponding nodes in the MinHeap and RBT.
 
Wayne Construction works on one building at a time. When it is time to select a building to work on, the building with the lowest executedTime (ties are broken by selecting the building with the lowest buildingNum) is selected. The selected building is worked on until complete or for 5 days, whichever happens first. If the building completes during this period it's number and day of completion is output and it is removed from the data structures. Otherwise, the building’s executedTime is updated. In both cases, Wayne Construction selects the next building to work on using the selection rule. When no building remains, the completion date of the new city is output.

You are required to maintain a global timer which starts at 0 and increments as the day passes. You cannot insert a building for construction to the data structures unless your global timer equals to the arrival time of the construction. All the time data are given in days.


*Example input test cases:*
```
0: Insert(5,25)
2: Insert(9,30)
7: Insert(30,3)
9: Print(30)
10: Insert(1345,12)
13: Print(10,100)
```

The number at the beginning of each command is the global time when the command has appeared in the system. You can assume this time is an integer in increasing order. When no input remains, construction continues on the remaining buildings until the city is built.

*Input should be read from the file named "input_file.txt".*


*Example output:*
- Insert(buildingNum,totalTime) - should produce no output unless buildingNum is a duplicate in which case you should output an error and stop.
- Print(buildingNum) - output the (buildingNum, executedTime, totalTime) triplet if the buildingNum exists. If not print (0,0,0).
- Print(buildingNum1,buildingNum2) - output all (buildingNum, executedTime, totalTime) triplets separated by commas in a single line including buildingNum1 and buildingNum2; if they exist. If there is no building in the specified range, output (0,0,0). You should not print an additional comma at the end of the line.
- Other output includes completion date of each building and completion date of city.

*All output should go to a file named “output_file.txt”.*

</p>


## Build Steps

- Clone or download the repo using `git clone https://github.com/phoenix-254/rising-city.git`
- move to `src` directory and run `make` to compile source - this will generate risingCity.class compiled file which we can run thourgh command line
- run `risingCity input_file.txt` to run the program - this will generate program output in output_file.txt
- Fiddle around with your own test cases by editing input_file.txt and verifying if correct output_file.txt is generated.
- Extra: run `make clean` to clean the compiled binaries


## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)
- **[MIT license](http://opensource.org/licenses/mit-license.php)**
