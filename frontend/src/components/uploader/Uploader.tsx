import {useState} from 'react'
import "./Uploader.css"
import Papa from "papaparse";



function Uploader() {
    const [file, setFile] = useState<File>();

    const fileReader = new FileReader();

    const handleOnSubmit = () => {
        
      };


    return (
        <div className= "uploader">
            <form action= "">
                <input type = "file" accept=".csv" className = "input-field"
                onChange = {({target: {files}}) => { //set the file to new one
                    if (files) {
                        setFile(files[0])
                    }                    
                }}
                />
            </form>

            
            <button onClick={() => { //when user submits, 
                if (file) {
                    console.log("hello hello file file")
                    Papa.parse(file, { //parse the file
                        complete: function(results) { //upon parsing completion
                            //send to backend
                            const url = 
                                "http://localhost:6969/saveData?usercsv=" +
                                results.data.toString()
                            fetch(url)
                                .then((response) => response.json())
                                .then((responseJSON) => {
                                    if (responseJSON.result === "success") {
                                        //TODO: maybe some 'success' screen
                                        console.log("successfully sent to backend") // it's working
                                    } else {; 
                                        //TODO: maybe some 'fail' screen
                                    }
                                })
                                //FIXME: get information back from backend algorithm
                                .then(() => {
                                    fetch("http://localhost:6969/wrapped")
                                        .then()
                                }
                                    
                                )
                            //TODO: display their wrapped
                          console.log("Finished:", results.data);
                        }}
                      )
                }
            }}>
                IMPORT CSV
            </button>
        </div>
    )
}

export default Uploader