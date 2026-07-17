
const inRange = require('./interval.js');


function procTest({
  start,
  candidate,
  end,
  expected 
}) {
  test(`test inRange(${candidate}, ${start}, ${end}) = ${expected}`, function() {
    const res = inRange(candidate, start, end);
    expect(res).toBe(expected);
  })
}

procTest({
  start: 1,
  candidate: 5,
  end: 7,
  expected: 0
});

procTest({
  start: -5,
  candidate: 6,
  end: 10,
  expected: 0
});

procTest({
  start: 5,
  candidate: -10,
  end: 8,
  expected: -1
});

procTest({
  start: 10,
  candidate: 54,
  end: 50,
  expected: 1
});

procTest({
  start: 5,
  candidate: 5,
  end: 8,
  expected: 0
});

procTest({
  start: 1,
  candidate: 8,
  end: 7,
  expected: 1
});

procTest({
  start: 7,
  candidate: 7,
  end: 7,
  expected: 0
});

procTest({
  start: 7,
  candidate: 5,
  end: 1,
  expected: 0
});

procTest({
  start: 100,
  candidate: 10,
  end: 7,
  expected: 0
});

procTest({
  start: 19,
  candidate: 2,
  end: 7,
  expected: -1
});
