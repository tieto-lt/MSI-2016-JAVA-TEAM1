var module = require('main_module');

function Controller(UserService) {

  var vm = this;
  vm.order = {};
  vm.user = {};

  vm.order.name = undefined;
  vm.order.email = undefined;
  vm.order.phone = undefined;

  vm.$onInit = function() {
          _loadOrderDetails();
  };

    function _loadOrderDetails() {
        UserService.get().then(
            function (response) {
                vm.user = response.data;
                _setOrder();
            },
            function (err) {
                console.log('Error', err);
            });
    }

    function _setOrder() {
        vm.order.name = vm.user.name;
        vm.order.email = vm.user.email;
        vm.order.phone = vm.user.phone;
    }

}
Controller.$inject = ['UserService'];
require('./order_component.scss');
module.component('orderComponent', {
    controller: Controller,
    templateUrl: require('./order_component.html')
});