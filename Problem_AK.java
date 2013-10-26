import sys
list = []
nodeList = []
for line in sys.stdin:
    list.append(line.rstrip('\n'));

list.reverse()
list.pop()
list.reverse()

startList = list.copy()

listLength = 0
while(len(list)!=listLength):
    listLength = len(list)
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
                        list.remove(l)
                        firstNums.remove(f)
                        should_restart = True
                        
finalList = []

for sl in startList:
    count = 0
    n = (sl.split(" "))
    for l in list:
        if (l.split(" ")[0] == n[1]):
            if (finalList.count(sl)==0):
                finalList.append(sl)
should_restart = True
while should_restart:
    should_restart = False
    counter = 0
    for l in finalList:
        n = l.split(" ")[0]
        for sl in startList:
            if (sl.split(" ")[0] == n[0]):
                counter+=1
        if (counter>1):
            finalList.remove(l)
            should_restart = True

finalL = []
for l in finalList:
    if (finalL.count(l.split(" ")[0])==0):
        finalL.append(l.split(" ")[0])
for l in list:
    if (finalL.count(l.split(" ")[0])==0):
        finalL.append(l.split(" ")[0])
for l in finalL:
    print(l)
