routing.$inject = ['$routeProvider', '$httpProvider'];

export default function routing($routeProvider, $httpProvider) {
    $routeProvider.
            when("/register", {templateUrl: "partials/register.html", controller: "registerController"}).
            when("/login", {templateUrl: "partials/login.html", controller: "loginController"}).
            when("/home", {templateUrl: "partials/home.html"}).
            when("/cardDetails", {templateUrl: "partials/cardDetails.html", controller: "cartDetailsController"}).
            otherwise({redirectTo: 'home'});
    
    $httpProvider.interceptors.push('walletHttpInterceptor');
}