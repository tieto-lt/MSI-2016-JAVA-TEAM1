var module = require('main_module');

function Controller($scope,$stateParams,OrderService) {


    var vm =this;
    vm.orders = [];
    vm.getAllOrders = getAllOrders;
    vm.acceptOrder = acceptOrder;
    vm.declineOrder = declineOrder;
    vm.allStatus = ["All","Pending", "Accepted"];
     vm.$onInit = function(){
        getAllOrders();
    }

      $scope.sortType     = undefined; // set the default sort type
      $scope.sortReverse  = false;  // set the default sort order

      // create the list of sushi rolls
      $scope.sushi = [
        { name: 'Cali Roll', fish: 'Crab', tastiness: 2 },
        { name: 'Philly', fish: 'Tuna', tastiness: 4 },
        { name: 'Tiger', fish: 'Eel', tastiness: 7 },
        { name: 'Rainbow', fish: 'Variety', tastiness: 6 }
      ];

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

Controller.$inject = ['$scope','$stateParams','OrderService'];

require('./order.css');
module.component('adminOrders', {
    controller: Controller,
    templateUrl: require('./order.html')
});
