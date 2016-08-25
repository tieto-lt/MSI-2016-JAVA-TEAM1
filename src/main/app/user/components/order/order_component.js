var module = require('main_module');

function Controller(UserService, $state) {
  var vm = this;
  vm.order = {};
  vm.user = {};
  vm.createOrder = createOrder;
  vm.checkIfEqual = checkIfEqual;
  vm.showMessage = showMessage;
  vm.find = find;
  vm.remove = remove;
  vm.selectedObject = [];
  vm.options = ["FRONT", "BOTTOM"];
  vm.order.video = false;
  vm.check = check;
//  vm.acceptPayment = acceptPayment;
//  vm.declinePayment = declinePayment;

  $.material.init()


  vm.size = 45;
  vm.all = [
    {id :0, selected: false, color: "#928f8f", name: "CASTLE", size: 55, text:"#171313", width: 5, cameraPosition: "FRONT"},
    {id :1, selected: false, color: "#928f8f", name: "HOUSE", size: 55, text:"#171313", width: 5, cameraPosition: "FRONT"},
    {id :2, selected: false, color: "#928f8f", name: "GARDEN", size: 55, text:"#171313", width: 5, cameraPosition: "FRONT"},
    {id :3, selected: false, color: "#928f8f", name: "LAKE", size: 55, text:"#171313", width: 5, cameraPosition: "FRONT"}
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
         /* UserService.getBalance(vm.user.id).then(
            function(response){
                if(response.data < vm.selectedObject * 5){
                    console.log("neuztenka");
                }
            },
            function(err){

          });*/
          for( i = 0; i < vm.selectedObject.length; i++){
            vm.obj[i] = {name: vm.selectedObject[i].name, cameraPosition : vm.selectedObject[i].cameraPosition};
          }
          vm.order.mapItems = vm.obj;
          if(!checkIfEqual()){
           console.log(vm.order);
           UserService.createOrder(vm.order).then(
                function(response) {
                   vm.message = !vm.error;
                   vm.previousOrder.fullName = vm.order.fullName;
                   vm.previousOrder.phone = vm.order.phone;
                   vm.previousOrder.email = vm.order.email;
                   vm.previousOrder.details = vm.order.details;
                   vm.previousOrder.missionName = vm.order.missionName;
                   $state.go('root.customerOrders');
                 },
                 function (err) {
                   vm.error = !vm.message;
                 });
         }
    }

//    function acceptPayment(id){
//        UserService.acceptPayment(id).then(
//            function(response){
//                console.log(response.data);
//            },
//            function(err){
//                console.log("error");
//            }
//        });
//    }
//
//    function declinePayment(id){
//            UserService.declinePayment(id).then(
//            function(response){
//                console.log(response.data);
//            },
//            function(err){
//                console.log("error");
//            }
//        });
//    }




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

   function check(){
        if(vm.selectedObject.length<1){
            console.log("true");
            return true;
        }
        console.log("false");
        return false;
   }

}
Controller.$inject = ['UserService', '$state'];
require('./order_component.scss');
module.component('orderComponent', {
    controller: Controller,
    templateUrl: require('./order_component.html')
});