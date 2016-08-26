var module = require('main_module');

function Controller($scope, MissionService) {

    var vm = this;
    vm.missionIds={};
    vm.mission = {};
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
          MissionService.getMissionIds().then(
                function (response) {
                    vm.missionIds = response.data;
                    console.log(vm.missionIds);
                    if(vm.missionIds.length == 0){
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

    function getResults (missionId){

         MissionService.getOrderResultsByMissionId(missionId).then(
                    function (response) {
                        vm.mission = response.data;
                        console.log(vm.mission);


                            vm.mission.startNavigationData.x = vm.mission.startNavigationData.x || 0;
                            vm.mission.startNavigationData.y = vm.mission.startNavigationData.y || 0;
                            vm.mission.startNavigationData.z = vm.mission.startNavigationData.z || 0;

                            vm.mission.finishNavigationData.x = vm.mission.finishNavigationData.x || 0;
                            vm.mission.finishNavigationData.y = vm.mission.finishNavigationData.y || 0;
                            vm.mission.finishNavigationData.z = vm.mission.finishNavigationData.z || 0;

                            vm.mission.startNavigationData.x = Math.round( vm.mission.startNavigationData.x*100)/100;
                            vm.mission.startNavigationData.y = Math.round( vm.mission.startNavigationData.y*100)/100;
                            vm.mission.startNavigationData.z = Math.round( vm.mission.startNavigationData.z*100)/100;
                            vm.mission.finishNavigationData.x = Math.round( vm.mission.finishNavigationData.x*100)/100;
                            vm.mission.finishNavigationData.y = Math.round( vm.mission.finishNavigationData.y*100)/100;
                            vm.mission.finishNavigationData.z = Math.round( vm.mission.startNavigationData.z*100)/100;



                        $scope.status = false;
                        console.log();
                        vm.videoUrls[missionId] = "/api/missionsUI/video/" + vm.mission.id;


                    });
        }

    function videoUrl(missionId) {
        console.log(missionId);
        return vm.videoUrl[missionId];
    }

    function publish() {
            MissionService.publish(vm.mission.id).then(
                function () {
                    console.log('mission results successfully published');
                    vm.success = "mission results successfully published";
                    vm.button = true;
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

        function redo() {
                MissionService.redo(vm.mission.id).then(
                   function () {
                        console.log('mission status changed to accepted');
                        vm.success = "Mission status changed to accepted";
                        vm.button = true;
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