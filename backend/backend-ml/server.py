from flask import Flask, send_from_directory
from flask_restful import Api, Resource, reqparse
import numpy as np
import pandas as pd
import tensorflow as tf
from tensorflow import keras
from keras import layers
import random
import os
from csv_read import featurized_movies, dict

"""
Attributes
----------
input_shape : tuple
    Denotes shape of each user input data
num_classes : int
    Number of Classes
classes : list
    List of Classes

"""
input_shape = (150, 44)
num_classes = 5
classes = ["The Explorer", "The Lovebird", "The Binge Watcher", "The Otaku", "The Old-School Watcher"]

def search_csv(str):
    """Helper function to search csv file

    Parameters
    ----------
    str : string
        string to search

    Returns
    -------
    list
        a list of int that denotes the feature of movie with title str

    """

    keys = dict.keys()
    if str in keys: 
        print(str)
        return dict[str]
    new_str = str.split(":")
    if new_str[0] in keys:
        print(new_str[0])
        return dict[new_str[0]]
    return []

def gen_data(arr):
    """Generate data for model prediction from 2D numpy array converted from dataframe

    Parameters
    ----------
    arr : numpy array
        Array that contains csv data to predict

    Returns
    -------
    numpy array
        Array of shape (150,44) that can be fit into model for prediction

    """

    ret = []
    for sub_arr in arr:
        new_arr = search_csv(sub_arr[0])
        if search_csv(sub_arr[0]) != []:
            ret.append(new_arr)
    if len(ret) > 150:
        ret = random.sample(ret, 150)
    elif len(ret) == 0:
        return np.array([])
    elif len(ret) < 150:
        dup = random.choices(ret, k=150 - len(ret))
        ret.extend(dup)
    return np.array(ret)

def create_model():
    """Create model skeleton

    Returns
    -------
    tensorflow.keras.Sequential model
        1D Convolutional Neural Network that is constructed for our task

    """
    model = keras.Sequential([
    layers.Conv1D(filters=32, kernel_size=3, activation='relu', input_shape=input_shape),
    layers.MaxPooling1D(pool_size=2),
    layers.Flatten(),
    layers.Dense(64, activation='relu'),
    layers.Dense(num_classes, activation='softmax')])
    model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['accuracy'])
    return model

"""Construct model skeleton and load pretrained weights"""
model = create_model()
model.load_weights("./weights/")
print(model.summary())

"""Create Flask app"""
app = Flask(__name__)

"""Declare flask route and specify how flask backend will retrieve data"""
@app.route('/filepath/', defaults={'path' : 'data/viewhist2.csv'})
#@app.route('/filepath/', defaults={'path' : 'data/viewhist.csv'})
@app.route('/filepath/<path>')
def prediction(path):
    """Prediction function that is called whenever java backend calls for personality

    Parameters
    ----------
    path : string
        path of csv data, deletd after model prediction

    Returns
    -------
    prediction : string
        rpredicted class result

    """
    print(path)
    csv = pd.read_csv(path).to_numpy()
    inputs = gen_data(csv)
    if inputs.size == 0:
        return '\" \"'
    inputs = inputs.astype('int32').reshape((1,150,44))
    prediction_batch = model.predict(inputs)
    prediction_id = np.argmax(prediction_batch, axis=-1)
    prediction = classes[prediction_id[0]]
    os.remove(path)
    return '\"'+prediction+'\"'

"""Main driver function"""
if __name__ == '__main__':
    app.run()