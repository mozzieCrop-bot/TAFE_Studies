import process from "node:process";

let arg1 = process.argv[2];
let arg2 = process.argv[3];

// Using Number() to convert strings to number types
// only used this as both integers and floating-point
// numbers would be realistic in this use and would be
// fine for this use.
let speed = Number(arg1);
let duration = Number(arg2);

// can see I use the + operator to combine the outputs for
// the console. Also used the * operator for simple maths
// in line.
console.log("Speed: " + arg1 + " km/h");
console.log("Duration: " + arg2 + " hours");
console.log("Travelled: " + (speed * duration) + " km");