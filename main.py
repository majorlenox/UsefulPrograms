import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
import numpy as np
import csv

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

plt.style.use('_mpl-gallery')

X = []
Y = []
Z = []

with open('output.txt', 'r') as datafile:
       plotting = csv.reader(datafile, delimiter=' ')

       for ROWS in plotting:
              X.append(int(ROWS[0]))
              Y.append(int(ROWS[1]))
              Z.append(int(ROWS[2]))

print(len(Z))
plt.plot(X, Y, Z, zdir='z', color='r', label='divide')

# make data
X = []
Y = []
Z = []
for x in range(1, 512):
       for y in range(1, 512):
              if x >= y:
                     X.append(x)
                     Y.append(y)
                     Z.append((np.log2(x)*np.log2(x-y+1))*10)
plt.plot(X, Y, Z, zdir='z', color='b', label='log')
plt.show()
