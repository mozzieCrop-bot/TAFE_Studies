const shout = require('./Shout.js');

test('shout - 1', function() {

  let e = 'HELLO';
  let r = shout('hello');

  expect(r).toBe(e);
  
});


test('shout - 2', function() {

  let e = "STAY CALM AND KEEP PROGRAMMING";
  let r = shout('stay calm and keep programming');

  expect(r).toBe(e);
  
});

test('shout - 3', function() {

  let e = 'GOOD EVENING';
  let r = shout('good evening');

  expect(r).toBe(e);
  
});
