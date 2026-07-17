 import process from 'node:process'

 // Formatting output and printing it
 function outputFilePath(filepath) {
    let output = 'The file that will be used is: ${filepath}'
    console.log(output);
 }


 let filepath = 'example.txt';

 if(process.argv.length >= 2) {
    filepath = process.argv[2];
 }

 outputFilePath(filepath);