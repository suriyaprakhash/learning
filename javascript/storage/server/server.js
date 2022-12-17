const http = require('https');
const fs = require('fs');

const hostname = 'server.localhost.com';
const port = 3000;

var httpsOptions = {
    key: fs.readFileSync('C:\\Suriya\\ws\\learning\\javascript\\storage\\cert\\DomainKey.pem'),
    cert: fs.readFileSync('C:\\Suriya\\ws\\learning\\javascript\\storage\\cert\\DomainCert.pem')
};

const server = http.createServer(httpsOptions, (req, res) => {
    switch(req.method) {
        case 'GET':
            if (req.url.endsWith('/server')) {
                res.statusCode = 200;
                res.setHeader('Content-Type', 'text/plain');
                // res.setHeader('Access-Control-Allow-Methods', 'GET');
                res.setHeader('Access-Control-Allow-Headers', 'true');
                res.setHeader('Access-Control-Allow-Origin', 'http://clienthost.net:5500');
                res.setHeader('Access-Control-Allow-Credentials', 'true');
                res.setHeader('Set-Cookie', 'where=server;HttpOnly;Secure;SameSite=None;expires='+ getUtcTimeInSecondsFromNow(60));
                // res.setHeader('Access-Control-Max-Age', '86400');
                // Domain=localhost.com
                // SameSite=None
                // HttpOnly
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