var module = require('main_module');

function Controller() {
}

/*require('./home.scss');*/
module.component('homeFirst', {
    controller: Controller,
    templateUrl: require('./home.html')
});


