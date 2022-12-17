/* This file inits the page */

const storeSelectList =  [
    'Cookie',
    'Local store',
    'Session store',
    'Indexed DB' 
];


const createOption = (storeSelect) => {
    const option = document.createElement("option");
    option.setAttribute('value', storeSelect);

    const optionText = document.createTextNode(storeSelect);
    option.appendChild(optionText);
    return option;
}

storeSelectList.forEach((value, index, arr) => {
    // console.log( value + ' ' + index + ' ' +  arr );
    storeSelectElement.appendChild(createOption(value));
})



