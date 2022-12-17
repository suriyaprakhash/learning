function submitUsername() {
    const username = usernameElement.value;
    const selectedStore = storeSelectElement.options[storeSelectElement.selectedIndex].value;

    switch(selectedStore) {
        case 'Cookie':
            setCookie(username);
            break;
        default:
            console.error('Incorrect store selected - no handler');
            break;
    }

}

function readStoredValue() {
    let storedValue = '';
    const selectedStore = storeSelectElement.options[storeSelectElement.selectedIndex].value;
    switch(selectedStore) {
        case 'Cookie':
            storedValue = readCookie(username);
            break;
        default:
            console.error('Incorrect store selected - no handler');
            break;
    }

    displayStoredValueElement.innerText = storedValue;
}


function getUtcTimeInSecondsFromNow(seconds) {
    var addedUTCTime  = (new Date(Date.now()+ seconds *1000)).toUTCString();
    return addedUTCTime;
}
