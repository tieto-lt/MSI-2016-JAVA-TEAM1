var module = require('main_module');

function Service ($http) {

    this.create = function(user) {
        console.log(user);
        return $http.post('/api/user', user);
    };

    this.get = function() {
        return $http.get('/api/user/current');
    };

    this.createOrder = function(order) {
        return $http.post('/api/order', order);
    }

}

Service.$inject = ['$http'];
module.service('UserService', Service);
