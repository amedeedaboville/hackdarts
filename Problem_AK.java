import sys

class node:
    def __init__(self, index, weight):
        self.index = index
        self.weight = weight
    def increment(self, weight):
        self.weight += weight
    def getWeight(self):
        return self.weight

list = []
nodeList = []
for line in sys.stdin:
    list.append(line.rstrip('\n'));

list.reverse()
i = 0
maxI = int(list.pop())
while (i<maxI):
    newNode = node(i,1)
    nodeList.append(newNode)
    i+=1
    
list.reverse()


 
while(len(list)>1):
    firstNums = []
    secondNums = []
    for l in list:
        n = (l.split(" "))
        firstNums.append(n[0])
        secondNums.append(n[1])
    should_restart = True
    while should_restart:
        should_restart = False
        for f in firstNums:
            if (secondNums.count(f) == 0 and firstNums.count(f) == 1):
                for l in list:
                    if (l.split(" ")[0] == f):
                        nodeList[int(f)].increment(nodeList[int(l.split(" ")[1])].getWeight())
                        list.remove(l)
                        firstNums.remove(f)
                        should_restart = True
'''
firstNums.clear()
secondNums.clear()
should_restart = True
aDict = {}
while should_restart:
    should_restart = False
    for l in list:
        firstNums.append(l.split(" ")[0])
        aDict[l.split(" ")[0]] = 0
        secondNums.append(l.split(" ")[1])


for f in firstNums:
    aDict[f] += 1

sorted(aDict.items(), key=lambda x: x[1])
'''

