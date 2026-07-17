/* In this task I decided to implement it as a command
line program similar to the programs I have been working
with up to this point */

import process from "node:process";

function checkHeight(height, threshold) {
    if (height >= threshold){
        return "Height passes the threshold"
    }
    else return "Height does not pass the threshold. Sorry.";
}

let valueX = Number.parseInt(process.argv[2]);
let valueY = Number.parseInt(process.argv[3]);

console.log(checkHeight(valueX, valueY));