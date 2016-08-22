var module = require('main_module');

function Controller(UserService) {
  var vm = this;
  vm.order = {};
  vm.user = {};
  vm.createOrder = createOrder;
  vm.checkIfEqual = checkIfEqual;
  vm.showMessage = showMessage;
  vm.find = find;
  vm.remove = remove;
  vm.selectedObject = [];


  vm.size = 45;
  vm.all = [
    {id :0, selected: false, color: "#928f8f", name: "Start",    size: 55, text:"#171313", width: 5, cameraPosition: "front"},
    {id :1, selected: false, color: "#928f8f", name: "1 object", size: 55, text:"#171313", width: 5, cameraPosition: "front"},
    {id :2, selected: false, color: "#928f8f", name: "2 object", size: 55, text:"#171313", width: 5, cameraPosition: "front"},
    {id :3, selected: false, color: "#928f8f", name: "3 object", size: 55, text:"#171313", width: 5, cameraPosition: "front"},
    {id :4, selected: false, color: "#928f8f", name: "4 object", size: 55, text:"#171313", width: 5, cameraPosition: "front"}
  ];

  vm.obj = [];

  vm.$onInit = function() {
          _loadOrderDetails();
  };

    function _loadOrderDetails() {
        UserService.get().then(
            function (response) {
                vm.user = response.data;
                _setOrder();
                vm.previousOrder = {};
            },
            function (err) {
                console.log('Error', err);
            });
    }

    function _setOrder() {
        vm.order.email = vm.user.email;
        vm.order.phone = vm.user.phone;
        vm.order.fullName = vm.user.name;
    }

    function createOrder(){
       for( i = 0; i < vm.selectedObject.length; i++){
           vm.obj[i] = {name: vm.selectedObject[i].name, cameraPosition : vm.selectedObject[i].cameraPosition};
       }
       vm.order.objects = vm.obj;
       console.log(vm.order);
          if(!checkIfEqual()){
             UserService.createOrder(vm.order).then(
                function(response) {
                   vm.message = !vm.error;
                   vm.previousOrder.fullName = vm.order.fullName;
                   vm.previousOrder.phone = vm.order.phone;
                   vm.previousOrder.email = vm.order.email;
                   vm.previousOrder.details = vm.order.details;
                   vm.previousOrder.missionName = vm.order.missionName;

                 },
                 function (err) {
                   vm.error = !vm.message;
                 });
         }
    }

    function checkIfEqual(){
        return ((vm.previousOrder.fullName == vm.order.fullName) &&
                (vm.previousOrder.phone == vm.order.phone) &&
                (vm.previousOrder.email == vm.order.email) &&
                (vm.previousOrder.details == vm.order.details) &&
                (vm.previousOrder.missionName == vm.order.missionName));
    }

  function showMessage(index){
          vm.all[index].selected = !vm.all[index].selected;
          if(vm.all[index].selected){
              vm.all[index].color = '#009688';
              vm.all[index].text = '#026f65';
              vm.all[index].width = vm.all[index].width + 3;
              vm.selectedObject[vm.selectedObject.length] = vm.all[index];
              vm.all[index].size = vm.all[index].size + 7;
          }
          else{
              vm.all[index].selected = !vm.all[index].selected;
              remove(index);
          }
     }

     function remove(index){
          vm.all[index].selected = !vm.all[index].selected;
          vm.all[index].color = '#928f8f';
          vm.all[index].text = '#171313';
          vm.all[index].width = vm.all[index].width - 3;
          vm.all[index].size = vm.all[index].size - 7;
          vm.selectedObject.splice(find(index),1);

     }
     function find (index){
          for( i = 0; i< vm.all.length; i++){
              if(vm.all[index].id == vm.selectedObject[i].id){
                  return i;
              }
          }
          return 0;
     }
   function find (index){
        for( i = 0; i< vm.all.length; i++){
            if(vm.all[index].id == vm.selectedObject[i].id){
                return i;
            }
        }
        return 0;
   }
}
Controller.$inject = ['UserService'];
require('./order_component.scss');
module.component('orderComponent', {
    controller: Controller,
    templateUrl: require('./order_component.html')
});