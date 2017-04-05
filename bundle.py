import pickle
import numpy as np
import random
from scipy.misc import imread
import glob, os

size = 128
width = size * size * 3
data_chunk = 2000

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
data_1 = np.empty((0,width), int)
label_1 = []
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
data_1 = np.empty((0,width), int)
label_1 = []
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
	data_1 = np.vstack([data_1, arr])
	label_1.append(file[0])
	num += 1
	if num % 100 == 0: 
		print num
print data_1.shape
os.chdir(orig_cwd + "/apples-batches-python")
dictA = {"data": data_1, "labels": label_1}
fn = "data_batch_2"
with open(fn, "wb") as f:
	pickle.dump(dictA, f)

## Third file 
os.chdir(orig_cwd + "/Images")
data_1 = np.empty((0,width), int)
label_1 = []
for file in files_3:
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
fn = "data_batch_3"
with open(fn, "wb") as f:
	pickle.dump(dictA, f)

# Fourth file
os.chdir(orig_cwd + "/Images")
data_1 = np.empty((0,width), int)
label_1 = []
for file in files_4:
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
fn = "data_batch_4"
with open(fn, "wb") as f:
	pickle.dump(dictA, f)

# Fifth file
os.chdir(orig_cwd + "/Images")
data_1 = np.empty((0,width), int)
label_1 = []
for file in files_5:
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
fn = "data_batch_5"
with open(fn, "wb") as f:
	pickle.dump(dictA, f)

# Test Batch
os.chdir(orig_cwd + "/Images")
data_1 = np.empty((0,width), int)
label_1 = []
for file in files_6:
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
dictA = {"data": data_6, "labels": label_6}
fn = "test_batch"
with open(fn, "wb") as f:
	pickle.dump(dictA, f)


#print np.reshape(cpic, (3, 32*32), order ="C")


