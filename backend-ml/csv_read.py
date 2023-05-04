import numpy as np
import pandas as pd

# load data
df = pd.read_csv("./data/netflix_titles.csv")
num = len(df.index)
df = df.drop(['show_id', 'director', 'cast', 'country', 'date_added', 'rating', 'duration', 'description'], axis=1)

arr = []
featurized_movies = []
dict = {}
Genres = {}
cnt = 0


# convert dataframe to array
for i in range(num):
   arr.append(df.loc[i].to_list())

# create dictionary to access features with title
for i in range(num):
    dict[arr[i][1]] = arr[i]

# delete title from features
for key in dict.keys():
    arr = dict[key]
    del arr[1]
    dict[key] = arr

# make dictionary of genres
for key in dict.keys():
    arr = dict[key]
    genre = arr[2]
    genres = genre.split(',')
    for genre in genres:
        genre = genre.strip()
        if genre not in Genres:
            Genres[genre] = cnt
            cnt += 1

# construct feature matrix and save to dictionary
# key: movie title, value: feature matrix
for key in dict.keys():
    new_arr = []
    arr = dict[key]
    
    # 1) TV Show / Movie
    if arr[0] == 'Movie':
        new_arr.append(0)

    elif arr[0] == 'TV Show':
        new_arr.append(1)
    else:
        print('fucked in 1')
        break

    # 2) Old / New
    year = int(arr[1])
    if year >= 2010:
        new_arr.append(0)
    elif year < 2010:
        new_arr.append(1)
    

    # 3) Genre Binary List
    to_append = [0] * 42
    genre = arr[2]
    genres = genre.split(',')
    for genre in genres:
        genre = genre.strip()
        #print(Genres[genre])
        to_append[Genres[genre]] = 1
    new_arr.extend(to_append)

    featurized_movies.append(new_arr)
    dict[key] = new_arr
print(featurized_movies[1])