import process from "node:process";

let start = Number.parseInt(process.argv[2]);
let end = Number.parseInt(process.argv[3]);
let step  = Number.parseInt(process.argv[4]);
let stopValue = start;

end += step;

while (stopValue != end){
    console.log(stopValue);
    stopValue += step;
}