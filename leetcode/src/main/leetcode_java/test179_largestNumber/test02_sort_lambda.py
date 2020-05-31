import numpy as np
data = np.array([[2,2,5],[2,2,4],[1,1,3],[1,2,3],[2,3,6]])

idex=np.lexsort([data[:,2], -1*data[:,1], data[:,0]])
sorted_data = data[idex, :]

print ("="*40,'\n',sorted_data)

sorted_data = sorted(data,key=lambda x:x[1])
print ("="*40,'\n',sorted_data)

