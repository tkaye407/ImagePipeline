import pickle
import numpy as np
import random
from scipy.misc import imread
import glob, os

size = 128
width = size * size * 3
data_chunk = 1000

data_1 = np.empty((0,width), int)
data_2 = np.empty((0,width), int)
data_3 = np.empty((0,width), int)
data_4 = np.empty((0,width), int)
data_5 = np.empty((0,width), int)
data_6 = np.empty((0,width), int)
label_1 = []
label_2 = []
label_3 = []
label_4 = []
label_5 = []
label_6 = []
files = []
num = 0

orig_cwd = os.getcwd()
os.chdir(os.getcwd() + "/Images")
for file in glob.glob("*.jpg"):
	files.append(file)

random.shuffle(files)
files_1 = files[0:data_chunk]
files_2 = files[data_chunk:2*data_chunk]
files_3 = files[2*data_chunk:3*data_chunk]
files_4 = files[3*data_chunk:4*data_chunk]
files_5 = files[4*data_chunk:5*data_chunk]
files_6 = files[5*data_chunk:6*data_chunk]

## First file
os.chdir(orig_cwd + "/Images")
for file in files_1:
	cpic = imread(file)
	arr = np.empty((3,size*size), int)
	for i in range(0, size):
		for j in range(0, size):
			arr[0,i*size + j] = cpic[i, j, 0]
			arr[1,i*size + j] = cpic[i, j, 1]
			arr[2,i*size + j] = cpic[i, j, 2]
	arr = arr.flatten()
	data_1 = np.vstack([data_1, arr])
	label_1.append(file[0])
	num += 1
	if num % 100 == 0: 
		print num
print data_1.shape
os.chdir(orig_cwd + "/apples-batches-python")
dictA = {"data": data_1, "labels": label_1}
fn = "data_batch_1"
with open(fn, "wb") as f:
	pickle.dump(dictA, f)

#Second File
os.chdir(orig_cwd + "/Images")
for file in files_2:
	cpic = imread(file)
	arr = np.empty((3,size*size), int)
	for i in range(0, size):
		for j in range(0, size):
			arr[0,i*size + j] = cpic[i, j, 0]
			arr[1,i*size + j] = cpic[i, j, 1]
			arr[2,i*size + j] = cpic[i, j, 2]
	arr = arr.flatten()
	data_2 = np.vstack([data_2, arr])
	label_2.append(file[0])
	num += 1
	if num % 100 == 0: 
		print num
print data_2.shape
os.chdir(orig_cwd + "/apples-batches-python")
dictA = {"data": data_2, "labels": label_2}
fn = "data_batch_2"
with open(fn, "wb") as f:
	pickle.dump(dictA, f)

## Third file 
os.chdir(orig_cwd + "/Images")
for file in files_3:
	cpic = imread(file)
	arr = np.empty((3,size*size), int)
	for i in range(0, size):
		for j in range(0, size):
			arr[0,i*size + j] = cpic[i, j, 0]
			arr[1,i*size + j] = cpic[i, j, 1]
			arr[2,i*size + j] = cpic[i, j, 2]
	arr = arr.flatten()
	data_3 = np.vstack([data_3, arr])
	label_3.append(file[0])
	num += 1
	if num % 100 == 0: 
		print num
print data_3.shape
os.chdir(orig_cwd + "/apples-batches-python")
dictA = {"data": data_3, "labels": label_3}
fn = "data_batch_3"
with open(fn, "wb") as f:
	pickle.dump(dictA, f)

# Fourth file
os.chdir(orig_cwd + "/Images")
for file in files_4:
	cpic = imread(file)
	arr = np.empty((3,size*size), int)
	for i in range(0, size):
		for j in range(0, size):
			arr[0,i*size + j] = cpic[i, j, 0]
			arr[1,i*size + j] = cpic[i, j, 1]
			arr[2,i*size + j] = cpic[i, j, 2]
	arr = arr.flatten()
	data_4 = np.vstack([data_4, arr])
	label_4.append(file[0])
	num += 1
	if num % 100 == 0: 
		print num
print data_4.shape
os.chdir(orig_cwd + "/apples-batches-python")
dictA = {"data": data_4, "labels": label_4}
fn = "data_batch_4"
with open(fn, "wb") as f:
	pickle.dump(dictA, f)

# Fifth file
os.chdir(orig_cwd + "/Images")
for file in files_5:
	cpic = imread(file)
	arr = np.empty((3,size*size), int)
	for i in range(0, size):
		for j in range(0, size):
			arr[0,i*size + j] = cpic[i, j, 0]
			arr[1,i*size + j] = cpic[i, j, 1]
			arr[2,i*size + j] = cpic[i, j, 2]
	arr = arr.flatten()
	data_5 = np.vstack([data_5, arr])
	label_5.append(file[0])
	num += 1
	if num % 100 == 0: 
		print num
print data_5.shape
os.chdir(orig_cwd + "/apples-batches-python")
dictA = {"data": data_5, "labels": label_5}
fn = "data_batch_5"
with open(fn, "wb") as f:
	pickle.dump(dictA, f)

# Test Batch
os.chdir(orig_cwd + "/Images")
for file in files_6:
	cpic = imread(file)
	arr = np.empty((3,size*size), int)
	for i in range(0, size):
		for j in range(0, size):
			arr[0,i*size + j] = cpic[i, j, 0]
			arr[1,i*size + j] = cpic[i, j, 1]
			arr[2,i*size + j] = cpic[i, j, 2]
	arr = arr.flatten()
	data_6 = np.vstack([data_6, arr])
	label_6.append(file[0])
	num += 1
	if num % 100 == 0: 
		print num
print data_6.shape
os.chdir(orig_cwd + "/apples-batches-python")
dictA = {"data": data_6, "labels": label_6}
fn = "test_batch"
with open(fn, "wb") as f:
	pickle.dump(dictA, f)


#print np.reshape(cpic, (3, 32*32), order ="C")


