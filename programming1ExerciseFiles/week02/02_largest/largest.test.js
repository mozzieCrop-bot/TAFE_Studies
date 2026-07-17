
const getLargest = require('./largest');

test('largest test 1', function() {
  let a = "10";
  let b = "20";
  let expected = "20 is the largest";

  let result = getLargest(a, b);

  expect(result).toEqual(expected);
})

test('largest test 2', function() {
  let a = "35";
  let b = "15";
  let expected = "35 is the largest";

  let result = getLargest(a, b);

  expect(result).toEqual(expected);
})

test('largest test 3', function() {
  let a = "15";
  let b = "15";
  let expected = "15 is the largest but both are the same";

  let result = getLargest(a, b);

  expect(result).toEqual(expected);
})

