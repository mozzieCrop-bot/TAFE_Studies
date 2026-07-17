
const isEven = require('./IsEven.js') 

function procTest({
  input,
  expected
}) {
  const genVal = input;
  
  test(`isEven test - ${genVal}`, function() {
    const result = isEven(input);
    expect(result).toEqual(expected);
  }) 
}


procTest({
  input: 2,
  expected: true
})

procTest({
  input: 1,
  expected: false
})

procTest({
  input: 0,
  expected: true
})

procTest({
  input: 41,
  expected: false
})

procTest({
  input: 82,
  expected: true
})

procTest({
  input: -23,
  expected: false
})

procTest({
  input: -1022,
  expected: true
})
