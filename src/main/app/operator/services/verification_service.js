var module = require('main_module');

function Service ($http) {


    this.getStatus = function() {
        return $http.get('/api/operator',{});
    }

}
Service.$inject = ['$http'];
module.service('VerificationService', Service);
