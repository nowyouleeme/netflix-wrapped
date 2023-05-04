import csv from 'csvtojson';

/**
 * asynchronous function used to convert the csv file into a json string that 
 * represents the data in the csv
 * @param file the file we're converting
 * @returns a string that represents the data in the csv
 */
export async function convertCSV(file: File): Promise<String> {
    let jsonString: String = "";

    //turn the file into a 2d array
    const reader = new FileReader();
    
    reader.readAsText(file);

    //pasted 
    await new Promise((resolve, reject) => {
        reader.onload = () => {
          const csvData: string = reader.result as string;
          csv()
            .fromString(csvData)
            .then((jsonObj: any[]) => {
                let json2DArray: String[][] = [];
                jsonObj.forEach((obj: any) => {
                    const row: any[] = [];
                    Object.keys(obj).forEach((key: string) => {
                    row.push(obj[key]);
                    });
                    json2DArray.push(row);
                });
              console.log(json2DArray);
              //turning the 2D array into json string
              let json: { [key: string] : String[][]} = {"usercsv": json2DArray};
              let stringjson = JSON.stringify(json);
              console.log("here is stringify "+ stringjson)
              jsonString = stringjson
              resolve(stringjson);
            })
        };
      });

    console.log("jsonstring right before returning: " + jsonString)
    return jsonString;

}

