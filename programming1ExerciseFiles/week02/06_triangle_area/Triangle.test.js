
const triangleArea = require('./Triangle.js')

test('Area Of Triangle - 1', function() {

  let result = triangleArea(3, 5);

  expect(result).toBe('7.50');
});

test('Area Of Triangle - 2', function() {

  let result = triangleArea(5.5, 2.5);

  expect(result).toBe('6.88');
});

test('Area Of Triangle - 3', function() {

  let result = triangleArea(10, 5);

  expect(result).toBe('25.00');
});
