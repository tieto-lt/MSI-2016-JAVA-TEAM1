var module = require('main_module');

function Controller($state,$stateParams,OrderService) {


    var vm =this;
    vm.orderId = $stateParams.id;
    vm.orders = [];
    vm.date = [];
    vm.getAllOrders = getAllOrders;
    vm.acceptOrder = acceptOrder;
    vm.declineOrder = declineOrder;

     vm.$onInit = function(){
        getAllOrders();
    }

    function getAllOrders(){
        OrderService.getAllOrders().then(
            function(response){
                vm.orders = response.data;

            },
            function(err){
                console.log('Error',err);
            }
        );
    }

    function acceptOrder(){
    //console.log(vm.orderId);
        OrderService.acceptOrder(vm.orderId).then(
            function(response){
                console.log("Order accepted");
            },
            function(err){
                console.log('Error',err);
            }
        );
    }

    function declineOrder(){
        OrderService.declineOrder(vm.orderId).then(
            function(response){
                console.log("Order declined");
            },
            function(err){
                console.log('Error',err);
            }
        );
    }
}

Controller.$inject = ['$state','$stateParams','OrderService'];

require('./order.css');
module.component('adminOrders', {
    controller: Controller,
    templateUrl: require('./order.html')
});
