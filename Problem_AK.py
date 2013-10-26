import sys
strings = [line.rstrip('\n) 'for line in sys.stdin]
n = strings.pop(0)
edges = [l.split(" ") for  in strings]

nodes = range(10)
for node in nodes:
  node.children = set()
  node.parents = set()

start = set(range(10))
end = set(range(10))

for edge in edges:
  start.remove(edge[0])
  end.remove(edge[1])
  edge[0].children.add(edge[1])

paths = list(set())
for node in start:
    
  if len(node.children) <= len(node.parents):
      node.after = true;

def getinters(node):
  inters = node.children.map(lambda x: getinters(x)
  return reduce(lambda x, y:x.intersection(y), inters)
   
       
