# JetPackProblem
One day a mathematician by name Ramakrishna was travelling on a Train which is fully crowded with no space to walk through the aisle, to submit his final thesis paper, his conversation with his fellow passenger Robert have put him in a situation to solve an interesting problem , Robert  hides the mathematician thesis paper in one of the compartments of the train, warns Ramakrishna the thesis will be burnt if he doesn't find them as soon as possible, considering the train is packed Robert equips Ramkrishna with a JETPACK(this is a wearable that can be used to hop from one compartment(coach) to another), in each compartment a threshold value for JETPACK is placed, the significance of this value is JETPACK cannot jump over more than the Threshold value, (meaning if a JETPACK is in compartment 2 where threshold value is 4, the JETPACK in one hop can max move to compartment 6 from 2, however user can choose to land anywhere between 3-6), Ramakrishna have to utilise JETPACK and find his Thesis in minimum hops.

Write a program which takes number of compartments as inputs, takes JETPACK threshold values for each compartment, and the value of the compartment where the thesis paper is hidden, and return the minimum hops Ramkrishna have to make to find his thesis paper.

Note: Always Ramakrishna have to start from compartment 1
 
Example :

Below is the input of compartments , values in each box is JetPack Thresholds

2 -> 3 -> 1 -> 1 -> 3
 

The Thesis is hidden in compartment 5

Output:
2
Reasoning:
Path:
1->3>4->-5  : number of hops 3
1->2->5       :number of hops 2

# Solution:
Since the search will always starts from index 1, we need to check the difference between current index and the destaination index.
If the difference is zero, then return the hop count
If the difference is 1, then increase the hop count by 1 return
If the difference is greater than 1, the following logic applies:
   -> Compute next compartment value by adding current compartment index and it's cost.
   -> if nextCompartmentIndex == destinationIndex
        then increase the hop count by 1 and return
   -> else if nextCompartmentIndex > destinationIndex
       then increase the hop count by one and move to next compartment
   -> else  
      if next compartment cost is greater than current compartment cost
        then move to next compartment
      else if next compartment cost is less than equals current compartment cost
        then move to the compartment number calculated by adding the current index and current compartment cost
        
        
   Overall time complexity will be O(n), where n is the number of compartments
  # Steps to execute the code:
  1. Clone the project
  2. Open in Android Studio and run either using a emulator or real device
  3. After the launching the App the UI will look like this
    [![Jet-Pack-Problem1.jpg](https://i.postimg.cc/GpN8Hvkd/Jet-Pack-Problem1.jpg)](https://postimg.cc/Ffys6dWB)  
  4. Click on Add to add the compartment cells, each cell will have index number on top and an edit box where the user can enter the cost
  5. Using the delete button, the user can delete one by one from end
[![Jet-Pack-Problem2.jpg](https://i.postimg.cc/3RThSRh2/Jet-Pack-Problem2.jpg)](https://postimg.cc/67YFTByQ)
6. The destination index edit text will contain the destination compartment index value.
  7. Click on Find button to get the minimum number of hops.
  8. Click on reset button to reset the values.
[![Jet-Pack-Problem3.jpg](https://i.postimg.cc/sDFL58VZ/Jet-Pack-Problem3.jpg)](https://postimg.cc/G9QzCXTc)  
        
   
