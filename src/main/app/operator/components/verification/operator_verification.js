var module = require('main_module');

function Controller($state,VerificationService) {

    var vm = this;
    vm.user = {};
    vm.token = undefined;
    vm.status = 'NOT VERIFIED';
    vm.generateToken = generateToken;
    vm.getStatus = getStatus;
    vm.message = undefined;
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
                if(response.data.operatorStatus == "TOKENISSUE"){
                vm.message = "Do not forget to execute test mission to be completely verified!"
                vm.status = "TOKEN ISSUE";
                }
                if(response.data.operatorStatus == "NOTVERIFIED"){
                vm.status = "NOT VERIFIED";
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
