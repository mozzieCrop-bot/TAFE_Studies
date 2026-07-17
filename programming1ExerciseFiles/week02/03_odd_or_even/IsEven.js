
function isEven(value) {
  let valueX = Number(value) / 2;
  if (Number.isSafeInteger(valueX)){
    return true;
  }
  else return false;
}


module.exports = isEven;
