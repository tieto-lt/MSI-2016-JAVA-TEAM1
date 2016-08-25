var module = require('main_module');

function Controller(UserService) {
    var vm = this;
    vm.amount = undefined;
    vm.amount = undefined;
    vm.submitAmount = submitAmount;
    vm.getPayUrl = getPayUrl;

    function submitAmount() {
        console.log("Amount added "+vm.amount);
        vm.getPayUrl();
       /* window.open('http://paysera.com', '_blank');*/
    }

    function getPayUrl() {
        UserService.getPayUrl(vm.amount*100).then(
             function(response) {
                window.open(response.data.url, '_blank');

             },
             function (err) {
                vm.error = !vm.message;
             });


    }


}

Controller.$inject = ['UserService'];
require('deposit.scss');
module.component('customerDeposit', {
    controller: Controller,
    templateUrl: require('./deposit.html')
});