var module = require('main_module');

function Controller(UserService) {

 var vm = this;
   vm.user = {};
   vm.updateUserInformation = updateUserInformation;
   vm.doPasswordNotMatch = doPasswordNotMatch;
   vm.password = undefined;

   vm.$onInit = function() {
           _loadOrderDetails();
   };

     function _loadOrderDetails() {
         UserService.get().then(
             function (response) {
                 vm.user = response.data;
             },
             function (err) {
                 console.log('Error', err);
             });
     };

     function updateUserInformation(){
        vm.user.password = vm.password;
        UserService.updateUserInformation(vm.user).then(
            function (){
                vm.message = true;
            },
            function (err){
                console.log('Error', err);
                vm.error = true;
            });
     };

      function doPasswordNotMatch() {
            return vm.password != vm.passwordRepeat;
     }
}
Controller.$inject = ['UserService'];
require('./information.css');
module.component('customerInformation', {
    controller: Controller,
    templateUrl: require('./information.html')
});

require('./password.css');
module.component('customerPassword', {
    controller: Controller,
    templateUrl: require('./password.html')
});