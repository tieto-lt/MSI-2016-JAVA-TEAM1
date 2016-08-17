var module = require('main_module');

function Service ($http) {

    this.getAllOrders = function() {
       return $http.get("/api/customer/orders",{});
    };
}
Service.$inject = ['$http'];
module.service('OrdersService', Service);
