import process from "node:process";

let valueX = Number.parseInt(process.argv[2]);
let counter = 1;

while (counter != 13){
    let response = valueX*counter;
    console.log(valueX + "x" + counter + ": " + response);
    counter++;
}