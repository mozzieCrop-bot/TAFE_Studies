// Here I am importing the node:process
// module to allow me to retrieve command line arguments
import process from 'node:process';

// Here I am using console.log to write to the console
// my chosen string and the first argument written in the
// command line after the file name when executing this.
console.log("Hello " + process.argv[2]);