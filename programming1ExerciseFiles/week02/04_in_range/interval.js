

function inRange(toCheck, start, end) {
        
    if (start > end){
        if (toCheck >= end && toCheck <= start){
            return 0;
        }
        else if (toCheck >= start){
            return 1;
        } else return -1;
    }
    else if (toCheck < start || toCheck > end){
        if (toCheck < start){
            return -1;
        }else return 1;
    } else return 0;

}

module.exports = inRange
