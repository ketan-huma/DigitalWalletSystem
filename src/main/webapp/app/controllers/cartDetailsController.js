cartDetailsController.$inject = ['$scope', '$http', '$window'];

export default function cartDetailsController($scope, $http, $window) {
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

        addCard.then(function (response, status, headers, config) {
            console.log("Card Add Result " + response.data);
            $scope.getCardDetails();
        });
    };

    $scope.getCardDetails = function () {
        var getCardDetails = $http({
            method: "GET",
            url: "./wallet/card/getCardDetails.htm",
            headers: {"Content-Type": "application/json"}
        });

        getCardDetails.then(function (response, status, headers, config) {
            console.log(response);
            $scope.cardDetails = response.data;
        });
    };

    $scope.getCardDetails();
}