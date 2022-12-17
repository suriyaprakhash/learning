function setCookie(username) {
    console.log('Setting cookie '+ username);
    const expireInSeconds = 30;
    // let userNameCookie = 'username=' + username + ';expires='+ getUtcTimeInSecondsFromNow(expireInSeconds);
    let userNameCookie = 'username=' + username;
    //  + ';domain=localhost.com';
    // cookie = cookie + 'Path=/server';
    document.cookie = userNameCookie;
    // document.cookie = 'data=/server';
}

function readCookie() {
    return document.cookie;
}

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
}

function readStoredCookieValueFromServer() {

    const http = new XMLHttpRequest();
    const url='https://server.localhost.com:3000/server';
    http.open("GET", url);
    http.withCredentials = true;
    http.send();
    
    http.onreadystatechange = (e) => {
      console.log(http.responseText)
    }
}