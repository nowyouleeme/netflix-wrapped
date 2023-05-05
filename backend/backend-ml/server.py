from flask import Flask, send_from_directory
from flask_restful import Api, Resource, reqparse
import numpy as np
import pandas as pd
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers
import random
from csv_read import featurized_movies, dict

input_shape = (150, 44)
num_classes = 5
classes = ["Explorer", "Lovebird", "Binge Watcher", "Otaku", "Old School"]

def search_csv(str):
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
    ret = []
    for sub_arr in arr:
        new_arr = search_csv(sub_arr[0])
        if search_csv(sub_arr[0]) != []:
            ret.append(new_arr)
    if len(ret) > 150:
        ret = random.sample(ret, 150)
    elif len(ret) < 150:
        dup = random.choices(ret, k=150 - len(ret))
        ret.extend(dup)
    return np.array(ret)

def create_model():
    model = keras.Sequential([
    layers.Conv1D(filters=32, kernel_size=3, activation='relu', input_shape=input_shape),
    layers.MaxPooling1D(pool_size=2),
    layers.Flatten(),
    layers.Dense(64, activation='relu'),
    layers.Dense(num_classes, activation='softmax')])
    model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', metrics=['accuracy'])
    return model

model = create_model()
model.load_weights("./weights/")
print(model.summary())

app = Flask(__name__)

@app.route('/filepath/', defaults={'path' : 'data/viewhist2.csv'})
#@app.route('/filepath/', defaults={'path' : 'data/viewhist.csv'})
@app.route('/filepath/<path>')
def prediction(path):
    print(path)
    csv = pd.read_csv(path).to_numpy()
    inputs = gen_data(csv)
    inputs = inputs.astype('int32').reshape((1,150,44))
    prediction_batch = model.predict(inputs)
    prediction_id = np.argmax(prediction_batch, axis=-1)
    prediction = classes[prediction_id[0]]
    return '\"'+prediction+'\"'

# main driver function
if __name__ == '__main__':
    app.run()