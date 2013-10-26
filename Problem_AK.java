import sys

list = []

for line in sys.stdin:
    list.append(line.rstrip('\n'));

list.reverse()
list.pop()
list.reverse()

newList = []
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
            firstNums.remove(f)
            should_restart = True

should_restart = True
while should_restart:
    should_restart = False
    for s in secondNums:
        if (firstNums.count(s) == 0 and secondNums.count(s) == 1):
            secondNums.remove(s)
            should_restart = True
        
should_restart = True
while should_restart:
    should_restart = False        
    for n in firstNums:
        if(firstNums.count(n) == secondNums.count(n)):
            firstNums.remove(n)
            secondNums.remove(n)
            should_restart = True

should_restart = True
while should_restart:
    should_restart = False        
    for n in secondNums:
        if(firstNums.count(n) == secondNums.count(n)):
            firstNums.remove(n)
            secondNums.remove(n)
            should_restart = True

maxOccurrence = 0
theStuff = 0
theStuffList = []
for n in firstNums:
    if (firstNums.count(n) == maxOccurrence):
        maxOccurrence = firstNums.count(n)
        if (theStuffList.count(n)==0):
            theStuffList.append(n)
    if (firstNums.count(n) > maxOccurrence):
        theStuffList.clear()
        maxOccurrence = firstNums.count(n)
        theStuffList.append(n)

for n in secondNums:
    if (secondNums.count(n) == maxOccurrence):
        maxOccurrence = secondNums.count(n)
        if (theStuffList.count(n)==0):
            theStuffList.append(n)
    if (secondNums.count(n) > maxOccurrence):
        theStuffList.clear()
        maxOccurrence = secondNums.count(n)
        theStuffList.append(n)

for nums in theStuffList:  
    print(nums)
