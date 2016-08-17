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

    vm.names = ["moon", "iii", "kai"];

    vm.allStatus = ["All","Pending", "Accepted"];
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
}

Controller.$inject = ['$scope','$stateParams','OrderService'];

require('./order.css');
module.component('adminOrders', {
    controller: Controller,
    templateUrl: require('./order.html')
});
