var module = require('main_module');

function Controller(UserService, $state) {

  var vm = this;
  vm.order = {};
  vm.user = {};
  vm.createOrder = createOrder;
  vm.checkIfEqual = checkIfEqual;

  vm.$onInit = function() {
          _loadOrderDetails();
  };

    function _loadOrderDetails() {
        UserService.get().then(
            function (response) {
                vm.user = response.data;
                _setOrder();
                vm.previousOrder = {};
            },
            function (err) {
                console.log('Error', err);
            });
    }

    function _setOrder() {
        vm.order.email = vm.user.email;
        vm.order.phone = vm.user.phone;
        vm.order.fullName = vm.user.name;
    }

    function createOrder(){
          if(!checkIfEqual()){
             UserService.createOrder(vm.order).then(
                function(response) {
                   vm.message = !vm.error;
                   vm.previousOrder.fullName = vm.order.fullName;
                   vm.previousOrder.phone = vm.order.phone;
                   vm.previousOrder.email = vm.order.email;
                   vm.previousOrder.details = vm.order.details;
                   vm.previousOrder.missionName = vm.order.missionName;
                   console.log("I ma here");
                   $state.go('root.customerOrders');

                 },
                 function (err) {
                   vm.error = !vm.message;
                 });
         }
    }

    function checkIfEqual(){
        return ((vm.previousOrder.fullName == vm.order.fullName) &&
                (vm.previousOrder.phone == vm.order.phone) &&
                (vm.previousOrder.email == vm.order.email) &&
                (vm.previousOrder.details == vm.order.details) &&
                (vm.previousOrder.missionName == vm.order.missionName));
    }

}
Controller.$inject = ['UserService', '$state'];
require('./order_component.scss');
module.component('orderComponent', {
    controller: Controller,
    templateUrl: require('./order_component.html')
});