/*jshint esversion: 6 */
import angular from 'angular';
import ngRoute from 'angular-route';
import appRoute from '../app/config/walletConfig.js';
import httpInterceptor from '../app/interceptors/walletHttpInterceptor.js';
import registerController from '../app/controllers/registrationController.js';
import loginController from '../app/controllers/loginController.js';
import cartController from '../app/controllers/cartDetailsController.js';

var wallet = angular.module('wallet', [ngRoute]);
wallet.factory('walletHttpInterceptor', httpInterceptor);
wallet.config(appRoute);
wallet.controller('registerController', registerController);
wallet.controller('loginController', loginController);
wallet.controller('cartDetailsController', cartController);

wallet.directive('usernameAvailable', function ($q, $http) {
    return {
        restrict: 'AE',
        require: 'ngModel',
        link: function (scope, elm, attr, model) {
            model.$asyncValidators.usernameExists = function (modelValue, viewValue) {
                var defer = $q.defer();
                var checkEmail = $http({
                    method: "POST",
                    url: "./wallet/checkEmailExists.htm",
                    data: viewValue,
                    headers: {"Content-Type": "application/json"},
                    responseType: 'json'
                });

                checkEmail.then(function (result, status, headers, config) {
                    console.log(result.data);

                    if (result.data) {
                        return defer.reject();
                    } else {
                        return defer.resolve();
                    }
                });
                return defer.promise;
            };
        }
    };
});