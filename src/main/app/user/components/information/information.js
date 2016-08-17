var module = require('main_module');

function Controller(UserService) {

 var vm = this;
   vm.user = {};
   vm.updateUserInformation = updateUserInformation;

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
        console.log(vm.user);
        UserService.updateUserInformation(vm.user).then(
            function (){
                console.log("Successful");
                vm.message = true;
                console.log(vm.message);
            },
            function (err){
                console.log('Error', err);
                 vm.error = true;
            });
     };
}
Controller.$inject = ['UserService'];
require('./information.css');
module.component('customerInformation', {
    controller: Controller,
    templateUrl: require('./information.html')
});