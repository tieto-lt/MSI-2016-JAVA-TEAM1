var module = require('main_module');

function Controller($scope,$stateParams,OrderService) {


    var vm =this;
    vm.orders = [];
    vm.sortBy = sortBy;
    vm.getAllOrders = getAllOrders;
    vm.acceptOrder = acceptOrder;
    vm.declineOrder = declineOrder;
    vm.sortType = 'submissionDate';
    vm.selected = undefined;

    vm.checkStatus = checkStatus;

    vm.sortReverse = true;
     vm.$onInit = function(){
        getAllOrders();
    }

      function sortBy(type){

             if(vm.sortReverse){
                vm.sortReverse = false;
             }
             else{
                 vm.sortReverse = true;
             }

             vm.sortType = type;
             console.log(vm.sortType);
             console.log(vm.sortReverse);
      }

    function getAllOrders(){
        OrderService.getAllOrders().then(
            function(response){
                vm.orders = response.data;
                vm.orders.forEach(function (order){
                    if("InProgress" == order.status){
                        order.status = "In progress";
                    }
                });
            },
            function(err){
                console.log('Error',err);
            }
        );
    }

    function acceptOrder(index){
        OrderService.acceptOrder(index).then(
            function(response){
                getAllOrders();
                console.log("Order accepted");
            },
            function(err){
                console.log('Error',err);
            }
        );
    }

    function declineOrder(index){
        OrderService.declineOrder(index).then(
            function(response){
                console.log("Order declined");
                getAllOrders();
            },
            function(err){
                console.log('Error',err);
            }
        );
    }

    function checkStatus(order){
        if(order.status == "Completed"){
            return true;
        }
        if(order.status =="Executed"){
            return true;
        }
        if(order.status == "Declined"){
            return true;
        }
        return false;
    }
}

Controller.$inject = ['$scope','$stateParams','OrderService'];

require('./order.css');
module.component('adminOrders', {
    controller: Controller,
    templateUrl: require('./order.html')
});
