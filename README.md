# JetPackProblem
One day a mathematician by name Ramakrishna was travelling on a Train which is fully crowded with no space to walk through the aisle, to submit his final thesis paper, his conversation with his fellow passenger Robert have put him in a situation to solve an interesting problem , Robert  hides the mathematician thesis paper in one of the compartments of the train, warns Ramakrishna the thesis will be burnt if he doesn't find them as soon as possible, considering the train is packed Robert equips Ramkrishna with a JETPACK(this is a wearable that can be used to hop from one compartment(coach) to another), in each compartment a threshold value for JETPACK is placed, the significance of this value is JETPACK cannot jump over more than the Threshold value, (meaning if a JETPACK is in compartment 2 where threshold value is 4, the JETPACK in one hop can max move to compartment 6 from 2, however user can choose to land anywhere between 3-6), Ramakrishna have to utilise JETPACK and find his Thesis in minimum hops.

Write a program which takes number of compartments as inputs, takes JETPACK threshold values for each compartment, and the value of the compartment where the thesis paper is hidden, and return the minimum hops Ramkrishna have to make to find his thesis paper.

Note: Always Ramakrishna have to start from compartment 1
 
Example :

Below is the input of compartments , values in each box is JetPack Thresholds

image.png
image.png
 

The Thesis is hidden in compartment 5

Output:
2
Reasoning:
Path:
1->3>4->-5  : number of hops 3
1->2->5       :number of hops 2
