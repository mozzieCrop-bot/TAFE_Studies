import process from "node:process";

let base = Number(process.argv[2]);
let height = Number(process.argv[3]);
let area = Number.parseFloat((base*height)/2).toFixed(2);

console.log(area);