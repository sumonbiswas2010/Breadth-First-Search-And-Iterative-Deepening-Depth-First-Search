# Breadth First Search And Iterative Deepening Depth-First Search
Problem: Solving the 15-puzzle with uninformed search algorithms (Breadth First Search and Iterative deepening depth-first)

 Initial state: (an easy case)
1		2	4
5	7	3	8
9	6	11	12
13	10	14	15
Goal state:
1	2	3	4
5	6	7	8
9	10	11	12
13	14	15	

Input should be given as follows where '0' represents the blank tile.
1 0 2 4 5 7 3 8 9 6 11 12 13 10 14 15

Output:
Your program should list the board configurations and steps taken by both search strategies.

Case1: If there is a solution/ BFS doesn't run out of memory

Example:
1 0 2 4 5 7 3 8 9 6 11 12 13 10 14 15
1 2 0 4 5 7 3 8 9 6 11 12 13 10 14 15
1 2 3 4 5 7 0 8 9 6 11 12 13 10 14 15
...
..
.. 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0

Case 2:
If BFS runs out of memory, just print “Can’t find solution- BFS ran out of memory"
