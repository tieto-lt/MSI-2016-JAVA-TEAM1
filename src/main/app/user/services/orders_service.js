var module = require('main_module');

function Service ($http) {

    this.getAllOrders = function() {
       return $http.get("/api/customer/orders",{});
    };

    this.getOrderResults = function (id){
            return $http.get('/api/customer/orders/'+ id,{});
        }

}
Service.$inject = ['$http'];
module.service('OrdersService', Service);
