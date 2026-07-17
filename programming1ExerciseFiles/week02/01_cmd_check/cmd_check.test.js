

const cmdcheck = require('./cmd_check.js');


test('CommandCheck, OK!', function() {

  let res = cmdcheck();

  expect(res).toEqual("OK!")
})
