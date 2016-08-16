var module = require('main_module');

function Controller($state,$stateParams,OrderService) {


    var vm =this;
    vm.orders = [];
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

    function acceptOrder(index){
        OrderService.acceptOrder(vm.orders[index].id).then(
            function(response){
                getAllOrders();
            },
            function(err){
                console.log('Error',err);
            }
        );
    }

    function declineOrder(index){
        OrderService.declineOrder(vm.orders[index].id).then(
            function(response){
                console.log("Order declined");
                getAllOrders();
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
