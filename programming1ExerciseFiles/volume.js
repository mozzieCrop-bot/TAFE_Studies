import process from "node:process";

let arg1 = Number(process.argv[2]);

function findVolume(radius){
    return (4 / 3) * Math.PI * (radius ** 3);
}

console.log("The radius inputted of the sphere: " + arg1);
console.log("Volume is: " + findVolume(arg1).toFixed(5));