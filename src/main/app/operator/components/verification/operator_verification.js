var module = require('main_module');

function Controller($state,VerificationService) {

    var vm = this;
    vm.user = {};
    vm.token = undefined;
    vm.status = 'NOT VERIFIED';
    vm.generateToken = generateToken;
    vm.getStatus = getStatus;
    vm.message = undefined;


    vm.$onInit = function() {
       getStatus();
    };



    function generateToken(){
    console.log('Token controller!!');
        VerificationService.generateToken().then(
            function(response){
                vm.token = response.data.token;
            },
            function(err){
                console.log('Error',err);
            }
        )
    }

    function getStatus(){
         console.log('Status controller!!');
         VerificationService.getStatus().then(
            function(response){
                vm.status = response.data.operatorStatus;
                if(response.data == "NOTVERIFIED"){
                    vm.message = "Do not forget to execute test mission to be completely verified!"
                    vm.status = "NOT VERIFIED";
                } else if (response.data == "VERIFIED"){
                    vm.status = "VERIFIED";
                }
            },
            function(err){
                 console.log('Error',err);
            }
         )
    }
}
Controller.$inject = ['$state','VerificationService'];

require('./operator_verification.css');
module.component('operatorVerification', {
controller: Controller,
templateUrl: require('./operator_verification.html')
});
