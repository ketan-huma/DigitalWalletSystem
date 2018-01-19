loginController.$inject = ['$scope', '$http', '$window'];

export default function loginController($scope, $http, $window) {
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

        login.then(function (result, status, headers, config) {
            console.log("Login " + result);

            if (result.data === "true") {
                $window.location.href = "index.htm#!/cardDetails";
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

        logout.then(function (result, status, headers, config) {
            console.log(result);

            if (result.data === "true") {
                console.log("Logout called");
                $window.location.href = "index.htm#!/login";
                $window.location.reload();
            }
        });
    };
}