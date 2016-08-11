var module = require('main_module');

function Controller( $scope, MissionService) {

    var vm = this;
    vm.missions = {};
    vm.information = {};
    vm.results = {};
    $scope.oneAtATime = true;

    vm.$onInit = function() {
            _loadList();

        };

    function _loadList() {
        MissionService.getAllMissionsResults().then(
            function (response) {
                vm.missions = response.data;
                console.log(vm.missions);
                $scope.status = false;
            },
            function (err) {
                console.log('Error',err);
            });
     }
     $scope.status = {
       isCustomHeaderOpen: false,
       isFirstOpen: true,
       isFirstDisabled: false
     };
}

Controller.$inject = ['$scope','MissionService'];
require('./list.css');
module.component('missionList', {
    controller: Controller,
    templateUrl: require('./list.html')
});
