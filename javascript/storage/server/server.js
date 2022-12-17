const http = require('http');

const hostname = 'server.localhost.com';
const port = 3000;

const server = http.createServer((req, res) => {
    switch(req.method) {
        case 'GET':
            if (req.url.endsWith('/server')) {
                res.statusCode = 200;
                res.setHeader('Content-Type', 'text/plain');
                // res.setHeader('Access-Control-Allow-Methods', 'GET');
                // res.setHeader('Access-Control-Allow-Headers', 'true');
                res.setHeader('Access-Control-Allow-Origin', 'http://localhost.com:5500');
                // res.setHeader('Access-Control-Allow-Credentials', 'true');
                // res.setHeader('Access-Control-Max-Age', '86400');
                res.setHeader('Set-Cookie', 'where=server;Domain=localhost.com;expires='+ getUtcTimeInSecondsFromNow(60));
                // HttpOnly=true
                // Domain=localhost.com
                res.end('Cookie world with the cookie set');
            } else {
                res.statusCode = 200;
                res.setHeader('Content-Type', 'text/plain');
                res.end('Cookie normal world');
            }
            
            break;
        default:
            console.log('Unhandled input method type')
            break;
    }

});


function getUtcTimeInSecondsFromNow(seconds) {
    var addedUTCTime  = (new Date(Date.now()+ seconds *1000)).toUTCString();
    return addedUTCTime;
}


server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}`);
});