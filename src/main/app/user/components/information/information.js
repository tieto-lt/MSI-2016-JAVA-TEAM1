var module = require('main_module');

function Controller(UserService, $state) {

 var vm = this;
   vm.user = {};
   vm.updateUserInformation = updateUserInformation;
   vm.doPasswordNotMatch = doPasswordNotMatch;
   vm.password = undefined;
   vm.getCurrentState = getCurrentState;


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

        UserService.updateUserInformation(vm.user).then(
            function (){
                vm.message = true;
                console.log(vm.user.password);
            },
            function (err){
                console.log('Error', err);
                vm.error = true;
            });
     };

      function doPasswordNotMatch() {
            return vm.password != vm.passwordRepeat;
     };

      function getCurrentState() {
            console.log($state.current.name);
            return $state.current.name;
       }

}
Controller.$inject = ['UserService', '$state'];
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