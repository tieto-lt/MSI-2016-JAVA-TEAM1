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
    /*order_id;*/

    /*vm.drop = drop;*/

     /*Carousel variables*/

   /* vm.allObjects = [
        {id :0, selected: false, color: "#928f8f", name: "Start",    size: 55, text:"#171313", width: 5, camera: "front"},
        {id :1, selected: false, color: "#928f8f", name: "1 object", size: 55, text:"#171313", width: 5, camera: "front"},
        {id :2, selected: false, color: "#928f8f", name: "2 object", size: 55, text:"#171313", width: 5, camera: "front"},
        {id :3, selected: false, color: "#928f8f", name: "3 object", size: 55, text:"#171313", width: 5, camera: "front"},
        {id :4, selected: false, color: "#928f8f", name: "4 object", size: 55, text:"#171313", width: 5, camera: "front"}
      ];
*/
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
                console.log(vm.orders.length);
                console.log(vm.orders);
                if(vm.orders.length == 0){
                vm.message = true;
                console.log(vm.message);
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
        //console.log(vm.selected);
     }

        $scope.test = function(text) {
          alert(text);
        }

     function setSelectedObjects(array){
        console.log(array);
        for( i = 0; i<array.length; i++){
            if(array[i].name == "1 object" || array[i].name == "2 object" || array[i].name == "3 object" || array[i].name == "4 object" || array[i].name == "Start"){
                vm.selected[vm.selected.length] = { name : array[i].name, cameraPosition : array[i].cameraPosition, color:"#009688"};
            }
            else{
                vm.selected[vm.selected.length] = { name : "", cameraPosition : "", color:"#928f8f"};
            }
        }
        //console.log(vm.selected);
    }

}

/*for( i = 0; i<vm.array.length; i++){
            if(vm.array[i].name == "1 object" ||
               vm.array[i].name == "2 object" ||
               vm.array[i].name == "3 object" ||
               vm.array[i].name == "4 object"
               vm.array[i].name == "Start"){

              // vm.selected[vm.selected.length] = {name : vm.array[i].name, cameraPosition : vm.array[i].cameraPosition, color:"red"};
            }
            else{
             //  vm.selected[vm.selected.length] = {name : "", cameraPosition : "", color:"black"};
            }
        }*/

Controller.$inject = ['$scope','OrdersService'];
require('./list.scss');
module.component('customerOrders', {
    controller: Controller,
    templateUrl: require('./list.html')
});