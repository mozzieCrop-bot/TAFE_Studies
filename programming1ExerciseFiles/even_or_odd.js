import process from "node:process";

let arg1 = Number(process.argv[2]);

function isEven(value) {
  let valueX = Number(value) / 2;
  if (Number.isSafeInteger(valueX)){
    return "Even";
  }
  else return "Odd";
}

console.log(isEven(arg1));