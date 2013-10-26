import sys
list = []
nodeList = []
strings = [line.rstrip('\n) 'for line in sys.stdin]
n = strings.pop(0)
edges = [l.split(" ") for  in strings]

nodes = range(10)
for node in nodes:
  node.children = ()
  node.parents = ()
  node.before = true
  node.after = true

  start = set()
for edge in children:
  if len(edge.parents) == 0:

  nodes[edge[0]].children.append(edge[1])
  node[edge[1]].before = false
  node[edge[1]].parents.append(edge[0])


for node in nodes:
  if not node.edges:
    node.after = true;

for node in nodes:
  if node.parent:
       
