var module = require('main_module');

function Controller($rootScope, $state, $interval, Session, AuthService, VerificationService,UserService) {

    var vm = this;

    vm.isLogoutVisible = isLogoutVisible;
    vm.balance = undefined;
    vm.isCustomer = isCustomer;
    vm.isOperator = isOperator;
    vm.isAdmin = isAdmin;
    vm.isLoggedIn = isLoggedIn;
    vm.isLogoutVisible = isLogoutVisible;
    vm.logout = logout;
    //vm.whatUsername = whatUsername;
    vm.getCurrentState = getCurrentState;
    vm.getBalance = getBalance;

    vm.isOperatorVerified = false;


    vm.$onInit = function() {
       vm.getBalance();
       whatUsername();
       checkOperator();
       $interval(checkOperator, 5000);
       $interval(vm.getBalance, 5000);
       $rootScope.$on('userLoggedIn', function() {
            whatUsername();
            checkOperator();
            vm.getBalance();
       });
       $rootScope.$on('orderWasPlaced', function(order) {
            vm.getBalance();
       });
    };

    function isLogoutVisible() {
        return Session.isSessionActive();
    }

    function isCustomer() {
        var role = Session.getRole();
        role = role && role[0];
        return "ROLE_CUSTOMER" == role;
    }

    function isAdmin() {
          var role = Session.getRole();
          role = role && role[0];
          return "ROLE_ADMIN" == role;
     }
    function isOperator() {
          var role = Session.getRole();
          role = role && role[0];
          return "ROLE_OPERATOR" == role
     }

    function whatUsername() {
          vm.whatUsername = Session.getUsername();
           console.log(vm.whatUsername);
           return vm.whatUsername;
    }

    function isLoggedIn() {
         return Session.isSessionActive();
    }

    function isLogoutVisible() {
            return Session.isSessionActive();
        }

    function logout() {
            AuthService.logout();
            $state.go('root.login');
    }

    function getCurrentState() {
            return $state.current.name;
    }

    function checkOperator() {
        if (isLoggedIn() && isOperator()) {
            VerificationService.getStatus().then(
                function(response){
                    vm.isOperatorVerified = response.data == "VERIFIED";
                },
                function(err){
                     console.log('Error',err);
                }
            )
        }
    }

    function getBalance(){
        if (isLoggedIn() && isCustomer()) {
            UserService.get().then(
                function(response){
                   // console.log(response.data.id);
                    UserService.getBalance().then(
                        function(response){
                            vm.balance = response.data;
                        },
                        function(err){
                            console.log("getBalance() err");
                        }
                    );
                },
                function(err){
                    console.log(" getBalance() error");
                }
            );
        }
    }
 }

Controller.$inject = ['$rootScope', '$state', '$interval', 'Session', 'AuthService', 'VerificationService','UserService'];

var templateUrl = require('./main.html');
require('main.scss');
module.component('main', {
    controller: Controller,
    templateUrl: templateUrl
});
