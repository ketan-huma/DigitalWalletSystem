registrationController.$inject = ['$scope', '$http', '$window'];

export default function registrationController($scope, $http, $window) {
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

        register.then(function (result, status, headers, config) {
            console.log("Registered " + result);
            if (result.data === "true") {
                $window.location.href = "index.htm#!/cardDetails";
                $window.location.reload();
            } else {
                $scope.registerFailed = true;
            }
        });
    };
}