var module = require('main_module');

function Controller( $scope, MissionService) {

    var vm = this;
    vm.missions = {};
    $scope.oneAtATime = true;

    vm.$onInit = function() {
            _loadList();
        };

    function _loadList() {
        MissionService.getAllMissions().then(
            function (response) {
            console.log(response.data);
                vm.missions = response.data;
            },
            function (err) {
                console.log('Error',err);
            });
     }

    $scope.groups = [
       {
          title: 'Dynamic Group Header - 1',
          content: 'Dynamic Group Body - 1'
        },
        {
          title: 'Dynamic Group Header - 2',
          content: 'Dynamic Group Body - 2'
        }
    ];

    $scope.items = ['Item 1', 'Item 2', 'Item 3'];

    $scope.addItem = function() {
       var newItemNo = $scope.items.length + 1;
       $scope.items.push('Item ' + newItemNo);
     };

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
