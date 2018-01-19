httpInterceptor.$inject = ['$q', '$rootScope', '$location'];

export default function httpInterceptor($q, $rootscope, $location) {
    return {
        responseError: function (rejection) {
            if (rejection.status === 401) {
                console.log("Unauthorized");
                $location.path('/login');
            }
            return $q.reject(rejection);
        }
    };
}