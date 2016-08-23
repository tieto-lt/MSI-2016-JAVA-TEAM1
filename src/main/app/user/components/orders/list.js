var module = require('main_module');

function Controller($scope,OrdersService) {

    var vm = this;
    vm.orders = {};
    vm.information = {};
    vm.result = {};
    vm.message = false;
    $scope.oneAtATime = true;
    vm.getResults= getResults;
    vm.setSelectedObjects = setSelectedObjects;
    vm.fakeOrder = [];
    vm.selected = [];
    vm.list = [];

    vm.all = [
        {id :0, selected: false, color: "#928f8f", name: "Start",    size: 55, text:"#171313", width: 5, camera: "front"},
        {id :1, selected: false, color: "#928f8f", name: "1 object", size: 55, text:"#171313", width: 5, camera: "front"},
        {id :2, selected: false, color: "#928f8f", name: "2 object", size: 55, text:"#171313", width: 5, camera: "front"},
        {id :3, selected: false, color: "#928f8f", name: "3 object", size: 55, text:"#171313", width: 5, camera: "front"},
        {id :4, selected: false, color: "#928f8f", name: "4 object", size: 55, text:"#171313", width: 5, camera: "front"}
      ];

    vm.fakeOrder = [
        {name : "Start",    cameraPosition : "front"},
        {name : "1 object", cameraPosition : "front"},
        {name : "4 object", cameraPosition : "front"}
    ];



    vm.myInterval = 3000;
    vm.noWrapSlides = false;
    vm.activeSlide = 0;

    vm.$onInit = function() {
            _loadList();

        };

    function _loadList() {
        OrdersService.getAllOrders().then(
            function (response) {
                vm.orders = response.data;
                if(vm.orders.length == 0){
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

    function getResults (customerId) {
        OrdersService.getOrderResults(customerId).then(
            function(response){
                vm.result=response.data[0];
                if(vm.result == null){
                    console.log("null");
                }
            }
        )
        setSelectedObjects(vm.fakeOrder);
     }

        $scope.test = function(text) {
          alert(text);
        }

     function setSelectedObjects(array){
        for(i = 0; i < vm.all.length; i++){
            for( j = 0; j< array.length; j++){
                if(array[j].name == vm.all[i].name){
                    vm.all[i].name = array[j].name;
                    vm.all[i].cameraPosition = array[j].cameraPosition;
                    vm.all[i].color = "#009688";
                    break;
                }
            }
        }
    }
}

Controller.$inject = ['$scope','OrdersService'];
require('./list.scss');
module.component('customerOrders', {
    controller: Controller,
    templateUrl: require('./list.html')
});