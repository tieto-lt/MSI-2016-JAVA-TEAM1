var module = require('main_module');

function Service ($http) {


    this.getAllOrders = function(){
        return $http.get('/api/order',{});
    }

    this.acceptOrder = function(id){
        return $http.put('/api/order/accept/{id}', id);
    }

    this.declineOrder= function(id){
        return $http.put('/api/order/decline/{id}',id);
    }
}

Service.$inject = ['$http'];
module.service('OrderService', Service);