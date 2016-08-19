var module = require('main_module');

function Controller($scope, OrdersService) {

    var vm = this;
    vm.orders = {};
    vm.information = {};
    vm.result = {};
    vm.message = false;
    $scope.oneAtATime = true;
    vm.getResults= getResults;
    /*order_id;*/

    /*vm.drop = drop;*/


    vm.myInterval = 3000;
    vm.noWrapSlides = false;
    vm.activeSlide = 0;

    vm.$onInit = function() {
            _loadList();

        };

    function _loadList() {
        console.log("labas")
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



    function getResults (customerId) {
        OrdersService.getOrderResults(customerId).then(
            function(response){
            vm.result=response.data[0];
            console.log(vm.result);
                if(vm.result == null){
                    console.log("null");
                }
            }
        )

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