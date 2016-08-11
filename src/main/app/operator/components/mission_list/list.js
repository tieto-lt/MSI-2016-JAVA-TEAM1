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
        MissionService.getAllMissions().then(
            function (response) {
                console.log(response.data);
//                vm.missions = response.data;
                vm.missions = [
                    {id: 1, title: "Test mission ", date: new Date()},
                    {id: 2, title: "Another test mission", date: new Date()},
                    {id: 1, title: "Test mission ", date: new Date()},
                    {id: 2, title: "Another test mission", date: new Date()},
                    {id: 1, title: "Test mission ", date: new Date()},
                    {id: 2, title: "Another test mission", date: new Date()},
                ];

                $scope.results = [
                    {id : 5, start: "Start", finish :"Finish", image: "Image", batteryStatus: 50},
                    {id : 10, start: "Start", image: "Image", batteryStatus: 99},
                    {id : 5, start: "Start", finish :"Finish", image: "Image", batteryStatus: 0},
                    {id : 10, start: "Start", image: "Image", batteryStatus: 20},
                ];

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
