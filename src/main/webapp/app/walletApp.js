var wallet = angular.module('wallet', ['ngRoute']);

wallet.factory('walletHttpInterceptor', ['$q', '$rootScope', '$location', function ($q, $rootscope, $location) {
        return {
            responseError: function (rejection) {
                if (rejection.status === 401) {
                    console.log("Unauthorized");
                    $location.path('/login');
                }
                return $q.reject(rejection);
            }
        };
    }]);


wallet.config(function ($routeProvider, $httpProvider) {
    $routeProvider.
            when("/register", {templateUrl: "partials/register.html", controller: "registerController"}).
            when("/login", {templateUrl: "partials/login.html", controller: "loginController"}).
            when("/home", {templateUrl: "partials/home.html"}).
            when("/cardDetails", {templateUrl: "partials/cardDetails.html", controller: "cartDetailsController"}).
            otherwise({redirectTo: 'home'});
    $httpProvider.interceptors.push('walletHttpInterceptor');
});

wallet.controller('registerController', function ($scope, $http, $window) {
    $scope.userData = {};

    $scope.registerFailed = false;

    $scope.register = function () {
        console.log($scope.userData);

        var register = $http({
            method: "POST",
            url: "./wallet/register.htm",
            data: $scope.userData,
            headers: {"Content-Type": "application/json"}
        });

        register.success(function (result, status, headers, config) {
            console.log("Registered " + result);
            if (result === "true") {
                $window.location.href = "index.htm#/cardDetails";
                $window.location.reload();
            } else {
                $scope.registerFailed = true;
            }
        });
    };
});

wallet.controller('loginController', function ($scope, $http, $window) {
    $scope.userData = {};
    $scope.loginFailed = false;

    $scope.login = function () {
        console.log($scope.userData);

        var login = $http({
            method: "POST",
            url: "./wallet/login.htm",
            data: $scope.userData,
            dataType: 'json',
            headers: {"Content-Type": "application/json"}
        });

        login.success(function (result, status, headers, config) {
            console.log("Login " + result);

            if (result === "true") {
                $window.location.href = "index.htm#/cardDetails";
                $window.location.reload();
            } else {
                $scope.loginFailed = true;
            }
        });
    };

    $scope.logout = function () {
        var logout = $http({
            method: "POST",
            url: "./wallet/logout.htm",
            dataType: 'json',
            headers: {"Content-Type": "application/json"}
        });

        logout.success(function (result, status, headers, config) {
            console.log("Logout " + result);

            if (result === "true") {
                $window.location.href = "index.htm#/login";
                $window.location.reload();
            }
        });
    };
});

wallet.controller('cartDetailsController', function ($scope, $http, $location) {
    $scope.cardData = {};
    $scope.cardDetails = [];

    $scope.saveCard = function () {
        console.log($scope.cardData);

        var addCard = $http({
            method: "POST",
            url: "./wallet/card/addCardDetails.htm",
            data: $scope.cardData,
            headers: {"Content-Type": "application/json"}
        });

        addCard.success(function (data, status, headers, config) {
            console.log("Card Add Result " + data);
            $scope.getCardDetails();
        });
    };

    $scope.getCardDetails = function () {
        var getCardDetails = $http({
            method: "GET",
            url: "./wallet/card/getCardDetails.htm",
            headers: {"Content-Type": "application/json"}
        });

        getCardDetails.success(function (data, status, headers, config) {
            console.log(data);
            $scope.cardDetails = data;
        });
    };

    $scope.getCardDetails();
});

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