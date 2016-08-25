var module = require('main_module');

function Controller($scope) {

    var vm = this;
    vm.myInterval = 2000;
    vm.noWrapSlides = false;
    vm.activeSlide = 0;

     $scope.myInterval = 5000;
      $scope.noWrapSlides = false;
      $scope.active = 0;


    vm.images=[
    {name: 'antanas.jpg'},
    {name: 'donatas.jpg'},
    {name: 'mark.jpg'}

    ]


}

//Controller.$inject = ['$scope','login'];
require('./home.scss');
module.component('homeFirst', {
    controller: Controller,
    templateUrl: require('./home.html')

});


