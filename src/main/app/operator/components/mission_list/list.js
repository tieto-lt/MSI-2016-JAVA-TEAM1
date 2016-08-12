var module = require('main_module');

function Controller($scope, MissionService) {

    var vm = this;
    vm.missions = {};
    vm.information = {};
    vm.results = {};
    vm.message = false;
    $scope.oneAtATime = true;


      vm.myInterval = 3000;
      vm.noWrapSlides = false;
      vm.activeSlide = 0;

    vm.$onInit = function() {
            _loadList();

        };

    function _loadList() {
        MissionService.getAllMissionsResults().then(
            function (response) {
                vm.missions = response.data;
                console.log(vm.missions.length);
                if(vm.missions.length == 0){
                vm.message = true;
                console.log(vm.message);
                }

                vm.missions.forEach(function (mission) {

                    mission.startNavigationData.x = mission.startNavigationData.x || 0;
                    mission.startNavigationData.y = mission.startNavigationData.y || 0;
                    mission.startNavigationData.z = mission.startNavigationData.z || 0;

                    mission.finishNavigationData.x = mission.finishNavigationData.x || 0;
                    mission.finishNavigationData.y = mission.finishNavigationData.y || 0;
                    mission.finishNavigationData.z = mission.finishNavigationData.z || 0;

                    mission.startNavigationData.x = Math.round( mission.startNavigationData.x*100)/100;
                    mission.startNavigationData.y = Math.round( mission.startNavigationData.y*100)/100;
                    mission.startNavigationData.z = Math.round( mission.startNavigationData.z*100)/100;
                    mission.finishNavigationData.x = Math.round( mission.finishNavigationData.x*100)/100;
                    mission.finishNavigationData.y = Math.round( mission.finishNavigationData.y*100)/100;
                    mission.finishNavigationData.z = Math.round( mission.startNavigationData.z*100)/100;
                });
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

