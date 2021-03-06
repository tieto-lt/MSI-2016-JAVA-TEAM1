var module = require('main_module');

function Controller(UserService, $state) {
$.material.input()
 var vm = this;
   vm.user = {};
   vm.updateUserInformation = updateUserInformation;
   vm.doPasswordNotMatch = doPasswordNotMatch;
   vm.password = undefined;
   vm.changePassword = changePassword;
   vm.passwords = {};
   vm.getCurrentState = getCurrentState;

   vm.$onInit = function() {
           _loadOrderDetails();
   };

     function _loadOrderDetails() {

         UserService.get().then(
             function (response) {
                 vm.user = response.data;
                 vm.passwords.oldPassword = response.data.password;
             },
             function (err) {
                 console.log('Error', err);
             });
     };

     function updateUserInformation(){

        UserService.updateUserInformation(vm.user).then(
            function (){
                vm.message = true;
            },
            function (err){
                console.log('Error', err);
                vm.error = true;
            });
     };

     function changePassword(){
            console.log(vm.passwords.enteredPassword);
            console.log(vm.passwords.OldPassword);
            console.log(vm.passwords.newPassword);
        UserService.changePassword(vm.passwords).then(
            function(){
                console.log("Success");
                vm.msg = true;
                vm.err = false;
            },
            function (err){
                vm.err = true;
                vm.msg = false;
            });
     }

      function doPasswordNotMatch() {
            return vm.passwords.newPassword != vm.passwordRepeat;
     }

      function getCurrentState() {
            return $state.current.name;
       }

}
Controller.$inject = ['UserService', '$state'];
require('./information.scss');
module.component('customerInformation', {
    controller: Controller,
    templateUrl: require('./information.html')
});

require('./password.scss');
module.component('customerPassword', {
    controller: Controller,
    templateUrl: require('./password.html')
});
