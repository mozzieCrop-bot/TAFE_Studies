

function getLargest(a, b) {
  if (Number(a) >= Number(b)){
    if (Number(a) != Number(b)){
      return a + " is the largest";
    }
    else return a + " is the largest but both are the same";
  }
  else return b + " is the largest";
}


module.exports = getLargest;
