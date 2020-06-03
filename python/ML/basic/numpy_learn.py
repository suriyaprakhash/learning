# -*- coding: utf-8 -*-
"""Numpy learn.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1I6t_56WDqs3zlxiTv2osbPMjPBjNm3st
"""

import numpy as np
import cv2
import matplotlib.pyplot as plt
#import matplotlib as mplot

a = np.random.randint(0,100,10)
img = cv2.imread('sample_data/download.jfif', 0)
rows = img.shape[0]
cols = img.shape[1]

print('%d rows & %d cols', rows, cols)

arr = np.array(img)
plt.imshow(cv2.cvtColor(arr, cv2.COLOR_BGR2RGB)) 
plt.savefig("sample_data/array")

count =0 
# actual logic
for i in range(rows):
  for j in range(cols):
    if img[i][j] != 255:
      img[i][j]  = 1
print(count)

arr = np.array(img)
plt.imshow(cv2.cvtColor(arr, cv2.COLOR_BGR2RGB)) 
plt.savefig("sample_data/array")


def checkRange():
  print('none')

from google.colab import drive
drive.mount('/content/drive')
