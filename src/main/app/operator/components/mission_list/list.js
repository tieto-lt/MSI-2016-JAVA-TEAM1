var module = require('main_module');

function Controller($scope, MissionService) {

    var vm = this;
    vm.missionNames={};
    vm.missions = {};
    vm.information = {};
    vm.results = {};
    vm.message = false;
    $scope.oneAtATime = true;
    vm.getResults= getResults;
    vm.publish = publish;
    vm.redo = redo;
    vm.videoUrl = undefined;
    vm.error = '';
    vm.success = '';
    vm.videoUrls = {};

    vm.myInterval = 3000;
    vm.noWrapSlides = false;
    vm.activeSlide = 0;
    vm.success = undefined;

    vm.$onInit = function() {
            _loadList();
        };

    function _loadList() {
          MissionService.getMissionNames().then(
                function (response) {
                    vm.missionNames = response.data;
                    console.log(vm.missionNames);
                    if(vm.missionNames.length == 0){
                    vm.message = true;
                    }

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

    function getResults (name){

         MissionService.getMissionsResultsByName(name).then(
                    function (response) {
                        vm.missions = response.data;
                        console.log(vm.missions);
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
                        console.log();
                        vm.videoUrls[name] = "/api/missionsUI/video/" + vm.missions[0].id;


                    });
        }

    function videoUrl(name) {
        console.log(name);
        return vm.videoUrl[name];
    }

    function publish(index) {
            MissionService.publish(vm.missions[index].orderId).then(
                function () {
                    console.log('mission results successfully published');
                    vm.success = "Mission results successfully published";
                    vm.clicked=true;
               },
               function (err) {
                   if (err.staus != 500) {
                       vm.error = err.data.message;
                   }
                   else {
                       vm.error = err.statusText;
                   }
               });
        }

        function redo(index) {
                MissionService.redo(vm.missions[index].id).then(
                   function () {
                        console.log('mission status changed to accepted');
                        vm.success = "mission status changed to accepted";
                        _loadList();
                   },
                   function (err) {
                       if (err.staus != 500) {
                           vm.error = err.data.message;
                       }
                       else {
                           vm.error = err.statusText;
                       }
                   });
            }
}

Controller.$inject = ['$scope','MissionService'];
require('./list.scss');
module.component('missionList', {
    controller: Controller,
    templateUrl: require('./list.html')
});