
# In Range

Number lines are a common concept in mathematics, they can expressed visually but also as an interval. You have been tasked to write a function that will check to see if a number exists within the interval or exceeds a particular endpoint.

You will need to implement the following function:

```js
function inRange(toCheck, start, end)
  
```

* `inRange`, is the number to check if it exists within start and end

* `start`, is typically considered the smallest number out of the two but if it is considered larger than `end`, it is considered the end point

* `end`, is typically considered the larger number out of the two, but if it is considered smaller than `start`, it is considered the start point.

The function will need to return a value that correspond to the following conditions:

* `0` - Indicates the number exists within the interval

* `1` - Indicates the number exists outside the interval and is larger than `end`, this is also assuming `end` may have been updated prior.

* `-1` - Indicates the number exists outside the interval and is smaller than `start`, this is also assuming `start` may have been updated prior.

## How to test

You can test your solution against the test cases by using `npm test`, make sure you install the required dependencies using `npm install`.
