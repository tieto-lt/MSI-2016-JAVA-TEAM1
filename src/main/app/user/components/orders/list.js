var module = require('main_module');

function Controller($scope, OrdersService) {

    var vm = this;
    vm.orders = {};
    vm.information = {};
    vm.results = {};
    vm.message = false;
    $scope.oneAtATime = true;

    vm.drop = drop;


    vm.myInterval = 3000;
    vm.noWrapSlides = false;
    vm.activeSlide = 0;

    vm.$onInit = function() {
            _loadList();

        };

    function _loadList() {
        OrdersService.getAllOrders().then(
            function (response) {
                vm.orders = response.data;
                console.log(vm.orders.length);
                console.log(vm.orders);
                if(vm.orders.length == 0){
                vm.message = true;
                console.log(vm.message);
                }

                $scope.status = false;
            },
            function (err) {
                console.log('Error',err);
            });
     }
     $scope.status = {
       isCustomHeaderOpen: false,
       isFirstOpen: true,
       isFirstDisabled: false
     };


     function drop(index){
       /* OrdersService*/



     /*toggleOpen(index);*/
     }
         $scope.test = function(text) {
           alert(text);
         }


}

Controller.$inject = ['$scope','OrdersService'];
require('./list.scss');
module.component('customerOrders', {
    controller: Controller,
    templateUrl: require('./list.html')
});