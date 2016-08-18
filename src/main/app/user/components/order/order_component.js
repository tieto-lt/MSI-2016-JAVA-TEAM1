var module = require('main_module');

function Controller(UserService) {

  var vm = this;
  vm.order = {};
  vm.user = {};
  vm.createOrder = createOrder;
  vm.checkIfEqual = checkIfEqual;
  vm.showMessage = showMessage;

  vm.object = undefined;

  vm.all = [
    {selected: false, color: "black", name: "1 object"},
    {selected: false, color: "black", name: "2 object"},
  ];

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

   function showMessage(index){
       vm.all[index].selected = !vm.all[index].selected;
       if(vm.all[index].selected) {
            vm.all[index].color = 'red';
            vm.object  = vm.all[index].name;

       } else {
            vm.all[index].color = 'black';
            vm.object = undefined;
       }
   }
}
Controller.$inject = ['UserService'];
require('./order_component.scss');
module.component('orderComponent', {
    controller: Controller,
    templateUrl: require('./order_component.html')
});