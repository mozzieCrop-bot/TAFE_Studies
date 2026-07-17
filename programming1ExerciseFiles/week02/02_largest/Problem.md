# Largest

You are tasked with implementing a function that will accept two values as strings.

The function `getLargest` has the following signature:

```js
function getLargest(a, b)
```

You will need to convert the values to a more appropriate data type that you can then compare against each other. You want to find out what value is larger, if A is > B or if B is > A or if A == B.



Example 1:

```js
let result = getLargest("10", "20");
//result = "20 is the largest"
```

Example 2:

```js
let result = getLargest("35", "15");
//result = "35 is the largest"
```

Example 1:

```js

let result = getLargest("15", "15");
//result = "15 is the largest but both are the same"
```

## How to Test

You can test your program by running `npm test` against a suite
of test cases. If you run into an issue, please make sure you install the required dependencies with `npm install`.
