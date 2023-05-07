# Netflix Wrapped

## Project Details
**Contributors:** klee161, khu21, jcha17, dhan26
**Repository:** https://github.com/cs0320-s2023/netflix-wrapped
**Total time:** 

## Design Choices
**Our project is divided up into 2 main directories: `frontend` and `backend`.**


### `frontend`
The `frontend` directory contains the **UI** and **sending API requests** to the backend. Within `frontend`, we have subdirectories `public` and `src`.
- `public` contains the HTML file to be loaded upon starting the application in the browser
- `src` contains our React components; frontend tests; mock data; and utilities for accessing the MapBox API and the redlining overlay data by sending requests to the backend. 

_Here are the most important pieces of our `frontend` and how they function/connect:_

- `components` is a package that contains the components of our App
  - `Display` is a component that displays the maps and its components.
  - `DisplayConfirm` is a component that displays confirmation as to if annotations have been saved or not
  - `InputPrompt` is component that prompts the user to make and submit an annotation for a given point on the redlining overlay data.
  - `PopUp` is a component that displays the information from clicking on a point of the redlining data and an option for the user to make an annotation.
  - `SaveAnnotations` is a component that allows a user to save the annotations made on the redlining overlay data.
  - `SideBar`is a component that displays the center latitude and longitude data, as well as the current zoom level.
- `mockData` is a package that contains mock geojson data that we use for testing and doing work on the frontend in isolation from the backend
  - `fullDownload.json` is a json file that contains the full redlining geojson
- `overlays` is a package for the overlays placed on the map
  - `overlays.tsx` contains code describing and styling a geo layer formed from the redlining data 
- `private` is a package for private information 
  - `key.tsx` contains the private token needed for MapBox access
- `tests` is a package with our frontend tests (more on this package below)
- `utilities` is a package with code that calls the backend API
  - `RequestHandler` contains code that calls the backend API to retrieve the data used to construct the layer of redlining geojson information.

### `backend`

## Tests
### Here is a summary of our backend tests
### Here is an overview of our frontend tests

## Bugs
### `frontend`
### `backend`

## How to Run
### Build & Run Program
1. Clone our repository at [https://github.com/cs0320-s2023/netflix-wrapped].
2. Open the repository in two separate code environments.
    * within **VSCode**, enter the `frontend` directory using the command `cd frontend`. 
    * within another window of **VSCode** or in **IntelliJ**, enter the `backend` directory using the command `cd backend`.
3. In VSCode, run npm install. Once all the necessary packages or installed, run `npm start` to load the Netflix Wrapped website in your default browser.
4.1. In IntelliJ,...
4.2 In VSCode, install the 'Maven for Java' and 'Extension Pack for Java' extensions. Once the extensions are installed, Click Run Java for the file 'Server.java'.
5. Install Anaconda for Python following the steps in the website. Then, run 'conda create -n [environment name]' to create a virtual environment for machine learning.
6. Activate the virtual environment by the command conda activate [environment name]. Install the following packages: tensorflow (conda install tensorflow), numpy (pip install numpy), pandas (pip install pandas), flask (pip install flask), and flask-restful (pip install flask-restful).
7. Enter the `backend-ml` directory using the command `cd backend-ml`. Then run command 'flask --app server run' to run the NVTI classifier embedded in the python flask server.

### Run Tests
To run tests on the backend, enter the backend directory using `cd backend`. Then run `mvn test` in the terminal. To run
tests individually, go to the test class you'd like to run and click the play button on the left side of the code window.

To run tests on the frontend, enter the frontend directory using `cd frontend`. Then run `npm test` in the terminal. 

## Citations
