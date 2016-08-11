var module = require('main_module');

function Service ($http) {

    this.getStatus = function() {
        return $http.get('/api/operator',{});
    }

    this.generateToken = function() {
        return $http.post('/api/token', {});
    };

}
Service.$inject = ['$http'];
module.service('VerificationService', Service);
