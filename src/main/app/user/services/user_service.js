var module = require('main_module');

function Service ($http) {


    this.create = function(user) {
        return $http.post('/api/user', user);
    };

    this.get = function() {
        return $http.get('/api/user/current');
    };

    this.createOrder = function(order) {
        return $http.post('/api/order', order);
    };

    this.updateUserInformation = function(user){
        return $http.post('/api/user/information',user);
    };

    this.changePassword = function(password){
        return $http.post('/api/customer/password',password);
    };

    this.getBalance = function(id){
        return $http.get('/api/customer/balance/'+id);
    };

//    this.acceptPayment = function(id){
//        return $http.get('/api/customer/payment/accept/'+id);
//    };
//
//    this.rejectPayment = function (id){
//        return $http.get('/api/customer/payment/reject/'+id);
//    };
}

Service.$inject = ['$http'];
module.service('UserService', Service);