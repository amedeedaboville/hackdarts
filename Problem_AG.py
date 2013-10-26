import sys
#dir of R is 0
#dir of D is 1
def recur(destX, destY, direc, x, y, numOfTurns, maxNumOfTurns):
    if (numOfTurns<=maxNumOfTurns and x<=destX and y<=destY):
        if (numOfTurns == maxNumOfTurns and destX == x and destY == y): 
            return 1
        else:
            if (direc==0):
                return (recur(destX, destY, 0, x+1, y, numOfTurns, maxNumOfTurns)
                        + recur(destX, destY, 1, x, y+1, numOfTurns+1, maxNumOfTurns))
            else:
                return(recur(destX, destY, 1, x, y+1, numOfTurns, maxNumOfTurns)
                       + recur(destX, destY, 0, x+1, y, numOfTurns+1, maxNumOfTurns))
    else:
        return 0
        
list = []
for line in sys.stdin:
    list.append(line.rstrip('\n'));

for l in list:
    if (l.split(" ")[0] != "0" and l.split(" ")[1] != "0"):
        print(recur(int(l.split(" ")[0]), int(l.split(" ")[0]), 0, 2, 1, 0, int(l.split(" ")[1]))
              + recur(int(l.split(" ")[0]), int(l.split(" ")[0]), 1, 1, 2, 0, int(l.split(" ")[1])))
