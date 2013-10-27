import re
class Node(object):
  def __str__(self):
    return ""+self.number
  
  def __init__(self, i):
    self.number = i
    self.children = set()

import sys
strings = [line.rstrip('\n') for line in sys.stdin]
n = int(strings.pop(0))
splits = [re.split(r"\s+", l) for l in strings]
edges = [(int(l[0]),int(l[1])) for l in splits if l]

nodes = {i: Node(i) for i in range(1, n+1)}
start = Node(-1)
for node in nodes.values():
  start.children.add(node) 
end = Node(-2)

for edge in edges:
  start.children = set(filter(lambda x: x.number != edge[1], start.children))
  nodes.get(edge[0]).children.add(nodes.get(edge[1])) # get what
for node in nodes.values():
  if len(node.children) == 0:
    node.children.add(end)
def getinters(node):
  if end in node.children:
    return [[node, end]]
  else:
    result = list(list())
    for child in node.children:
      paths = getinters(child) #paths is a list of Nodes or a list of set of nodes
      for path in paths:
        path.insert(0, node)
        result.append(path)
      if len(node.children) > 1:
        return [reduce(lambda x, y: set(x).intersection(y), result)]
    return result

inters = getinters(start)
    
bnecks = reduce(lambda x, y:set(x).intersection(y), inters)
bnecks.remove(start)
bnecks.remove(end)
if(len(bnecks) == 0): bnecks = start.children
for b in bnecks:
  print b.number
